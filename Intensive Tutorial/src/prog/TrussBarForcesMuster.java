package prog;

import fachwerk.Fachwerk;
import fachwerk.Fachwerkstab;

import java.util.Scanner;

public class TrussBarForcesMuster {
	
	public static void main(String[] args) {
		
		System.out.println("Welches Fachwerk wollen Sie rechnen (0-4)?");
		Scanner sc = new Scanner(System.in);
		int wahl = sc.nextInt();
		
		Fachwerk fw = new Fachwerk(wahl);
		fw.berechneGroessen();
		double epsilon = 1e-6;
		
		int zug = 0;
		int druck = 0;
		int nullstaebe = 0;
		
		double maxBetrag = -1.0;
		int maxNr = -1;
		
		for (int i = 0; i < fw.gibAnzahlStaebe(); i++) {
			Fachwerkstab stab = fw.gibStab(i);
			double kraft = stab.gibStabkraft();
			
			if (kraft > epsilon) {
				zug = zug + 1;
			} else if (kraft < -epsilon) {
				druck = druck + 1;
			} else {
				nullstaebe = nullstaebe + 1;
			}
			
			if (Math.abs(kraft) > maxBetrag) {
				maxBetrag = Math.abs(kraft);
				maxNr = i;
			}
		}
		
		System.out.println("Zugstaebe:   " + zug);
		System.out.println("Druckstaebe: " + druck);
		System.out.println("Nullstaebe:  " + nullstaebe);
		
		Fachwerkstab maxStab = fw.gibStab(maxNr);
		System.out.println("Am staerksten beansprucht: Stab " + maxNr + " (Knoten " + maxStab.gibKnotenNrA() + " -> " + maxStab.gibKnotenNrE() + ")" + " mit " + maxStab.gibStabkraft());
	}
}
