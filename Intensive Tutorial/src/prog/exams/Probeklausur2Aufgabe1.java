package prog.exams;

import java.util.Scanner;

public class Probeklausur2Aufgabe1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// Teil 1: Quersumme und Anzahl gerader Ziffern
		int m = sc.nextInt();
		
		int qsumme = 0;
		int anzG = 0;
		
		while (m > 0) {
			
			int rest = m % 10;
			qsumme = qsumme + rest;
			
			if (rest % 2 == 0) {
				anzG = anzG + 1;
			}
			
			m = m / 10;
		}
		
		System.out.println("Quersumme: " + qsumme);
		System.out.println("Gerade Ziffern: " + anzG);
		
		// Teil 2: Primzahlen bis zur Obergrenze g
		int g = sc.nextInt();
		int anzP = 0;
		
		for (int z = 2; z <= g; z++) {
			
			boolean istPrim = true;
			
			for (int t = 2; t <= z - 1; t++) {
				
				if (z % t == 0) {
					istPrim = false;
					break;
				}
			}
			
			if (istPrim) {
				System.out.println(z);
				anzP = anzP + 1;
			}
		}
		
		System.out.println("Anzahl Primzahlen: " + anzP);
		
		sc.close();
	}
}
