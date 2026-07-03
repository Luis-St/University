package gdl.sose26.ka;

public class Probeklausur6Aufgabe3 {
	
	public static void zeichne(int n) {
		if (n <= 0) {
			System.out.print(".");
		} else {
			System.out.print(n);
			zeichne(n - 1);
			System.out.print(n);
		}
	}
	
	public static void tabelle(int zeilen) {
		int i = 1;
		while (i <= zeilen) {
			int summe = 0;
			for (int j = 1; j <= i; j++) {
				summe = summe + j;
			}
			System.out.println(i + ":" + summe);
			i = i * 2;
		}
	}
	
	public static void main(String[] args) {
		for (int k = 2; k >= 0; k--) {
			zeichne(k);
			System.out.println();
		}
		
		tabelle(5);
		
		int n = 16;
		while (n > 1) {
			System.out.print(n + " ");
			n = n / 2;
		}
		
		System.out.println();
	}
	
}
