package gdl.sose25.ka;

/**
 *
 * @author Luis-St
 *
 */

public class Falkultät {
	
	public static void main(String[] args) {
		
		System.out.println(f(5));
		
		// 0! = 1
		// 1! = 1
		// 2! = 2 * 1
		// 3! = 3 * 2 * 1
		
		// factorial(5)
		// 5 * factorial(4)
		//     4 * factorial(3)
		//         3 * factorial(2)
		//             2 * factorial(1)
		//                 1
		
	}
	
	public static int factorial(int n) {
		if (n == 0 || n == 1) {
			return 1;
		}
		
		return n * factorial(n - 1);
	}
	
	public static int f(int n) {
		if (n == 0 || n == 1) {
			return 1;
		}
		
		int factorial = n;
		
		System.out.print(factorial);
		
		for (int i = n - 1; i > 0; i--) {
			System.out.print(" * " + i);
			
			factorial = factorial * i;
		
		}
		System.out.println(" = " + factorial);
		
		return factorial;
		//return n * factorial(n - 1);
	}
	
}
