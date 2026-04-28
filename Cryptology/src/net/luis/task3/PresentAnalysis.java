package net.luis.task3;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class PresentAnalysis {

	static final int[] SBOX = {0xC, 0x5, 0x6, 0xB, 0x9, 0x0, 0xA, 0xD, 0x3, 0xE, 0xF, 0x8, 0x4, 0x7, 0x1, 0x2};
	static final BigInteger MASK128 = BigInteger.ONE.shiftLeft(128).subtract(BigInteger.ONE);

	static long sBoxLayer(long state) {
		long result = 0;
		for (int i = 0; i < 16; i++) {
			int nibble = (int) ((state >>> (i * 4)) & 0xF);
			result |= ((long) SBOX[nibble]) << (i * 4);
		}
		return result;
	}
	
	static long pLayer(long state) {
		long result = 0;
		for (int i = 0; i < 64; i++) {
			if ((state & (1L << i)) != 0) {
				int dest = (i == 63) ? 63 : (i * 16) % 63;
				result |= 1L << dest;
			}
		}
		return result;
	}

	static long[] generateRoundKeys128(BigInteger key) {
		long[] roundKeys = new long[32];
		for (int i = 1; i <= 32; i++) {
			roundKeys[i - 1] = key.shiftRight(64).longValue();
			if (i <= 31) {
				key = key.shiftLeft(61).or(key.shiftRight(67)).and(MASK128);
				int hi = key.shiftRight(124).intValue() & 0xF;
				int lo = key.shiftRight(120).intValue() & 0xF;
				key = key.andNot(BigInteger.valueOf(0xFFL).shiftLeft(120));
				key = key.or(BigInteger.valueOf(SBOX[hi]).shiftLeft(124));
				key = key.or(BigInteger.valueOf(SBOX[lo]).shiftLeft(120));
				key = key.xor(BigInteger.valueOf(i).shiftLeft(62));
			}
		}
		return roundKeys;
	}

	static long presentEncrypt(long plaintext, long[] roundKeys, long[] states) {
		long state = plaintext;
		for (int i = 0; i < 31; i++) {
			state ^= roundKeys[i];
			state = sBoxLayer(state);
			state = pLayer(state);
			if (states != null) states[i] = state;
		}
		return state ^ roundKeys[31];
	}

	static void countNibbles(long state, int[] freq) {
		for (int i = 0; i < 16; i++) {
			freq[(int) ((state >>> (i * 4)) & 0xF)]++;
		}
	}

	static double percentile(int[] sorted, double p) {
		double pos = (sorted.length - 1) * p;
		int lo = (int) Math.floor(pos);
		int hi = Math.min((int) Math.ceil(pos), sorted.length - 1);
		if (lo == hi) return sorted[lo];
		return sorted[lo] + (pos - lo) * (sorted[hi] - sorted[lo]);
	}

	public static void main(String[] args) throws Exception {
		byte[] keyBytes = "LuisLuisLuisLuis".getBytes(StandardCharsets.US_ASCII);
		BigInteger key = new BigInteger(1, keyBytes);
		System.out.printf("Key: 0x%032X%n", key);

		long[] testRK = generateRoundKeys128(BigInteger.ZERO);
		long testCT = presentEncrypt(0L, testRK, null);
		System.out.printf("Test: 0x%016X (expected 0x96DB702A2E6900AF)%n", testCT);
		if (testCT != 0x96DB702A2E6900AFL) throw new RuntimeException("Test vector failed!");

		long[] roundKeys = generateRoundKeys128(key);

		long[] statesZero = new long[31];
		long ctZero = presentEncrypt(0L, roundKeys, statesZero);
		System.out.printf("%nALL-ZERO CT: 0x%016X%n", ctZero);

		long[][] statesHW1 = new long[64][31];
		long[] ctHW1 = new long[64];
		for (int bit = 0; bit < 64; bit++) {
			ctHW1[bit] = presentEncrypt(1L << bit, roundKeys, statesHW1[bit]);
		}

		System.out.println("\nHW-1 Ciphertexts (Auswahl):");
		for (int bit : new int[]{0, 15, 31, 47, 63}) {
			System.out.printf("  Bit %2d (PT=0x%016X): CT=0x%016X%n", bit, 1L << bit, ctHW1[bit]);
		}

		int[][] hammingDists = new int[31][64];
		for (int r = 0; r < 31; r++) {
			for (int j = 0; j < 64; j++) {
				hammingDists[r][j] = Long.bitCount(statesZero[r] ^ statesHW1[j][r]);
			}
		}

		System.out.printf("%n=== Diffusion ===%n%6s %5s %5s %7s %7s%n", "Runde", "Min", "Max", "Mean", "Median");
		for (int r = 0; r < 31; r++) {
			int[] sorted = hammingDists[r].clone();
			Arrays.sort(sorted);
			double mean = Arrays.stream(sorted).average().orElse(0);
			double median = percentile(sorted, 0.50);
			System.out.printf("%6d %5d %5d %7.1f %7.1f%n", r + 1, sorted[0], sorted[63], mean, median);
		}

		int[][] nibbleFreq = new int[31][16];
		for (int r = 0; r < 31; r++) {
			countNibbles(statesZero[r], nibbleFreq[r]);
			for (int j = 0; j < 64; j++) {
				countNibbles(statesHW1[j][r], nibbleFreq[r]);
			}
		}

		double expected = 1040.0 / 16.0;
		System.out.printf("%n=== Confusion ===%n");
		for (int r = 0; r < 31; r++) {
			double chi2 = 0;
			for (int k = 0; k < 16; k++) {
				chi2 += Math.pow(nibbleFreq[r][k] - expected, 2) / expected;
			}
			System.out.printf("Runde %2d: Chi²=%8.2f  %s%n", r + 1, chi2, Arrays.toString(nibbleFreq[r]));
		}

		String outDir = "task/03/";

		writeHammingBoxplots(hammingDists, outDir + "hamming_boxplots.tex");
		writeNibbleCSV(nibbleFreq, outDir + "nibble_freq.csv");

		System.out.println("\nOutput: " + outDir + "hamming_boxplots.tex, " + outDir + "nibble_freq.csv");
	}

	static void writeHammingBoxplots(int[][] hammingDists, String filename) throws IOException {
		try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
			for (int r = 0; r < 31; r++) {
				int[] sorted = hammingDists[r].clone();
				Arrays.sort(sorted);

				double q1 = percentile(sorted, 0.25);
				double median = percentile(sorted, 0.50);
				double q3 = percentile(sorted, 0.75);
				double iqr = q3 - q1;
				double lwBound = q1 - 1.5 * iqr;
				double uwBound = q3 + 1.5 * iqr;

				int lw = sorted[0];
				for (int v : sorted) {
					if (v >= lwBound) { lw = v; break; }
				}
				int uw = sorted[sorted.length - 1];
				for (int i = sorted.length - 1; i >= 0; i--) {
					if (sorted[i] <= uwBound) { uw = sorted[i]; break; }
				}

				List<Integer> outliers = new ArrayList<>();
				for (int v : sorted) {
					if (v < lwBound || v > uwBound) outliers.add(v);
				}

				pw.printf("\\addplot[boxplot prepared={draw position=%d, ", r + 1);
				pw.printf("lower whisker=%d, lower quartile=%.2f, median=%.2f, ", lw, q1, median);
				pw.printf("upper quartile=%.2f, upper whisker=%d}, fill=blue!20]", q3, uw);

				if (!outliers.isEmpty()) {
					pw.print(" coordinates {");
					for (int o : outliers) pw.printf(" (0,%d)", o);
					pw.print(" }");
				} else {
					pw.print(" coordinates {}");
				}
				pw.println(";");
			}
		}
	}

	static void writeNibbleCSV(int[][] nibbleFreq, String filename) throws IOException {
		try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
			pw.print("nibble");
			for (int r = 1; r <= 31; r++) pw.printf(",r%d", r);
			pw.println();
			for (int k = 0; k < 16; k++) {
				pw.print(k);
				for (int r = 0; r < 31; r++) pw.printf(",%d", nibbleFreq[r][k]);
				pw.println();
			}
		}
	}
}
