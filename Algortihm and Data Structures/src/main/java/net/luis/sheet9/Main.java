package net.luis.sheet9;

import net.luis.util.Utils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static net.luis.util.ConsoleColors.*;

/**
 *
 * @author Luis-St
 *
 */

public class Main {
	
	public static void main(String[] args) {
		System.out.println(BRIGHT_GREEN + "Sheet 9" + RESET);
		System.out.println();
		testTask1();
		System.out.println();
		testTask2();
	}
	
	private static void testTask1() {
		System.out.println(BRIGHT_GREEN + "--- Task 1 ---" + RESET);
		Map<String, Integer> words = new TreeMap<>(Utils.readWords(new File("test.txt")).stream().flatMap(List::stream).collect(Collectors.toMap(word -> word, word -> 1, Integer::sum)));
		for (Map.Entry<String, Integer> entry : words.entrySet()) {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
	}
	
	private static void testTask2() {
		System.out.println(BRIGHT_GREEN + "--- Task 2 ---" + RESET);
		Map<String, Integer> words = new TreeMap<>();
		Map<String, List<Integer>> wordLines = new HashMap<>();
		List<List<String>> lines = Utils.readWords(new File("test.txt"));
		for (int i = 0; i < lines.size(); i++) {
			for (String word : lines.get(i)) {
				words.merge(word, 1, Integer::sum);
				wordLines.putIfAbsent(word, new ArrayList<>());
				wordLines.get(word).add(i + 1);
			}
		}
		for (Map.Entry<String, Integer> entry : words.entrySet()) {
			System.out.println(entry.getKey() + " -> " + entry.getValue() + " -> " + wordLines.get(entry.getKey()));
		}
	}
}
