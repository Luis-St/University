package sose25.ka;

/**
 *
 * @author Luis-St
 *
 */

public class Aufgabe_30 {
	
	public static void main(String[] args) {
		
		int x[] = {
			2, 2, 2
		};
		
		int p = 0;
		
		while (p < x.length) {
			
			if (x[p] > 0) {
				
				x[p] = x[p] - 1;
				
			}
			
			for (int i = 0; i < x.length; i++) {
				
				System.out.print(x[i] + ", ");
				
			}
			
			System.out.println(p);
			p = p + x[0];
			
			if (p >= x.length) {
				
				p = 0;
				
			}
		}
	}
	
}
