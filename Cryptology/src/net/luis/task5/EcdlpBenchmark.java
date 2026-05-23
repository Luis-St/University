package net.luis.task5;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

public class EcdlpBenchmark {
	
	static final SecureRandom RANDOM = new SecureRandom();
	
	// BSI TR-02102-1 (vgl. Aufgabenblatt)
	static final int[] K = {
		16, 32, 56, 60, 64, 70, 100, 120, 128, 192, 256
	};
	static final int[] EC_N = {
		32, 64, 112, 120, 128, 140, 200, 240, 256, 384, 512
	};
	static final int[] GF_N = {
		64, 256, 512, 700, 816, 1000, 1900, 2800, 3200, 7900, 15500
	};
	
	static final int WARMUP = 50;
	
	static void main(String[] args) throws Exception {
		PrintWriter csv = new PrintWriter(new FileWriter("task/05/results.csv"));
		csv.println("k,security_bits,type,bitlen,time_us");
		
		for (int i = 0; i < K.length; i++) {
			int k = K[i];
			int n = EC_N[i];
			int N = GF_N[i];
			int reps = repsFor(N);
			
			System.out.printf("=== k=%d Bit | EC n=%d Bit | GF(p) N=%d Bit | reps=%d ===%n", k, n, N, reps);
			
			double[] ecTimes = benchmarkEc(n, reps);
			writeRows(csv, k, "EC", n, ecTimes);
			System.out.printf("  EC    [a]G       median=%10.3f us%n", median(ecTimes));
			
			double[] gfTimes = benchmarkGf(N, reps);
			writeRows(csv, k, "GF", N, gfTimes);
			System.out.printf("  GF(p) G^a mod p  median=%10.3f us%n", median(gfTimes));
			
			csv.flush();
		}
		
		csv.close();
		System.out.println("\nResults written to task/05/results.csv");
	}
	
	static int repsFor(int N) {
		if (N <= 1000) return 1000;
		if (N <= 3200) return 200;
		return 50;
	}
	
	static void writeRows(PrintWriter csv, int k, String type, int bitlen, double[] times) {
		for (double t : times) {
			csv.printf("%d,%d,%s,%d,%.6f%n", k, k, type, bitlen, t);
		}
	}
	
	// ============================================================
	// DLP in GF(p): A = G^a mod p
	// ============================================================
	
	static double[] benchmarkGf(int bits, int reps) {
		// Parametersatz (einmal pro Bitlaenge): Primzahl p, Generator G.
		BigInteger p = BigInteger.probablePrime(bits, RANDOM);
		BigInteger g = randomBelow(p.subtract(BigInteger.TWO)).add(BigInteger.TWO); // in [2, p-1)
		
		// JIT-Warmup
		for (int i = 0; i < WARMUP; i++) {
			g.modPow(randomBelow(p), p);
		}
		
		int inner = calibrate(() -> g.modPow(randomBelow(p), p));
		
		double[] times = new double[reps];
		for (int r = 0; r < reps; r++) {
			BigInteger[] expon = new BigInteger[inner];
			for (int j = 0; j < inner; j++) expon[j] = randomBelow(p);
			long start = System.nanoTime();
			for (int j = 0; j < inner; j++) g.modPow(expon[j], p);
			long end = System.nanoTime();
			times[r] = (end - start) / 1_000.0 / inner; // us pro Operation
		}
		return times;
	}
	
	// ============================================================
	// ECDLP: A = [a]G auf y^2 = x^3 + ax + b ueber GF(p)
	// ============================================================
	
	static double[] benchmarkEc(int bits, int reps) {
		// Parametersatz (einmal pro Bitlaenge): Kurve und Generator G.
		// Die Punktarithmetik uebernimmt Bouncy Castle (org.bouncycastle.math.ec).
		ECPoint g = randomGenerator(bits);

		// JIT-Warmup
		for (int i = 0; i < WARMUP; i++) {
			g.multiply(randomScalar(bits)).normalize();
		}

		int inner = calibrate(() -> g.multiply(randomScalar(bits)).normalize());

		double[] times = new double[reps];
		for (int r = 0; r < reps; r++) {
			BigInteger[] scalars = new BigInteger[inner];
			for (int j = 0; j < inner; j++) scalars[j] = randomScalar(bits);
			long start = System.nanoTime();
			for (int j = 0; j < inner; j++) g.multiply(scalars[j]).normalize();
			long end = System.nanoTime();
			times[r] = (end - start) / 1_000.0 / inner;
		}
		return times;
	}

	static BigInteger randomScalar(int bits) {
		BigInteger s = new BigInteger(bits, RANDOM);
		return s.signum() == 0 ? BigInteger.ONE : s;
	}

	/**
	 * Erzeugt eine zufaellige kurze Weierstrass-Kurve y^2 = x^3 + ax + b ueber GF(p)
	 * der gewuenschten Bitlaenge und liefert einen Generatorpunkt G.
	 * b wird so gewaehlt, dass der zufaellig gezogene Punkt G auf der Kurve liegt.
	 */
	static ECPoint randomGenerator(int bits) {
		BigInteger p = BigInteger.probablePrime(bits, RANDOM);
		BigInteger a = randomBelow(p);
		BigInteger gx = randomBelow(p);
		BigInteger gy = randomBelow(p.subtract(BigInteger.ONE)).add(BigInteger.ONE);
		// b = gy^2 - gx^3 - a*gx (mod p)
		BigInteger b = gy.multiply(gy)
			.subtract(gx.modPow(BigInteger.valueOf(3), p))
			.subtract(a.multiply(gx))
			.mod(p);
		// Ordnung/Kofaktor sind fuer die Laufzeitmessung irrelevant -> null.
		ECCurve curve = new ECCurve.Fp(p, a, b, null, null);
		return curve.createPoint(gx, gy);
	}
	
	static BigInteger randomBelow(BigInteger bound) {
		BigInteger r;
		do {
			r = new BigInteger(bound.bitLength(), RANDOM);
		} while (r.compareTo(bound) >= 0);
		return r;
	}
	
	// ============================================================
	// Hilfsfunktionen
	// ============================================================
	
	/** Bestimmt eine innere Schleifenlaenge, sodass eine Messung >= ~2 ms dauert. */
	static int calibrate(Runnable op) {
		long start = System.nanoTime();
		op.run();
		long single = System.nanoTime() - start;
		if (single <= 0) single = 1;
		int inner = (int) Math.max(1, Math.min(2000, 2_000_000L / single));
		return inner;
	}
	
	static double median(double[] values) {
		double[] copy = values.clone();
		Arrays.sort(copy);
		int m = copy.length / 2;
		return copy.length % 2 == 0 ? (copy[m - 1] + copy[m]) / 2 : copy[m];
	}
}
