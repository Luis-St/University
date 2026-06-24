package gdl.sose26.nachhilfe;

/**
 *
 * @author Luis-St
 *
 */

public class Probe1Aufgabe3 {
	
	public static void f(int n) {
		if (n <= 0) {
			return;
		}
		
		System.out.print(n + " ");
		f(n - 1);
		System.out.print(n + " ");
	}
	
	public static void main(String[] args) {
		for (int i = 1; i <= 3; i++) {
			f(i);
			System.out.println();
		}
	}
	
}
