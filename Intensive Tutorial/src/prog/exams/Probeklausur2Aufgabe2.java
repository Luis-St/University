package prog.exams;

import java.util.Scanner;

public class Probeklausur2Aufgabe2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Energieform (1=kinetisch, 2=potentiell, 3=Feder): ");
		int form = sc.nextInt();
		
		if (form == 1) {
			
			System.out.println("Masse m [kg]: ");
			double m = sc.nextDouble();
			System.out.println("Geschwindigkeit v [m/s]: ");
			double v = sc.nextDouble();
			
			if (m <= 0) {
				System.out.println("Ungueltige Eingabe: Masse muss groesser 0 sein");
			} else {
				double e = 0.5 * m * v * v;
				System.out.println("Energie E = " + e + " J");
			}
			
		} else if (form == 2) {
			
			System.out.println("Masse m [kg]: ");
			double m = sc.nextDouble();
			System.out.println("Hoehe h [m]: ");
			double h = sc.nextDouble();
			
			if (m <= 0) {
				System.out.println("Ungueltige Eingabe: Masse muss groesser 0 sein");
			} else {
				double g = 9.81;
				double e = m * g * h;
				System.out.println("Energie E = " + e + " J");
			}
			
		} else if (form == 3) {
			
			System.out.println("Federkonstante D [N/m]: ");
			double d = sc.nextDouble();
			System.out.println("Auslenkung s [m]: ");
			double s = sc.nextDouble();
			
			if (d <= 0) {
				System.out.println("Ungueltige Eingabe: Federkonstante muss groesser 0 sein");
			} else {
				double e = 0.5 * d * s * s;
				System.out.println("Energie E = " + e + " J");
			}
			
		} else {
			System.out.println("Ungueltige Energieform");
		}
		
		sc.close();
	}
}
