import org.bouncycastle.crypto.*;
import org.bouncycastle.crypto.engines.*;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.*;

public class CipherBenchmark {
	
	static final int WARMUP_ITERATIONS = 3;
	static final int MEASURE_ITERATIONS = 10;
	static final int MIN_N = 10; // 1 KB
	static final int MAX_N = 26; // 64 MB
	
	static final SecureRandom RANDOM = new SecureRandom();
	
	public static void main(String[] args) throws Exception {
		org.bouncycastle.jce.provider.BouncyCastleProvider bcProv = new org.bouncycastle.jce.provider.BouncyCastleProvider();
		java.security.Security.addProvider(bcProv);
		
		String[] cipherNames = { "ChaCha20", "AES-256-CBC", "ARC4", "Blowfish-CBC", "Serpent-CBC" };
		
		// Results: cipher -> list of (n, avgTimeMs)
		Map<String, List<double[]>> results = new LinkedHashMap<>();
		for (String name : cipherNames) {
			results.put(name, new ArrayList<>());
		}
		
		for (int n = MIN_N; n <= MAX_N; n++) {
			int size = 1 << n;
			byte[] plaintext = new byte[size];
			RANDOM.nextBytes(plaintext);
			
			String sizeStr = formatSize(size);
			System.out.printf("=== Plaintext size: 2^%d = %s ===%n", n, sizeStr);
			
			for (String cipherName : cipherNames) {
				double avgMs = benchmark(cipherName, plaintext);
				double throughputMBs = (size / (1024.0 * 1024.0)) / (avgMs / 1000.0);
				results.get(cipherName).add(new double[] { n, avgMs });
				System.out.printf("  %-15s: %.4f ms  (%.2f MB/s)%n", cipherName, avgMs, throughputMBs);
			}
		}
		
		// Write CSV
		try (PrintWriter pw = new PrintWriter(new FileWriter("results.csv"))) {
			pw.print("n,size_bytes");
			for (String name : cipherNames) {
				pw.print("," + name.replace(",", "_"));
			}
			pw.println();
			
			for (int i = 0; i <= MAX_N - MIN_N; i++) {
				int n = MIN_N + i;
				int size = 1 << n;
				pw.printf("%d,%d", n, size);
				for (String name : cipherNames) {
					pw.printf(",%.6f", results.get(name).get(i)[1]);
				}
				pw.println();
			}
		}
		
		System.out.println("\nResults written to results.csv");
	}
	
	static double benchmark(String cipherName, byte[] plaintext) throws Exception {
		// Warmup
		for (int i = 0; i < WARMUP_ITERATIONS; i++) {
			encrypt(cipherName, plaintext);
		}
		
		// Measure
		double[] times = new double[MEASURE_ITERATIONS];
		for (int i = 0; i < MEASURE_ITERATIONS; i++) {
			long start = System.nanoTime();
			encrypt(cipherName, plaintext);
			long end = System.nanoTime();
			times[i] = (end - start) / 1_000_000.0;
		}
		
		// Average
		double sum = 0;
		for (double t : times) sum += t;
		return sum / times.length;
	}
	
	static byte[] encrypt(String cipherName, byte[] plaintext) throws Exception {
		byte[] key256 = new byte[32];
		RANDOM.nextBytes(key256);
		
		return switch (cipherName) {
			case "ChaCha20" -> encryptChaCha20(key256, plaintext);
			case "AES-256-CBC" -> encryptBlockCipher(AESEngine.newInstance(), key256, 16, plaintext);
			case "ARC4" -> encryptARC4(key256, plaintext);
			case "Blowfish-CBC" -> encryptBlockCipher(new BlowfishEngine(), Arrays.copyOf(key256, 16), 8, plaintext);
			case "Serpent-CBC" -> encryptBlockCipher(new SerpentEngine(), key256, 16, plaintext);
			default -> throw new IllegalArgumentException("Unknown cipher: " + cipherName);
		};
	}
	
	static byte[] encryptChaCha20(byte[] key, byte[] plaintext) throws Exception {
		byte[] nonce = new byte[12];
		RANDOM.nextBytes(nonce);
		
		StreamCipher cipher = new ChaCha7539Engine();
		cipher.init(true, new ParametersWithIV(new KeyParameter(key), nonce));
		
		byte[] output = new byte[plaintext.length];
		cipher.processBytes(plaintext, 0, plaintext.length, output, 0);
		return output;
	}
	
	static byte[] encryptARC4(byte[] key, byte[] plaintext) throws Exception {
		StreamCipher cipher = new RC4Engine();
		cipher.init(true, new KeyParameter(key));
		
		byte[] output = new byte[plaintext.length];
		cipher.processBytes(plaintext, 0, plaintext.length, output, 0);
		return output;
	}
	
	static byte[] encryptBlockCipher(BlockCipher engine, byte[] key, int ivSize, byte[] plaintext) throws Exception {
		byte[] iv = new byte[ivSize];
		RANDOM.nextBytes(iv);
		
		PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(
			CBCBlockCipher.newInstance(engine), new PKCS7Padding()
		);
		
		CipherParameters params = new ParametersWithIV(new KeyParameter(key), iv);
		cipher.init(true, params);
		
		byte[] output = new byte[cipher.getOutputSize(plaintext.length)];
		int len = cipher.processBytes(plaintext, 0, plaintext.length, output, 0);
		cipher.doFinal(output, len);
		return output;
	}
	
	static String formatSize(long bytes) {
		if (bytes < 1024) return bytes + " B";
		if (bytes < 1024 * 1024) return (bytes / 1024) + " KB";
		if (bytes < 1024L * 1024 * 1024) return (bytes / (1024 * 1024)) + " MB";
		return (bytes / (1024L * 1024 * 1024)) + " GB";
	}
}
