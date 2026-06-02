package gdl.wise2526.ka;

public class TeilString {
	
	public static void main(String[] args) {
	
	}
	
	public static int f0(String x, String y) {
		
		int stelle = -1;
		
		for (int i = 0; i < x.length() - (y.length() - 1); i++) {
			
			boolean match = true;
			
			for (int j = 0; j < y.length(); j++) {
				
				if (x.charAt(i + j) != y.charAt(j)) {
					
					match = false;
					
				}
				
			}
			
			/*if (match) { // Suchen der ersten Position
				return i;
			}*/
			
			if (match == true) {
				stelle = i;
			}
			
		}
		
		return stelle;
	}
	
	public static int f1(String[] x, String[] y) {
		
		int stelle = -1;
		
		for (int i = 0; i < x.length - (y.length - 1); i++) {
			
			boolean match = true;
			
			for (int j = 0; j < y.length; j++) {
				
				if (!x[i + j].equals(y[j])) {
					
					match = false;
					
				}
				
			}
			
			if (match == true) {
				stelle = i;
			}
			
		}
		
		return stelle;
	}
	
}
