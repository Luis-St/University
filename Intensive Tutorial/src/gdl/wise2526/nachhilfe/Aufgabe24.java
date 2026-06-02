package gdl.wise2526.nachhilfe;

public class Aufgabe24 {
	
	public static void f(String s, int a, int b) {
		for (int i = a; i < b; i++) {
			System.out.print(s.charAt(i));
			f(s, i + 1, b - 1);
			System.out.print(s.charAt(i));
		}
	}
	
	public static void g(String s) {
		f(s, 0, s.length());
		System.out.println("*");
	}
	
	public static void main(String[] args) {
		g("ABC");
		g("Hund");
	}
}
