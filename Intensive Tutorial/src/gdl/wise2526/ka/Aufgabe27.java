package gdl.wise2526.ka;

public class Aufgabe27 {
	
	public static void f(int n) {
		if (n <= 0)
			System.out.print("-");
		else {
			f(n - 2);
			System.out.print("" + n);
			f(n - 1);
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			f(i);
			System.out.println();
		}
	}
	
}
