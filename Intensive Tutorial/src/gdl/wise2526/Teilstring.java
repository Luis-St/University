package gdl.wise2526;

public class Teilstring {
	
	public static void main(String[] args) {
	
	}
	
	public static int zaehleVorkommenString(String x, String y) {
		int anzahl = 0;
		
		for (int i = 0; i <= x.length() - y.length(); i++) {
			
			boolean gefunden = true;
			
			for (int j = 0; j < y.length(); j++) {
				if (x.charAt(i + j) != y.charAt(j)) {
					gefunden = false;
				}
			}
			
			if (gefunden) {
				anzahl++; // i += 1; i = i + 1;
			}
		}
		
		return anzahl;
	}
	
	public static int zaehleVorkommenArray(int[] arr, int[] muster) {
		int anzahl = 0;
		
		for (int i = 0; i <= arr.length - muster.length; i++) {
			
			boolean gefunden = true;
			
			for (int j = 0; j < muster.length; j++) {
				if (arr[i + j] != muster[j]) {
					gefunden = false;
				}
			}
			
			if (gefunden) {
				anzahl++;
			}
		}
		
		return anzahl;
	}
	
	public static int erstePositionString(String x, String y) {
		
		for (int i = 0; i <= x.length() - y.length(); i++) {
			
			boolean gefunden = true;
			
			for (int j = 0; j < y.length(); j++) {
				if (x.charAt(i + j) != y.charAt(j)) {
					gefunden = false;
				}
			}
			
			if (gefunden) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static int erstePositionArray(int[] arr, int[] muster) {
		
		for (int i = 0; i <= arr.length - muster.length; i++) {
			
			boolean gefunden = true;
			
			for (int j = 0; j < muster.length; j++) {
				if (arr[i + j] != muster[j]) {
					gefunden = false;
				}
			}
			
			if (gefunden) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static int letztePositionString(String x, String y) {
		int letztePosition = -1;
		
		for (int i = 0; i <= x.length() - y.length(); i++) {
			
			boolean gefunden = true;
			
			for (int j = 0; j < y.length(); j++) {
				if (x.charAt(i + j) != y.charAt(j)) {
					gefunden = false;
				}
			}
			
			if (gefunden) {
				letztePosition = i;
			}
		}
		
		return letztePosition;
	}
	
	public static int letztePositionArray(int[] arr, int[] muster) {
		int letztePosition = -1;
		
		for (int i = 0; i <= arr.length - muster.length; i++) {
			
			boolean gefunden = true;
			
			for (int j = 0; j < muster.length; j++) {
				
				if (arr[i + j] != muster[j]) {
					
					gefunden = false;
					
				}
				
			}
			
			if (gefunden) {
				letztePosition = i;
			}
		}
		
		return letztePosition;
	}
	
	public static boolean enthaeltString(String x, String y) {
		
		for (int i = 0; i <= x.length() - y.length(); i++) {
			
			boolean gefunden = true;
			
			for (int j = 0; j < y.length(); j++) {
				if (x.charAt(i + j) != y.charAt(j)) {
					gefunden = false;
				}
			}
			
			if (gefunden) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean enthaeltArray(int[] arr, int[] muster) {
		
		for (int i = 0; i <= arr.length - muster.length; i++) {
			
			boolean gefunden = true;
			
			for (int j = 0; j < muster.length; j++) {
				if (arr[i + j] != muster[j]) {
					gefunden = false;
				}
			}
			
			if (gefunden) {
				return true;
			}
		}
		
		return false;
	}
}
