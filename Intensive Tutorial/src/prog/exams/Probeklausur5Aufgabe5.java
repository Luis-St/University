package prog.exams;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Probeklausur5Aufgabe5 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double g = 9.81;
		
		System.out.println("Anfangsgeschwindigkeit v0 [m/s]: ");
		double v0 = sc.nextDouble();
		System.out.println("Winkel alpha [Grad]: ");
		double alpha = sc.nextDouble();
		
		double alphaRad = Math.toRadians(alpha);
		
		try {
			FileWriter fw = new FileWriter("bahn.txt");
			
			fw.write("t [s]\tx [m]\ty [m]\n");
			
			double t = 0.0;
			double x = 0.0;
			double y = 0.0;
			
			boolean aufprall = false;
			
			while (!aufprall) {
				
				x = v0 * Math.cos(alphaRad) * t;
				y = v0 * Math.sin(alphaRad) * t - 0.5 * g * t * t;
				
				if (y < 0) {
					aufprall = true;
				} else {
					fw.write(t + "\t" + x + "\t" + y + "\n");
					t = t + 0.1;
				}
			}
			
			fw.write("Aufprall bei t = " + t + " s, Wurfweite = " + x + " m\n");
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sc.close();
	}
}
