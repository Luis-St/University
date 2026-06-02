package gdl.sose26.ka;

public class Aufgabe6 {
	
	public static int func(String s, int n) {
		if (n==4)
			return 2*n;
		if (n>14)
			return func(s,11 - s.length());
		return n;
	}
	
	public static void main(String[] args) {
		System.out.println(func("Hase",5));    // 5
		System.out.println(func("Bär",18));    // 8
		System.out.println(func("Igel",1899)); // 7
		System.out.println(func("Maus",-4));   // -4
		System.out.println(func("Maus",4));    // 8
	}
}
