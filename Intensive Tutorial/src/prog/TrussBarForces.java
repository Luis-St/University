package prog;

import fachwerk.Fachwerk;
import fachwerk.Fachwerkstab;

import java.util.Scanner;

public class TrussBarForces {
	
	public static void main(String[] args) {
		
		System.out.println("Welches Fachwerk wollen Sie rechnen (0-4)?");
		Scanner sc = new Scanner(System.in);
		int wahl = sc.nextInt();
		
		Fachwerk fw = new Fachwerk(wahl);
		fw.berechneGroessen();
		double epsilon = 1e-6;
		
		int zugStaebe = 0;
		int druckStaebe = 0;
		int nullStaebe = 0;
		
		int maxStab = 0;
		double maxBetrag = 0.0;
		
		for (int i = 0; i < fw.gibAnzahlStaebe(); i++) {
			Fachwerkstab stab = fw.gibStab(i);
			double kraft = stab.gibStabkraft();
			
			if (kraft >= epsilon) {
				zugStaebe += 1;
			} else if (kraft <= -epsilon) {
				druckStaebe += 1;
			} else {
				nullStaebe += 1;
			}
			
			if (Math.abs(kraft) > Math.abs(maxBetrag)) {
				maxStab = i;
				maxBetrag = kraft;
			}
		}
		
		System.out.println("Zugstaebe:   " + zugStaebe);
		System.out.println("Druckstaebe: " + druckStaebe);
		System.out.println("Nullstaebe:  " + nullStaebe);
		
		System.out.println("Am staerksten beansprucht: Stab " + maxStab + " mit Betrag: " + maxBetrag);
		
		sc.close();
	}
}
