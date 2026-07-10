package prog.exams;

import java.io.FileWriter;
import java.io.IOException;

public class Probeklausur1Aufgabe5 {
	
	public static void main(String[] args) {
		
		double a = 9.81;
		
		try {
			FileWriter fw = new FileWriter("fall.txt");
			
			for (int t = 0; t <= 15; t++) {
				
				double s = 0.5 * a * (t * t);
				
				fw.write("t = " + t + " s\ts = " + s + " m\n");
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
