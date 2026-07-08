package prog;

import fachwerk.Fachwerk;

import java.util.Scanner;

public class FachwerkAufgabe1 {
	
	public static void main(String[] args) {
		Fachwerk fw = null;
		System.out.println("Welches Fachwerk wollen Sie rechnen (0-4)?");
		
		int wahl = -1;
		try (Scanner sc = new Scanner(System.in)) {
			wahl = sc.nextInt();
			
			if (wahl < 0 || wahl > 4) {
				System.out.println("Ungültige Eingabe. Bitte eine Zahl zwischen 0 und 4 eingeben.");
				return;
			}
		}
		
		fw = new Fachwerk(wahl);
		double max = 0;
		int nr = 0;
		
		for (int i = 0; i < fw.gibAnzahlStaebe(); i++) {
			int anfang = fw.gibStab(i).gibKnotenNrA();
			int ende = fw.gibStab(i).gibKnotenNrE();
			
			double xAnfang = fw.gibKnoten(anfang).gibX();
			double yAnfang = fw.gibKnoten(anfang).gibY();
			double xEnde = fw.gibKnoten(ende).gibX();
			double yEnde = fw.gibKnoten(ende).gibY();
			
			double dx = xEnde - xAnfang;
			double dy = yEnde - yAnfang;
			
			double laenge = Math.sqrt(dx * dx + dy * dy);
			
			if (laenge >= max) {
				max = laenge;
				nr = i;
			}
		}
		
		System.out.println(nr + ": " + max + "m");
		
	}
}
