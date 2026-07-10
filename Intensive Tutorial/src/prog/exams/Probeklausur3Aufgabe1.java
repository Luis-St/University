package prog.exams;

import java.util.Scanner;

public class Probeklausur3Aufgabe1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		double unten = sc.nextDouble();
		double oben = sc.nextDouble();
		double grenze = sc.nextDouble();
		
		double[] werte = new double[n];
		
		for (int i = 0; i < n; i++) {
			
			werte[i] = sc.nextDouble();
		}
		
		int gueltig = 0;
		double tsum = 0;
		double min = werte[0];
		double max = werte[0];
		
		for (int i = 0; i < n; i++) {
			
			if (unten <= werte[i] && werte[i] <= oben) {
				gueltig = gueltig + 1;
				tsum = tsum + werte[i];
			}
			
			if (werte[i] < min) {
				min = werte[i];
			}
			
			if (werte[i] > max) {
				max = werte[i];
			}
		}
		
		double mittel;
		
		if (gueltig > 0) {
			mittel = tsum / gueltig;
		} else {
			mittel = 0;
		}
		
		System.out.println("Gueltige Werte: " + gueltig);
		System.out.println("Mittelwert: " + mittel);
		System.out.println("Minimum: " + min);
		System.out.println("Maximum: " + max);
		
		int i = 0;
		boolean gefunden = false;
		int pos = -1;
		
		while (!gefunden && i < n) {
			
			if (Math.abs(werte[i] - mittel) > grenze) {
				gefunden = true;
				pos = i;
			}
			
			i = i + 1;
		}
		
		if (gefunden) {
			System.out.println("Erster Ausreisser an Position " + pos);
		} else {
			System.out.println("Kein Ausreisser");
		}
		
		sc.close();
	}
}
