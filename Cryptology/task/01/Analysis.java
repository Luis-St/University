public class Analysis {
	
	static final int NUM_BITS = 20000;
	
	public static void main(String[] args) {
		// === LFSR Configuration ===
		// g(x) = x^16 + x^10 + x^6 + x^5 + 1
		// Initial value: (1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1)
		int[] lfsr = { 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 };
		int[] bits = new int[NUM_BITS];
		
		for (int i = 0; i < NUM_BITS; i++) {
			bits[i] = lfsr[15];
			int fb = (lfsr[15] + lfsr[9] + lfsr[5] + lfsr[4]) % 2;
			for (int j = 15; j > 0; j--) {
				lfsr[j] = lfsr[j - 1];
			}
			lfsr[0] = fb;
		}
		
		// First and last 25 bits
		StringBuilder first25 = new StringBuilder();
		StringBuilder last25 = new StringBuilder();
		for (int i = 0; i < 25; i++) first25.append(bits[i]);
		for (int i = NUM_BITS - 25; i < NUM_BITS; i++) last25.append(bits[i]);
		System.out.println("First 25 bits: " + first25);
		System.out.println("Last 25 bits:  " + last25);
		System.out.println();
		
		// === Monobit Test ===
		int xMonobit = 0;
		for (int b : bits) {
			xMonobit += b;
		}
		
		boolean monobitNist = xMonobit > 9725 && xMonobit < 10275;
		boolean monobitBsi = xMonobit > 9654 && xMonobit < 10346;
		
		System.out.println("=== Monobit Test ===");
		System.out.println("X = " + xMonobit);
		System.out.printf("NIST FIPS140-2: (9725, 10275) -> %s%n", monobitNist ? "PASS" : "FAIL");
		System.out.printf("BSI AIS 31:     (9654, 10346) -> %s%n", monobitBsi ? "PASS" : "FAIL");
		
		// === Poker Test ===
		int[] nibbleCounts = new int[16];
		for (int i = 0; i < NUM_BITS; i += 4) {
			int val = bits[i] * 8 + bits[i + 1] * 4 + bits[i + 2] * 2 + bits[i + 3];
			nibbleCounts[val]++;
		}
		
		double sumSq = 0;
		for (int c : nibbleCounts) {
			sumSq += (double) c * c;
		}
		double yPoker = (16.0 / 5000.0) * sumSq - 5000.0;
		
		boolean pokerNist = yPoker > 2.16 && yPoker < 46.17;
		boolean pokerBsi = yPoker > 1.03 && yPoker < 57.4;
		
		System.out.println("\n=== Poker Test ===");
		System.out.printf("Y = %.4f%n", yPoker);
		StringBuilder nibbleStr = new StringBuilder("[");
		for (int i = 0; i < 16; i++) {
			if (i > 0) nibbleStr.append(", ");
			nibbleStr.append(nibbleCounts[i]);
		}
		nibbleStr.append("]");
		System.out.println("Nibble frequencies: " + nibbleStr);
		System.out.printf("NIST FIPS140-2: (2.16, 46.17) -> %s%n", pokerNist ? "PASS" : "FAIL");
		System.out.printf("BSI AIS 31:     (1.03, 57.4)  -> %s%n", pokerBsi ? "PASS" : "FAIL");
		
		// === Run Test ===
		int[] zeroRuns = new int[7]; // index 1-6, where 6 means >=6
		int[] oneRuns = new int[7];
		int maxRun = 0;
		int currentBit = bits[0];
		int currentLen = 1;
		
		for (int i = 1; i < NUM_BITS; i++) {
			if (bits[i] == currentBit) {
				currentLen++;
			} else {
				int k = Math.min(currentLen, 6);
				if (currentBit == 0) {zeroRuns[k]++;} else oneRuns[k]++;
				if (currentLen > maxRun) maxRun = currentLen;
				currentBit = bits[i];
				currentLen = 1;
			}
		}
		// Last run
		int k = Math.min(currentLen, 6);
		if (currentBit == 0) {zeroRuns[k]++;} else oneRuns[k]++;
		if (currentLen > maxRun) maxRun = currentLen;
		
		int[][] nistRanges = { { 0, 0 }, { 2315, 2685 }, { 1114, 1386 }, { 527, 723 }, { 240, 384 }, { 103, 209 }, { 103, 209 } };
		int[][] bsiRanges = { { 0, 0 }, { 2267, 2733 }, { 1079, 1421 }, { 502, 748 }, { 233, 402 }, { 90, 223 }, { 90, 223 } };
		
		System.out.println("\n=== Run Test ===");
		System.out.printf("%-10s %-10s %-10s %-18s %-8s %-18s %-8s%n", "Length", "zero_k", "one_k", "NIST range", "NIST", "BSI range", "BSI");
		
		boolean allRunNist = true;
		boolean allRunBsi = true;
		for (int i = 1; i <= 6; i++) {
			String label = (i == 6) ? ">=" + i : String.valueOf(i);
			int z = zeroRuns[i];
			int o = oneRuns[i];
			boolean passNist = z >= nistRanges[i][0] && z <= nistRanges[i][1] && o >= nistRanges[i][0] && o <= nistRanges[i][1];
			boolean passBsi = z >= bsiRanges[i][0] && z <= bsiRanges[i][1] && o >= bsiRanges[i][0] && o <= bsiRanges[i][1];
			if (!passNist) {
				allRunNist = false;
			}
			if (!passBsi) {
				allRunBsi = false;
			}
			System.out.printf("%-10s %-10d %-10d [%d, %d]%6s %-8s [%d, %d]%6s %-8s%n", label, z, o, nistRanges[i][0], nistRanges[i][1], "", passNist ? "PASS" : "FAIL", bsiRanges[i][0], bsiRanges[i][1], "", passBsi ? "PASS" : "FAIL");
		}
		
		// === Long Run Test ===
		boolean longrunNist = maxRun < 34;
		boolean longrunBsi = maxRun < 31;
		
		System.out.println("\n=== Long Run Test ===");
		System.out.println("Longest run: " + maxRun);
		System.out.printf("NIST FIPS140-2 (< 34): %s%n", longrunNist ? "PASS" : "FAIL");
		System.out.printf("BSI AIS 31     (< 31): %s%n", longrunBsi ? "PASS" : "FAIL");
	}
}
