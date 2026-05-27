package wise2526.ka;

public class Aufgabe2 {
	
	public static int f(int x, boolean b) {
		System.out.println("x = " + x);
		if (b == true) {
			return g(x - 1);
		} else {
			return x;
		}
	}
	public static int g(int y) {
		System.out.println("y = " + y);
		boolean b = false;
		if (y<5) {
			b = true;
		}
		return f(y*y, b);
	}
	public static void main (String args[] ) {
		System.out.println("g(6) = " + g(6));
		System.out.println("g(5) = " + g(5));
		System.out.println("g(4) = " + g(4));
		System.out.println("g(3) = " + g(3));
		System.out.println("g(2) = " + g(2));
		System.out.println("g(1) = " + g(1));
		System.out.println("g(0) = " + g(0));
	}
	
}
