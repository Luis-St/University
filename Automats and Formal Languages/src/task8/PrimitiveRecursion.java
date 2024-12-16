package task8;

/**
 *
 * @author Luis-St
 *
 */

public class PrimitiveRecursion {
	
	public static void main(String[] args) {
		System.out.println(multiply(5, 3)); // 15
		System.out.println(difference(3, 5)); // 0
		System.out.println(absoluteDifference(3, 5)); // 2
		System.out.println(isOdd(5)); // 1
		System.out.println(isOdd(4)); // 0
		System.out.println(min(3, 5));
		System.out.println(min(5, 3));
	}
	
	/**
	 * Predecessor function.<br>
	 * Decreases the value of n by 1.<br>
	 * Defined as:<br>
	 * <pre>{@code
	 * pred(0) = 0
	 * pred(n) = n - 1, n > 0
	 * }</pre>
	 * @param n The value to decrease by 1
	 * @return The value of n - 1
	 */
	private static int pred(int n) {
		if (n == 0) {
			return 0;
		} else {
			return n - 1;
		}
	}
	
	/**
	 * Sign function.<br>
	 * Returns 0 if n is 0, otherwise returns the predecessor of the sign of n - 1.<br>
	 * Defined as:<br>
	 * <pre>{@code
	 * sign(0) = 0
	 * sign(n + 1) = pred(sign(n)) + 1
	 * }</pre>
	 * @param n The value to check the sign of
	 * @return 0 if n is 0, otherwise the predecessor of the sign of n - 1
	 */
	private static int sign(int n) {
		if (n == 0) {
			return 0;
		} else {
			return pred(sign(n - 1)) + 1;
		}
	}
	
	/**
	 * Primitive recursive multiplication function.<br>
	 * Multiplies a by b.<br>
	 * Defined as:<br>
	 * <pre>{@code
	 * multiply(0, b) = 0
	 * multiply(a + 1, b) = multiply(a - 1, b) + b
	 * }</pre>
	 * @param a The first value to multiply
	 * @param b The second value to multiply
	 * @return The product of a and b
	 */
	public static int multiply(int a, int b) {
		if (a == 0) {
			return 0;
		} else {
			return multiply(a - 1, b) + b;
		}
	}
	
	/**
	 * Primitive recursive difference function.<br>
	 * Subtracts b from a, if the result is negative, returns 0.<br>
	 * Defined as:<br>
	 * <pre>{@code
	 * difference(a, 0) = a
	 * difference(a, b + 1) = pred(difference(a, b))
	 * }</pre>
	 * @param a The value to subtract from
	 * @param b The value to subtract
	 * @return The difference of a and b or 0 if the result is negative
	 */
	public static int difference(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return pred(difference(a, b - 1));
		}
	}
	
	/**
	 * Primitive recursive absolute difference function.<br>
	 * Returns the absolute difference between a and b.<br>
	 * Defined as:<br>
	 * <pre>{@code
	 * absoluteDifference(a, b) = difference(a, b) + difference(b, a)
	 * }</pre>
	 * @param a The first value
	 * @param b The second value
	 * @return The absolute difference between a and b
	 */
	public static int absoluteDifference(int a, int b) {
		return difference(a, b) + difference(b, a);
	}
	
	/**
	 * Primitive recursive is odd function.<br>
	 * Returns 1 if n is odd, 0 if n is even.<br>
	 * Defined as:<br>
	 * <pre>{@code
	 * isOdd(0) = 0
	 * isOdd(n + 1) = 1 - isOdd(n)
	 * }</pre>
	 * @param n The value to check if it is odd
	 * @return 1 if n is odd, 0 if n is even
	 */
	public static int isOdd(int n) {
		if (n == 0) {
			return 0;
		} else {
			return 1 - isOdd(n - 1);
		}
	}
	
	/**
	 * Primitive recursive min function.<br>
	 * Returns the minimum of a and b.<br>
	 * Defined as:<br>
	 * <pre>{@code
	 * min(a, b) = multiply(b, sign(difference(a, b))) + multiply(a, sign(difference(b, a)))
	 * }</pre>
	 * @param a The first value
	 * @param b The second value
	 * @return The minimum of a and b
	 */
	public static int min(int a, int b) {
		return multiply(b, sign(difference(a, b))) + multiply(a, sign(difference(b, a)));
	}
}
