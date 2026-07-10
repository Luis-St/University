package prog.exams;

import fachwerk.Fachwerk;
import fachwerk.Knoten;

import java.util.Scanner;

public class Probeklausur5Aufgabe4 {
	
	public static void main(String[] args) {
		
		System.out.println("Welches Fachwerk wollen Sie rechnen (0-4)?");
		Scanner sc = new Scanner(System.in);
		int wahl = sc.nextInt();
		
		Fachwerk fw = new Fachwerk(wahl);
		fw.berechneGroessen();
		double epsilon = 1.0e-6;
		
		// Teil 1: rein vertikal verschobene Knoten
		boolean einerGefunden = false;
		
		for (int i = 0; i < fw.gibAnzahlKnoten(); i++) {
			
			Knoten knoten = fw.gibKnoten(i);
			double vx = knoten.gibVerschiebungX();
			double vy = knoten.gibVerschiebungY();
			
			if (Math.abs(vx) < epsilon && Math.abs(vy) >= epsilon) {
				System.out.println("Knoten " + i + " verschiebt sich rein vertikal");
				einerGefunden = true;
			}
		}
		
		if (!einerGefunden) {
			System.out.println("Kein Knoten verschiebt sich rein vertikal");
		}
		
		// Teil 2: größte Gesamtverschiebung
		double maxV = -1.0;
		int maxNr = -1;
		
		for (int i = 0; i < fw.gibAnzahlKnoten(); i++) {
			
			Knoten knoten = fw.gibKnoten(i);
			double vx = knoten.gibVerschiebungX();
			double vy = knoten.gibVerschiebungY();
			
			double v = Math.sqrt(vx * vx + vy * vy);
			
			if (v > maxV) {
				maxV = v;
				maxNr = i;
			}
		}
		
		System.out.println("Groesste Gesamtverschiebung: Knoten " + maxNr + " mit " + maxV);
		
		sc.close();
	}
}
