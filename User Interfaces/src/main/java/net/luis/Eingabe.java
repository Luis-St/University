package net.luis;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 *
 * @author Luis-St
 *
 */

public class Eingabe {
	
	public static @NotNull String eingabe(@NotNull String prompt) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print(prompt);
			return scanner.nextLine();
		}
	}
}
