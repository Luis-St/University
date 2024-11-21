package net.luis;

import java.util.Scanner;

/**
 *
 * @author Luis-St
 *
 */

public class Eingabe {
	
	public static String eingeben(String prompt) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print(prompt);
			return scanner.nextLine();
		}
	}
}
