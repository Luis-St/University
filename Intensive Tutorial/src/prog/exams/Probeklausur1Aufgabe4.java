package prog.exams;

import fachwerk.Fachwerk;
import fachwerk.Fachwerkstab;

import java.util.Scanner;

public class Probeklausur1Aufgabe4 {
	
	public static void main(String[] args) {
		
		System.out.println("Welches Fachwerk wollen Sie rechnen (0-4)?");
		Scanner sc = new Scanner(System.in);
		int wahl = sc.nextInt();
		
		Fachwerk fw = new Fachwerk(wahl);
		fw.berechneGroessen();
		double epsilon = 1.0e-6;
		
		int zug = 0;
		int druck = 0;
		int nullstaebe = 0;
		
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
		}
		
		System.out.println("Zugstaebe:   " + zug);
		System.out.println("Druckstaebe: " + druck);
		System.out.println("Nullstaebe:  " + nullstaebe);
		
		sc.close();
	}
}
