package gdl.sose26.ka;

public class Aufgabe4 {
	
	static void main() {
	
	}
	
	public static int f(String x, String y /*teilstring*/) {
		
		int anzahl = 0;
		
		for (int i = 0; i < x.length() - (y.length() - 1); i++) {
			
			int buchstaben = 0;
			for (int j = 0; j < y.length(); j++) {
			
				if (x.charAt(i + j) == y.charAt(j)) {
				
					buchstaben++;
					// buchstaben += 1;
					// buchstaben = buchstaben + 1;
					
				}
			}
			
			if (buchstaben == y.length()) {
				
				anzahl++;
				
			}
		}
		
		return anzahl;
	}
	
}
