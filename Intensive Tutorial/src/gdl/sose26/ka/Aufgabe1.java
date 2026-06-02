package gdl.sose26.ka;

public class Aufgabe1 {

	public static void main(String[] args) {
		int[] a = { 1, 5, 1, 7, 3 };
		int b = 9;
		System.out.println(f(a, b));
	}

	public static int f(int[] a, int b) {
		
		for (int i = 0; i < a.length - 1; i++) {
			
			int aktuellerIndex = i;
			int naechsterIndex = aktuellerIndex + 1;
			
			if (a[aktuellerIndex] + a[naechsterIndex] == b) {
				return i;
			}
		}
		
		return -1;
	}

}
