package net.luis.sheet7;

import net.luis.sheet2.Fraction;

import static net.luis.util.ConsoleColors.*;

/**
 *
 * @author Luis-St
 *
 */

public class Main {
	
	public static void main(String[] args) {
		System.out.println(BRIGHT_GREEN + "Sheet 7" + RESET);
		System.out.println();
		testTask2();
		System.out.println();
		testTask4();
	}
	
	private static void testTask2() {
		System.out.println(BRIGHT_GREEN + "--- Task 2 ---" + RESET);
		IntNodeList list = new IntNodeList();
		for (int i = 1; i <= 10; ++i) {
			long start = System.nanoTime();
			list.add(8 * i);
			long end = System.nanoTime();
			System.out.println("Time to add " + i + " elements: " + (end - start) + " ns");
		}
		list.remove(3);
		for (int i = list.size() - 1; i >= 0; --i) {
			System.out.print(" " + list.get(i));
		}
		System.out.println();
	}
	
	private static void testTask4() {
		System.out.println(BRIGHT_GREEN + "--- Task 4 ---" + RESET);
		GenericNodeList<Fraction> list = new GenericNodeList<>();
		for (int i = 1; i <= 10; ++i) {
			list.add(new Fraction(1, 8 * i));
		}
		list.remove(3);
		for (int i = list.size() - 1; i >= 0; --i) {
			System.out.print(" " + list.get(i));
		}
		System.out.println();
	}
	
}
