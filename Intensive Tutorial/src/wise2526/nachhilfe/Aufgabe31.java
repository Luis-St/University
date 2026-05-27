package wise2526.nachhilfe;


public class Aufgabe31 {
	
	/*public static double f(double d, double e) {
		if (d.equals(e)) {
			return e;
		}
		return -1;
	}*/
	
}

/*public class A {
	
	public String a;
	
	private static int f(int x, String[] y) {
		String c = "Hase";
		System.out.println(a+c); // Fehler
			*//*public int g(int x) {
				return x*x;
			}*//*
		System.out.println(x + y); // Fehler
		return x + y[2].length();
	}
}

public class B {
	public static double f(double d, double e) {
		if (d.equals(e)) // Fehler
			return e;
		// Fehler
	}
	
	public static double g(String a, double b) {
		if (a.equals("Baum"))
			return b;
		else {
			String c = "Blume";
			while (c == b) { // Fehler
				System.out.println(c);
				b = b + c.length();
			}
			// Fehler
		}
	}
	
	public static void main(String[] args) {
		System.out.println(f(5.2, 6.4));
		System.out.println(g(4.7, "Maus"));
		System.out.println(A.a);
		String[] x = {"AA", "AB", "AC"};
		System.out.println(A.f(5,x));
	}
}*/
