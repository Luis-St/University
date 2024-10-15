package net.luis.sheet8;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static net.luis.util.ConsoleColors.*;

/**
 *
 * @author Luis-St
 *
 */

public class Main {
	
	private static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	private static final LinkedList<String> LIST = new LinkedList<>(Arrays.asList(DAYS)); // Input order
	private static final TreeSet<String> TREE_SET = new TreeSet<>(LIST); // Lexicographic order
	private static final HashSet<String> HASH_SET = new HashSet<>(LIST); // Hash order
	
	public static void main(String[] args) {
		System.out.println(BRIGHT_GREEN + "Sheet 8" + RESET);
		System.out.println();
		testTask1();
		System.out.println();
		testTask2();
		System.out.println();
		testTask3();
		System.out.println();
		testTask4();
	}
	
	private static void testTask1() {
		System.out.println(BRIGHT_GREEN + "--- Task 1 ---" + RESET);
		System.out.println("LinkedList:");
		for (String s : LIST) {
			System.out.println(s);
		}
		System.out.println();
		System.out.println("TreeSet:");
		for (String s : TREE_SET) {
			System.out.println(s);
		}
		System.out.println();
		System.out.println("HashSet:");
		for (String s : HASH_SET) {
			System.out.println(s);
		}
	}
	
	private static void testTask2() {
		System.out.println(BRIGHT_GREEN + "--- Task 2 ---" + RESET);
		System.out.println("LinkedList iterator:");
		printIterator(LIST.iterator());
		System.out.println();
		System.out.println("TreeSet iterator:");
		printIterator(TREE_SET.iterator());
		System.out.println();
		System.out.println("HashSet iterator:");
		printIterator(HASH_SET.iterator());
	}
	
	private static void printIterator(@NotNull Iterator<String> iterator) {
		while (iterator.hasNext()) {
			iterator.next();
			if (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		}
	}
	
	private static void testTask3() {
		System.out.println(BRIGHT_GREEN + "--- Task 3 ---" + RESET);
		List<Integer> numbers = IntStream.range(2, 1000).boxed().collect(Collectors.toCollection(LinkedList::new));
		while (!numbers.isEmpty()) {
			int i = numbers.iterator().next();
			System.out.print(i + " ");
			numbers.remove(Integer.valueOf(i));
			numbers.removeIf(integer -> integer % i == 0);
		}
		System.out.println();
	}
	
	private static void testTask4() {
		System.out.println(BRIGHT_GREEN + "--- Task 4 ---" + RESET);
		List<Integer> list = IntStream.range(2, 1000).boxed().collect(Collectors.toCollection(LinkedList::new));
		List<Integer> primes = new ArrayList<>();
		while (!list.isEmpty()) {
			int i = list.iterator().next();
			primes.add(i);
			list.remove(Integer.valueOf(i));
			list.removeIf(integer -> integer % i == 0);
		}
		for (int i = 0; i < primes.size() - 1; i++) {
			int prime = primes.get(i);
			int nextPrime = primes.get(i + 1);
			int difference = nextPrime - prime;
			if (3 > difference && difference > 0) {
				System.out.print("(" + prime + ", " + nextPrime + ") ");
			}
		}
		System.out.println();
	}
}
