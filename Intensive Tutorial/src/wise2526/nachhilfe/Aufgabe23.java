package wise2526.nachhilfe;

public class Aufgabe23 {
	
	public static void main(String[] args) {
	
		int[] a = { 4, 5, 8, 3, 7 };
		
		f(a);
	
	}
	
	// [
	// 4
	// 5
	// 0
	// 1
	// 2
	// 7
	// 9
	// ]
	
	// [ 4, 5, 8, 3, 7 ]
	// [ 5, 2, 8, 1 ]
	// [ 1, 2, 3, 4, 5 ]
	// [ 7 ]
	// [ ]
	
	public static void f(int[] a) {
		
		for (int i = 1; i < a.length; i++) {
			
			int erstesElement = a[0];
			int aktuellesElement = a[i];
			
			if (aktuellesElement < erstesElement) {
			
				a[i] = a[0];
				a[0] = aktuellesElement;
				
				i = a.length;
			}
		
		}
		
	}
	
}
