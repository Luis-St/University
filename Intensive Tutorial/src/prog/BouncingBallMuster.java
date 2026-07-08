package prog;

import java.io.FileWriter;
import java.io.IOException;

public class BouncingBallMuster {
	
	public static void main(String[] args) throws IOException {
		
		double[] fallhoehen = { 1.0, 2.0, 3.0 };
		double[] rueckprallfaktoren = { 0.5, 0.6, 0.7 };
		double epsilon = 0.01;
		
		FileWriter writer = new FileWriter("ball.txt");
		writer.write("h0 [m]\tk\tSpruenge\tGesamtstrecke [m]\n");
		
		for (int i = 0; i < fallhoehen.length; i++) {
			double h0 = fallhoehen[i];
			
			for (int j = 0; j < rueckprallfaktoren.length; j++) {
				double k = rueckprallfaktoren[j];
				
				int anzahl = 0;
				double gesamtstrecke = h0;
				double sprungHoehe = h0 * k;
				
				while (sprungHoehe >= epsilon) {
					anzahl = anzahl + 1;
					gesamtstrecke = gesamtstrecke + 2 * sprungHoehe;
					sprungHoehe = sprungHoehe * k;
				}
				
				writer.write(h0 + "\t" + k + "\t" + anzahl + "\t" + gesamtstrecke + "\n");
			}
		}
		
		writer.close();
		System.out.println("Fertig, Ergebnisse in ball.txt geschrieben.");
	}
}
