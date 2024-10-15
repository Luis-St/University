package net.luis.sheet3;

import net.luis.util.Task;

import static net.luis.util.ConsoleColors.*;

/**
 *
 * @author Luis-St
 *
 */

@Task("1")
public class Main {
	
	public static void main(String[] args) {
		System.out.println(BRIGHT_GREEN + "Sheet 3" + RESET);
		System.out.println();
		analyzeArguments(args);
		System.out.println();
		analyzeCharacters(args);
	}
	
	private static void analyzeArguments(String[] args) {
		System.out.println(BRIGHT_GREEN + "--- Task 1 ---" + RESET);
		System.out.print("Arguments: " + args.length + " ");
		System.out.print("with length of ");
		for (String arg : args) {
			System.out.print(arg.length() + " ");
		}
		System.out.println();
	}
	
	@Task("2")
	private static void analyzeCharacters(String[] args) {
		System.out.println(BRIGHT_GREEN + "--- Task 2 ---" + RESET);
		int[] charCount = new int[26];
		for (String arg : args) {
			for (int j = 0; j < arg.length(); j++) {
				char c = arg.charAt(j);
				if (c >= 'a' && c <= 'z') {
					charCount[c - 'a']++;
				} else if (c >= 'A' && c <= 'Z') {
					charCount[c - 'A']++;
				}
			}
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < charCount.length; i++) {
			if (charCount[i] > 0) {
				result.append((char) (i + 'A')).append(": ").append(charCount[i]).append(", ");
			}
		}
		System.out.println(result.substring(0, result.length() - 2));
	}
	
}
