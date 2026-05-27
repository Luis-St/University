package wise2526.ka;

public class Aufgabe5 {
	
	public static void g(boolean x[], int n) {
	
		for (int i = 0; i < n; i++) {
		
			f(x);
		
		}
	
	}
	
	public static void f(boolean x[]) {
		
		for (int i = 0; i < x.length; i++) {
		
			if (x[i] == true) {
				
				if (i > 0) {
					
					x[i - 1] = true;
					
				}
				
				if (i < x.length - 1) {
					
					if (x[i + 1] == false) {
						
						x[i + 1] = true;
						
						i++;
						
					}
					
				}
			}
		
		}
		
	}
	
	public static void f1(boolean x[]) {
		
		boolean[] orginal = new boolean[x.length];
		for (int i = 0; i < x.length; i++) {
			orginal[i] = x[i];
		}
		
		for (int i = 0; i < x.length; i++) {
			
			if (orginal[i] == true) {
				
				if (i > 0) {
					
					x[i - 1] = true;
					
				}
				
				if (i < x.length - 1) {
					
					x[i + 1] = true;
					
				}
			}
			
		}
		
		
	}
	
}
