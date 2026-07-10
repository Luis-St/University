package prog.exams;

import java.util.Scanner;

public class Probeklausur1Aufgabe1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		double[] werte = new double[n];
		
		for (int i = 0; i < n; i++) {
			
			double wert = sc.nextDouble();
			werte[i] = wert;
			
		}
		
		double summe = 0;
		double max = werte[0];
		
		for (int i = 0; i < n; i++) {
			
			if (werte[i] > max) {
				
				max = werte[i];
				
			}
			
			summe = summe + werte[i];
		}
		
		double mittel = summe / n;
		
		for (int i = 0; i < n; i++) {
			
			if (werte[i] < mittel) {
				
				System.out.println("Wert " + werte[i] + " unter " + mittel);
				
			} else {
				
				System.out.println("Wert " + werte[i] + " über " + mittel);
				
			}
			
		}
		
		sc.close();
	}
}
