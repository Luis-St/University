package prog.exams;

import java.util.Scanner;

public class Probeklausur4Aufgabe1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int k = sc.nextInt();
		
		while (k < 1) {
			k = sc.nextInt();
		}
		
		int schritte = 0;
		int maxK = k;
		
		while (k != 1) {
			
			if (k % 2 == 0) {
				k = k / 2;
			} else {
				k = 3 * k + 1;
			}
			
			if (k > maxK) {
				maxK = k;
			}
			
			schritte = schritte + 1;
		}
		
		System.out.println("Schritte: " + schritte);
		System.out.println("Groesster erreichter Wert: " + maxK);
		
		int n = sc.nextInt();
		
		long grenze = 1000000;
		long fak = 1;
		
		for (int i = 2; i <= n; i++) {
			
			fak = fak * i;
			
			if (fak > grenze) {
				System.out.println("Warnung: Grenze bei i = " + i + " ueberschritten");
				break;
			}
		}
		
		System.out.println("Fakultaet: " + fak);
		
		sc.close();
	}
}
