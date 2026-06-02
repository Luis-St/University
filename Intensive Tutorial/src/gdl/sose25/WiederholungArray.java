package gdl.sose25;

import static prog.ConsoleReader.*;

public class WiederholungArray {
	
	public static void main(String[] args) {
		
		double[] noten = new double[5];
		
		
		noten[0] = readDouble("Note von Student 1 eingeben: ");
		
		noten[1] = readDouble("Note von Student 2 eingeben: ");
		
		noten[2] = readDouble("Note von Student 3 eingeben: ");
		
		noten[3] = readDouble("Note von Student 4 eingeben: ");
		
		noten[4] = readDouble("Note von Student 5 eingeben: ");
		
		
		double gesamt = noten[0] + noten[1] + noten[2] + noten[3] + noten[4];
		
		
		double durchschnitt = gesamt / 5;
		
		System.out.println("Noten durchschnitt der fünf Studenten: " + durchschnitt);
		
	}
	
	
	public static void direkteDeklaration() {
		
		
		String[] studenten = new String[] {
			"Luis",
			"Antonia",
			"Richard",
			"Dana"
		};
		
		
		int[] laengeNamen = new int[] {
			4, // "Luis"
			7, // "Antonia"
			7, // "Richard"
			4  // "Dana"
		};
		
		
		
	}
	
}
