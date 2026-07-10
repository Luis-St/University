package prog.exams;

import java.io.FileWriter;

public class Probeklausur2Aufgabe5 {
	
	public static void main(String[] args) {
		
		double a = 2.5;
		
		try {
			FileWriter fw = new FileWriter("bewegung.txt");
			
			fw.write("t [s] \tv [m/s] \ts [m]\n");
			
			for (int t = 0; t <= 12; t++) {
				
				double v = a * t;
				double s = 0.5 * a * (t * t);
				
				fw.write(t + "\t\t" + v + "\t\t" + s + "\n");
			}
			
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
