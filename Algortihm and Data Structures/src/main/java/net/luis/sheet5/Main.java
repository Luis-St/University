package net.luis.sheet5;

import net.luis.sheet2.Fraction;
import net.luis.sheet5.ComparableMergesort;
import net.luis.sheet5.IntMergesort;

import java.util.Arrays;

import static net.luis.util.ConsoleColors.*;

/**
 *
 * @author Luis-St
 *
 */

public class Main {
	
	public static void main(String[] args) {
		System.out.println(BRIGHT_GREEN + "Sheet 5" + RESET);
		System.out.println();
		testTask1();
		System.out.println();
		testTask2();
		System.out.println();
		testTask3();
	}
	
	private static void testTask1() {
		System.out.println(BRIGHT_GREEN + "--- Task 1 ---" + RESET);
		int[] array = {4, 2, 1, 3, 5};
		System.out.println("Unsorted: " + Arrays.toString(array));
		IntMergesort.sort(array);
		System.out.println("Sorted: " + Arrays.toString(array));
	}
	
	private static void testTask2() {
		System.out.println(BRIGHT_GREEN + "--- Task 2 ---" + RESET);
		Integer[] integers = {4, 2, 1, 3, 5};
		System.out.println("Unsorted: " + Arrays.toString(integers));
		ComparableMergesort.sort(integers);
		System.out.println("Sorted: " + Arrays.toString(integers));
		String[] strings = {"e", "c", "b", "d", "a"};
		System.out.println("Unsorted: " + Arrays.toString(strings));
		ComparableMergesort.sort(strings);
		System.out.println("Sorted: " + Arrays.toString(strings));
	}
	
	private static void testTask3() {
		System.out.println(BRIGHT_GREEN + "--- Task 3 ---" + RESET);
		Fraction[] fractions = {new Fraction(1, 2), new Fraction(1, 3), new Fraction(1, 4), new Fraction(1, 5), new Fraction(1, 6)};
		System.out.println("Unsorted: " + Arrays.toString(fractions));
		ComparableMergesort.sort(fractions);
		System.out.println("Sorted: " + Arrays.toString(fractions));
	}
	
}
