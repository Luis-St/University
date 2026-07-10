package prog.exams;

import java.util.Scanner;

public class Probeklausur1Aufgabe2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Bewegungsart (1=gleichfoermig, 2=beschleunigt, 3=mit v0): ");
		int art = sc.nextInt();
		
		if (art == 1) {
			
			System.out.println("Geschwindigkeit v [m/s]: ");
			double v = sc.nextDouble();
			System.out.println("Zeit t [s]: ");
			double t = sc.nextDouble();
			
			if (t < 0) {
				System.out.println("Ungueltige Eingabe: negative Zeit");
			} else {
				double s = v * t;
				System.out.println("Zurueckgelegte Strecke s = " + s + " m");
			}
			
		} else if (art == 2) {
			
			System.out.println("Beschleunigung a [m/s^2]: ");
			double a = sc.nextDouble();
			System.out.println("Zeit t [s]: ");
			double t = sc.nextDouble();
			
			if (t < 0) {
				System.out.println("Ungueltige Eingabe: negative Zeit");
			} else {
				double s = 0.5 * a * t * t;
				System.out.println("Zurueckgelegte Strecke s = " + s + " m");
			}
			
		} else if (art == 3) {
			
			System.out.println("Anfangsgeschwindigkeit v0 [m/s]: ");
			double v0 = sc.nextDouble();
			System.out.println("Beschleunigung a [m/s^2]: ");
			double a = sc.nextDouble();
			System.out.println("Zeit t [s]: ");
			double t = sc.nextDouble();
			
			if (t < 0) {
				System.out.println("Ungueltige Eingabe: negative Zeit");
			} else {
				double s = v0 * t + 0.5 * a * t * t;
				System.out.println("Zurueckgelegte Strecke s = " + s + " m");
			}
			
		} else {
			System.out.println("Ungueltige Bewegungsart");
		}
		
		sc.close();
	}
}
