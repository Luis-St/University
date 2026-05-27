package sose25.ka;

/**
 *
 * @author Luis-St
 *
 */

public class Aufgabe_6 {
	
	public static int f(String s, int n) {
		if (n == 4) {
			return 2 * n;
		}
		
		if (n > 14) {
			// Rekursive nur dann wenn n > 14
			
			// s eine Länge = 0 -> f(s, 11 - 0) -> 11
			// s eine Länge = 2 -> f(s, 11 - 2) -> 9
			// s eine Länge = 11 -> f(s, 11 - 11) -> 0
			// s eine Länge = 20 -> f(s, 11 - 20) -> -9
			
			int m = 11 - s.length();
			
			if (m == 4) {
				
				return 2 * m;
				
			}
			
			return m;
			// return f(s, 11 - s.length());
		}
		return n;
	}
	
	public static void main(String[] args) {
		System.out.println(f("Hase", 5));
		System.out.println(f("Bär", 18));
		System.out.println(f("Igel", 1899));
		System.out.println(f("Maus", -4));
		System.out.println(f("Maus", 4));
	}
	
}
