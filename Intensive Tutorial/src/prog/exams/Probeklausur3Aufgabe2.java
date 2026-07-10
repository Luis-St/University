package prog.exams;

import java.util.Scanner;

public class Probeklausur3Aufgabe2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Schaltung (1=Reihe, 2=Parallel): ");
		int schaltung = sc.nextInt();
		
		double r = 0;
		boolean gueltig = true;
		
		if (schaltung == 1) {
			
			System.out.println("R1 [Ohm]: ");
			double r1 = sc.nextDouble();
			System.out.println("R2 [Ohm]: ");
			double r2 = sc.nextDouble();
			System.out.println("R3 [Ohm]: ");
			double r3 = sc.nextDouble();
			
			r = r1 + r2 + r3;
			
		} else if (schaltung == 2) {
			
			System.out.println("R1 [Ohm]: ");
			double r1 = sc.nextDouble();
			System.out.println("R2 [Ohm]: ");
			double r2 = sc.nextDouble();
			
			// Hier darf der Nenner nicht 0 werden
			if (r1 + r2 == 0) {
				gueltig = false;
			} else {
				r = (r1 * r2) / (r1 + r2);
			}
			
		} else {
			gueltig = false;
			System.out.println("Ungueltige Schaltungsart");
		}
		
		if (gueltig) {
			
			System.out.println("Gesamtwiderstand R = " + r + " Ohm");
			
			if (r == 0) {
				System.out.println("Kurzschluss: Strom kann nicht berechnet werden");
			} else {
				System.out.println("Spannung U [V]: ");
				double u = sc.nextDouble();
				
				double i = u / r;
				System.out.println("Strom I = " + i + " A");
			}
		}
		
		sc.close();
	}
}
