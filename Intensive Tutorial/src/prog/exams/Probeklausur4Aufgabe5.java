package prog.exams;

import java.io.FileWriter;
import java.io.IOException;

public class Probeklausur4Aufgabe5 {
	
	public static void main(String[] args) {
		
		double m = 3.0;
		double a = 2.0;
		double eGrenze = 100.0;
		
		try {
			FileWriter fw = new FileWriter("energie.txt");
			
			fw.write("t [s]\tv [m/s]\tE [J]\n");
			
			double tGrenze = -1.0;
			
			for (double t = 0.0; t <= 10.0; t = t + 0.5) {
				
				double v = a * t;
				double e = 0.5 * m * (v * v);
				
				fw.write(t + "\t" + v + "\t" + e + "\n");
				
				if (e > eGrenze && tGrenze < 0) {
					tGrenze = t;
				}
			}
			
			if (tGrenze < 0) {
				fw.write("Grenze von " + eGrenze + " J wurde nie ueberschritten\n");
			} else {
				fw.write("Grenze von " + eGrenze + " J erstmals ueberschritten bei t = " + tGrenze + " s\n");
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
