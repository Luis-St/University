package prog;

import java.util.Scanner;

public class BeamSupportForces {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Geben Sie F ein: ");
		double f = sc.nextDouble();
		
		System.out.print("Geben Sie a ein: ");
		double a = sc.nextDouble();
		
		System.out.print("Geben Sie L ein: ");
		double l = sc.nextDouble();
		
		if (f < 0 || a < 0 || l < 0 /*l <= 0*/) {
			System.out.println("Fehler: Alle Werte müssen positiv sein");
		} else {
			
			if (l == 0) {
				System.out.println("Fehler: L darf nicht ");
			} else {
				
				if (a > l) {
					System.out.println("Fehler: a muss kleiner als L sein");
				} else {
					double fb = f * (a / l);
					double fa = f * ((l - a) / l);
					
					System.out.println("FB: " + fb);
					System.out.println("FA: " + fa);
				}
				
			}
			
		}
	}
}
