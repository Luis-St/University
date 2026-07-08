package prog;

import java.io.FileWriter;
import java.io.IOException;

public class BouncingBall {
	
	public static void main(String[] args) {
		
		double[] fallhoehen = { 1.0, 2.0, 3.0 };
		double[] rueckprallfaktoren = { 0.5, 0.6, 0.7 };
		double epsilon = 0.01;
		
		try {
			FileWriter writer = new FileWriter("ball.txt");
			writer.write("h0 [m]\tk\tSprünge\tGesamtstrecke [m]\n");
			
			for (int i = 0; i < fallhoehen.length; i++) {
				double h0 = fallhoehen[i];
				
				for (int j = 0; j < rueckprallfaktoren.length; j++) {
					double k = rueckprallfaktoren[j];
					
					double gesamtstrecke = 0.0;
					int anzahl = 0;
					double sprungHoehe = h0;
					
					while (sprungHoehe >= epsilon) {
						anzahl = anzahl + 1;
						sprungHoehe = sprungHoehe * k;
						gesamtstrecke = gesamtstrecke + 2 * sprungHoehe;
					}
					
					writer.write(h0 + "\t" + k + "\t" + anzahl + "\t" + gesamtstrecke + "\n");
				}
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
