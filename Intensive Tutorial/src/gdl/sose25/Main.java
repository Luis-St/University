package gdl.sose25;

public class Main {
	
	public static void main(String[] args) {
	
	}
	
	/*public static int f(String s, int n) {
		
		if (n == 4) {
			
			return 2 * n;
			
		}
		
		if (n > 14) {
			
			// s länge = 0 -> f(s, 11)
			// 11 > s länge > 0 -> f(s, 11 - s.length())
			// s länge = 11 -> f(s, 0)
			// s länge > 11 -> f(s, negativer Wert)
			
			int m = 11 - s.length();
			
			if (m == 4) {
				
				return m * 2;
				
			}
			
			return m;
			
			//return f(s, 11 - s.length());
			
		}
		return n;
	}*/
	
	public static int f(String s, int n) {
		while (true) {
			if (n == 4) {
				return 2 * n;
			} else if (n > 14) {
				n = 11 - s.length();
			} else {
				return n;
			}
		}
	}
	
}
