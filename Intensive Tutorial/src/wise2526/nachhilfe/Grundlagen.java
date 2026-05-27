package wise2526.nachhilfe;

import prog.ConsoleReader;

public class Grundlagen {
	
	public static void main(String[] args) {
	
	
	}
	
	public static void schleifen() {
		
		for (int i = 0; i < 10; i++) {
			System.out.println("Wert: " + i);
		}
		
		for (int i = 9; i >= 0; i--) {
			System.out.println("Wert: " + i);
		}
		
		for (int i = 0; i < 5; i += 5) {
			System.out.println("Wert: " + i);
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.println("Wert: " + i);
		}
		
		int i = 0;
		while (i < 10) {
			
			System.out.println("Wert: " + i);
			i++;
		}
		
	}
	
	public static void arrays() {
	
		int[] zahlen = new int[5];
		zahlen[0] = 10;
		zahlen[1] = 20;
		
		int[][][] dreidimensional = new int[3][3][3];
		dreidimensional[0][0][0] = 42;
		
		for (int i = 0; i < dreidimensional.length; i++) {
			int[][] zweidimensional = dreidimensional[i];
			
			for (int j = 0; j < zweidimensional.length; j++) {
				int[] eindimensional = zweidimensional[j];
				
				for (int k = 0; k < eindimensional.length; k++) {
					int wert = eindimensional[k];
					
					System.out.println("Wert: " + wert);
				}
			}
		}
	
	}
	
	public static void bedingungen() {
		
		int zahl = ConsoleReader.readInt();
		
		
		boolean groesserFuenf = zahl > 5;
		boolean f = false;
		
		if (groesserFuenf && !f) {
			System.out.println("Zahl ist größer als 5");
		} else if (zahl == 5) {
			System.out.println("Zahl ist gleich 5");
		} else {
			System.out.println("Zahl ist kleiner als 5");
		}
		
		
		
	}
	
	
}
