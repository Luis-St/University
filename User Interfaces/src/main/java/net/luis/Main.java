package net.luis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;

/**
 *
 * @author Luis-St
 *
 */

public class Main {
	
	private static final Logger LOGGER = LogManager.getLogger(Main.class);
	
	public static void main(String @NotNull [] args) {
		//String name = readString("Enter a name: ");
		//System.out.println("Hello, " + name + "!");
		
		//String option = chooseOption("Option 1", "Option 2", "Option 3");
		//System.out.println("You chose: " + option);
	}
	
	public static @NotNull String readString(@NotNull String prompt) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print(prompt);
			return scanner.nextLine();
		}
	}
	
	public static @NotNull String chooseOption(@NotNull String... options) {
		Map<Integer, String> lookup = Arrays.stream(options).collect(HashMap::new, (map, option) -> map.put(map.size() + 1, option), HashMap::putAll);
		
		System.out.println("Options:");
		lookup.forEach((key, value) -> System.out.println(" " + key + ". " + value));
		
		while (true) {
			System.out.print("Choose an option: ");
			try (Scanner scanner = new Scanner(System.in)) {
				int choice = scanner.nextInt();
				if (lookup.containsKey(choice)) {
					return lookup.get(choice);
				}
			} catch (Exception ignored) {
				System.out.println("Invalid choice, please try again.");
			}
		}
	}
}
