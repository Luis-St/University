package gdl.wise2526.nachhilfe;

public class Aufgabe19 {
	
	public static void main(String[] args) {
		
		
		
		
	}
	
	public static void f(int[] x) {
		
		for (int i = 0; i < x.length; i += 2) {
		
			if (i + 1 == x.length) {
			
				x[i] = 0;
			
			} else {
				
				if (x[i] == 0 || x[i + 1] == 0) {
				
					// Kein tauschen
				
				} else {
				
					int speichern = x[i];
					x[i] = x[i + 1];
					x[i + 1] = speichern;
				
				}
			
			}
		
			
		}
		
		
		
	}
}
