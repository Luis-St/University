package prog.exams;

import java.io.FileWriter;
import java.io.IOException;

public class Probeklausur3Aufgabe5 {
	
	public static void main(String[] args) {
		
		double u0 = 10.0;
		double tau = 5.0;
		
		try {
			FileWriter fw = new FileWriter("entladung.txt");
			
			fw.write("t [s]\t\tU [V]\n");
			
			for (double t = 0.0; t <= 20.0; t = t + 0.5) {
				
				double u = u0 * Math.exp(-t / tau);
				
				// Wir runden auf 3 Nachkommastellen
				// Beispiel:
				// u = 1.7354
				// u * 1000.0 = 1735.4
				// Math.round(u) -> Rundet beim Komma
				// u = 1735.0
				// u / 1000.0 -> Wir stellen die Kommastellen wieder her
				// u = 1.735
				// Wir schieben um 3 Nullen nach oben
				// Java rundet für uns
				// Wir schieben um 3 Nullen nach unten
				double uGerundet = Math.round(u * 1000.0) / 1000.0;
				
				if (u < 0.37 * u0) {
					fw.write(t + "\t\t" + uGerundet + "\t\t*\n");
				} else {
					fw.write(t + "\t\t" + uGerundet + "\n");
				}
				
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
