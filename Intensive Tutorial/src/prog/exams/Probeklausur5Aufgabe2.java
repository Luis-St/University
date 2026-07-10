package prog.exams;

import java.util.Scanner;

public class Probeklausur5Aufgabe2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double g = 9.81;
		
		System.out.println("Gesuchte Groesse (1=x, 2=y, 3=Steigzeit, 4=Wurfweite): ");
		int wahl = sc.nextInt();
		
		System.out.println("Anfangsgeschwindigkeit v0 [m/s]: ");
		double v0 = sc.nextDouble();
		System.out.println("Winkel alpha [Grad]: ");
		double alpha = sc.nextDouble();
		
		// Math.sin und Math.cos erwarten das Bogenmaß
		double alphaRad = Math.toRadians(alpha);
		
		if (wahl == 1) {
			
			System.out.println("Zeit t [s]: ");
			double t = sc.nextDouble();
			
			if (alpha == 90) {
				System.out.println("Horizontalweg x = 0.0 m");
			} else {
				double x = v0 * Math.cos(alphaRad) * t;
				System.out.println("Horizontalweg x = " + x + " m");
			}
			
		} else if (wahl == 2) {
			
			System.out.println("Zeit t [s]: ");
			double t = sc.nextDouble();
			
			double y = v0 * Math.sin(alphaRad) * t - 0.5 * g * t * t;
			System.out.println("Hoehe y = " + y + " m");
			
		} else if (wahl == 3) {
			
			if (alpha == 0) {
				System.out.println("Steigzeit ts = 0.0 s");
			} else {
				double ts = (v0 * Math.sin(alphaRad)) / g;
				System.out.println("Steigzeit ts = " + ts + " s");
			}
			
		} else if (wahl == 4) {
			
			if (alpha == 90) {
				System.out.println("Wurfweite w = 0.0 m");
			} else {
				double w = (v0 * v0 * Math.sin(2 * alphaRad)) / g;
				System.out.println("Wurfweite w = " + w + " m");
			}
			
		} else {
			System.out.println("Ungueltige Auswahl");
		}
		
		sc.close();
	}
}
