package net.luis;

import java.util.Scanner;

public class Eingabe {
	
	public static String eingeben(String prompt) {
		Scanner scanner = new Scanner(System.in);
		System.out.print(prompt + " ");
		return scanner.nextLine();
	}
}
