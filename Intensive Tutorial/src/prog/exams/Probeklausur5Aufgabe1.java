package prog.exams;

import java.util.Scanner;

public class Probeklausur5Aufgabe1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] werte = new int[n];
		
		for (int i = 0; i < n; i++) {
			
			werte[i] = sc.nextInt();
		}
		
		int anzAbund = 0;
		
		for (int i = 0; i < n; i++) {
			
			int tsum = 0;
			
			for (int t = 1; t <= werte[i] / 2; t++) {
				
				if (werte[i] % t == 0) {
					tsum = tsum + t;
				}
			}
			
			if (tsum == werte[i]) {
				System.out.println(werte[i] + " ist vollkommen");
			}
			
			if (tsum > werte[i]) {
				anzAbund = anzAbund + 1;
			}
		}
		
		System.out.println("Abundante Zahlen: " + anzAbund);
		
		// Teil 2: erste Primzahl im Feld suchen
		int i = 0;
		boolean primGefunden = false;
		int primPos = -1;
		
		while (!primGefunden && i < n) {
			
			boolean istPrim = (werte[i] >= 2);
			
			for (int t = 2; t <= werte[i] - 1; t++) {
				
				if (werte[i] % t == 0) {
					istPrim = false;
					break;
				}
			}
			
			if (istPrim) {
				primGefunden = true;
				primPos = i;
			}
			
			i = i + 1;
		}
		
		if (primGefunden) {
			System.out.println("Erste Primzahl an Position " + primPos);
		} else {
			System.out.println("Keine Primzahl");
		}
		
		sc.close();
	}
}
