package net.luis.sheet2;

import net.luis.sheet2.Fraction;

import static net.luis.util.ConsoleColors.*;

/**
 *
 * @author Luis-St
 *
 */

public class Main {
	
	public static void main(String[] args) {
		System.out.println(BRIGHT_GREEN + "Sheet 2" + RESET);
		System.out.println();
		testTask1();
		System.out.println();
		testTask2();
		System.out.println();
		testTask3();
	}
	
	private static void testTask1() {
		System.out.println(BRIGHT_GREEN + "--- Task 1 ---" + RESET);
		System.out.println("Fraction of 5/7 is expected " + new Fraction(5, 7));
		System.out.println("Fraction of 1/14 is expected " + new Fraction(1, 14));
		Fraction fraction = new Fraction(5, 7).add(new Fraction(1, 14));
		System.out.println("5/7 + 1/14 = " + fraction);
		Fraction fraction1 = new Fraction(5, 7).subtract(new Fraction(1, 14));
		System.out.println("5/7 - 1/14 = " + fraction1);
		Fraction fraction2 = new Fraction(5, 7).multiply(new Fraction(1, 14));
		System.out.println("5/7 * 1/14 = " + fraction2);
		Fraction fraction3 = new Fraction(5, 7).divide(new Fraction(1, 14));
		System.out.println("5/7 / 1/14 = " + fraction3);
		System.out.println("70/7 = " + fraction3.simplify());
	}
	
	private static void testTask2() {
		System.out.println(BRIGHT_GREEN + "--- Task 2 ---" + RESET);
		Fraction fraction = new Fraction(1, 1);
		for (int i = 2; i < 10; i++) {
			fraction = fraction.add(new Fraction(1, i));
		}
		System.out.println("Fraction of 1026576/362880 is expected " + fraction);
		Fraction fraction1 = fraction.simplify();
		System.out.println("Fraction of 7129/2520 is expected " + fraction1);
	}
	
	private static void testTask3() {
		System.out.println(BRIGHT_GREEN + "--- Task 3 ---" + RESET);
		System.out.println("Fraction of 8/3 is expected " + Fraction.e(3).simplify());
		System.out.println("Fraction of 65/24 is expected " + Fraction.e(4).simplify());
	}
	
}
