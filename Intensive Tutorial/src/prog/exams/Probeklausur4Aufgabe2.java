package prog.exams;

import java.util.Scanner;

public class Probeklausur4Aufgabe2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Gesuchte Groesse (1=v, 2=s, 3=a, 4=t): ");
		int wahl = sc.nextInt();
		
		if (wahl == 1) {
			
			System.out.println("v0 [m/s]: ");
			double v0 = sc.nextDouble();
			System.out.println("a [m/s^2]: ");
			double a = sc.nextDouble();
			System.out.println("t [s]: ");
			double t = sc.nextDouble();
			
			double v = v0 + a * t;
			System.out.println("Geschwindigkeit v = " + v + " m/s");
			
		} else if (wahl == 2) {
			
			System.out.println("v0 [m/s]: ");
			double v0 = sc.nextDouble();
			System.out.println("a [m/s^2]: ");
			double a = sc.nextDouble();
			System.out.println("t [s]: ");
			double t = sc.nextDouble();
			
			double s = v0 * t + 0.5 * a * t * t;
			System.out.println("Strecke s = " + s + " m");
			
		} else if (wahl == 3) {
			
			System.out.println("v [m/s]: ");
			double v = sc.nextDouble();
			System.out.println("v0 [m/s]: ");
			double v0 = sc.nextDouble();
			System.out.println("t [s]: ");
			double t = sc.nextDouble();
			
			if (t == 0) {
				System.out.println("Ungueltige Eingabe: t darf nicht 0 sein");
			} else {
				double a = (v - v0) / t;
				System.out.println("Beschleunigung a = " + a + " m/s^2");
			}
			
		} else if (wahl == 4) {
			
			System.out.println("v [m/s]: ");
			double v = sc.nextDouble();
			System.out.println("v0 [m/s]: ");
			double v0 = sc.nextDouble();
			System.out.println("a [m/s^2]: ");
			double a = sc.nextDouble();
			
			if (a == 0) {
				System.out.println("Ungueltige Eingabe: a darf nicht 0 sein");
			} else {
				double t = (v - v0) / a;
				System.out.println("Zeit t = " + t + " s");
			}
			
		} else {
			System.out.println("Ungueltige Auswahl");
		}
		
		sc.close();
	}
}
