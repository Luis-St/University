package prog.exams;

import fachwerk.Fachwerk;
import fachwerk.Fachwerkstab;

import java.util.Scanner;

public class Probeklausur4Aufgabe4 {
	
	public static void main(String[] args) {
		
		System.out.println("Welches Fachwerk wollen Sie rechnen (0-4)?");
		Scanner sc = new Scanner(System.in);
		int wahl = sc.nextInt();
		
		Fachwerk fw = new Fachwerk(wahl);
		
		double maxLaenge = -1.0;
		int maxNr = -1;
		
		double minLaenge = -1.0;
		int minNr = -1;
		
		double gesamtLaenge = 0.0;
		
		for (int i = 0; i < fw.gibAnzahlStaebe(); i++) {
			
			Fachwerkstab stab = fw.gibStab(i);
			
			int a = stab.gibKnotenNrA();
			int e = stab.gibKnotenNrE();
			
			double dx = fw.gibKnoten(e).gibX() - fw.gibKnoten(a).gibX();
			double dy = fw.gibKnoten(e).gibY() - fw.gibKnoten(a).gibY();
			
			double laenge = Math.sqrt(dx * dx + dy * dy);
			
			gesamtLaenge = gesamtLaenge + laenge;
			
			if (i == 0 || laenge > maxLaenge) {
				maxLaenge = laenge;
				maxNr = i;
			}
			
			if (i == 0 || laenge < minLaenge) {
				minLaenge = laenge;
				minNr = i;
			}
		}
		
		System.out.println("Laengster Stab:  Stab " + maxNr + " mit Laenge " + maxLaenge);
		System.out.println("Kuerzester Stab: Stab " + minNr + " mit Laenge " + minLaenge);
		System.out.println("Gesamtlaenge aller Staebe: " + gesamtLaenge);
		
		sc.close();
	}
}
