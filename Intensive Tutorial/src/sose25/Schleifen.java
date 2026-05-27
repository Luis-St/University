package sose25;

import prog.ConsoleReader;

public class Schleifen {
	
	public static void main(String[] args) {
		
		int x = ConsoleReader.readInt();
		
		while (x < 18) {
			
			System.out.print("Hallo aus der Schleife");
			
			x = ConsoleReader.readInt();
			
		}
		
		
		
	}
	
	
	public static void zaehlenMitWhile() {
		
		
		int x = 0;
		
		while (x < 10) {
			
			// Programmteil der x mal Ausgeführt werden soll
			System.out.print("Wert von x: " + x);
			// Hier als Beispiel die Ausgabe von x
			
			x = x + 1;
			
		}
		
	}
	
	
	public static void zaehlenMitFor() {
		
		
		for (int x = 0; x < 10; x = x + 1) {
			
			// Programmteil der x mal Ausgeführt werden soll
			System.out.print("Wert von x: " + x);
			// Hier als Beispiel die Ausgabe von x
			
		}
		
	}
	
	public static void zaehlenMitForRueckfuehrung1() {
		
		
		int x = 0;
		
		for (; x < 10; x = x + 1) {
			
			// Programmteil der x mal Ausgeführt werden soll
			System.out.print("Wert von x: " + x);
			// Hier als Beispiel die Ausgabe von x
			
		}
		
	}
	
	public static void zaehlenMitForRueckfuehrung2() {
		
		
		for (;;) {
			
			System.out.print("Hallo aus der Schleife");
			
		}
		
	}
	
	
}
