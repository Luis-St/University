package net.luis;

import java.util.*;

/**
 *
 * @author Luis-St
 *
 */

public class Auswaehlen {
	
	public static int optionAuswaehlen(String... options) {
		Map<Integer, String> lookup = Arrays.stream(options).collect(HashMap::new, (map, option) -> map.put(map.size() + 1, option), HashMap::putAll);
		
		System.out.println("Verfügbare Optionen:");
		lookup.forEach((key, value) -> System.out.println(" " + key + ". " + value));
		
		while (true) {
			System.out.print("Auswahl: ");
			try (Scanner scanner = new Scanner(System.in)) {
				int choice = scanner.nextInt();
				if (lookup.containsKey(choice)) {
					return choice;
				}
			} catch (Exception ignored) {
				System.out.println("Ungültige Eingabe!");
			}
		}
	}
}
