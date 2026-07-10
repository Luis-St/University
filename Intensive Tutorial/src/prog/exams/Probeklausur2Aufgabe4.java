package prog.exams;

import fachwerk.Fachwerk;
import fachwerk.Fachwerkstab;

import java.util.Scanner;

public class Probeklausur2Aufgabe4 {
	
	public static void main(String[] args) {
		
		System.out.println("Welches Fachwerk wollen Sie rechnen (0-4)?");
		Scanner sc = new Scanner(System.in);
		int wahl = sc.nextInt();
		
		Fachwerk fw = new Fachwerk(wahl);
		fw.berechneGroessen();
		
		double maxZug = 0.0;
		int maxZugNr = -1;
		
		double maxDruck = 0.0;
		int maxDruckNr = -1;
		
		for (int i = 0; i < fw.gibAnzahlStaebe(); i++) {
			
			Fachwerkstab stab = fw.gibStab(i);
			double kraft = stab.gibStabkraft();
			
			if (kraft > maxZug) {
				maxZug = kraft;
				maxZugNr = i;
			}
			
			if (kraft < maxDruck) {
				maxDruck = kraft;
				maxDruckNr = i;
			}
		}
		
		System.out.println("Groesste Zugkraft   (Stab " + maxZugNr + "): " + maxZug);
		System.out.println("Groesste Druckkraft (Stab " + maxDruckNr + "): " + maxDruck);
		
		sc.close();
	}
}
