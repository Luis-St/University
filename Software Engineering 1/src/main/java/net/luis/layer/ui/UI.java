package net.luis.layer.ui;

import net.luis.layer.library.*;
import net.luis.layer.library.pay.PayPal;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author Luis-St
 *
 */

public class UI implements Runnable {
	
	private static final Library library = Library.getInstance();
	
	public void init() {
		library.createBook("The Lord of the Rings", Set.of("Fantasy"), "J.R.R. Tolkien", 1200, 3);
		library.createBook("Harry Potter - Chamber of Secrets", Set.of("Fantasy"), "J.K. Rowling", 350, 3);
		library.createBook("Harry Potter - Prisoner of Azkaban", Set.of("Fantasy"), "J.K. Rowling", 400, 4);
		library.createBook("Harry Potter - Goblet of Fire", Set.of("Fantasy"), "J.K. Rowling", 500, 5);
		library.createBook("Harry Potter - Order of the Phoenix", Set.of("Fantasy"), "J.K. Rowling", 600, 6);
		library.createBook("Harry Potter - Half-Blood Prince", Set.of("Fantasy"), "J.K. Rowling", 700, 7);
		library.createBook("Harry Potter - Deathly Hallows", Set.of("Fantasy"), "J.K. Rowling", 800, 8);
		library.createBook("Harry Potter - Sorcerer's Stone", Set.of("Fantasy"), "J.K. Rowling", 300, 5);
		library.createBook("The Hobbit", Set.of("Fantasy"), "J.R.R. Tolkien", 300, 2);
		library.createBook("Hunger Games", Set.of("Dystopian"), "Suzanne Collins", 400, 4);
		library.createBook("The Da Vinci Code", Set.of("Mystery"), "Dan Brown", 500, 1);
		library.createBook("The Catcher in the Rye", Set.of("Drama"), "J.D. Salinger", 200, 2);
		library.createBook("The Great Gatsby", Set.of("Drama"), "F. Scott Fitzgerald", 250, 3);
		library.createBook("The Shining", Set.of("Horror"), "Stephen King", 400, 2);
		
		Address address = new Address("Robert-Gerwig-Platz", 1, "78120", "Furtwangen im Schwarzwald", "Baden-Württemberg", "Germany");
		library.createUser("User", "1", LocalDateTime.of(2001, 10, 1, 0, 0), address, new PayPal("user1", "*****"));
		library.createUser("User", "2", LocalDateTime.of(2004, 4, 11, 0, 0), address, new PayPal("user2", "*****"));
	}
	
	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("> ");
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			String command = parts[0];
		
			switch (command) {
				case "user" -> this.handleUserCommand(parts);
				case "media" -> this.handleMediaCommand(parts);
				case "lending" -> this.handleLendingCommand(parts);
				case "exit" -> System.exit(0);
				default -> System.out.println("Unknown command: " + command);
			}
		}
	}
	
	private void handleUserCommand(String @NotNull [] parts) {
		String subCommand = parts[1];
		switch (subCommand) {
			case "create" -> {
				String firstName = parts[2];
				String lastName = parts[3];
				LocalDateTime birthDate = LocalDateTime.parse(parts[4]);
				String street = parts[5];
				int houseNumber = Integer.parseInt(parts[6]);
				String postalCode = parts[7];
				String city = parts[8];
				String state = parts[9];
				String country = parts[10];
				String paypalUsername = parts[11];
				String paypalPassword = parts[12];
				
				User user = library.createUser(firstName, lastName, birthDate, new Address(street, houseNumber, postalCode, city, state, country), new PayPal(paypalUsername, paypalPassword));
				System.out.println("User created: " + user);
			}
			case "list" -> {
				library.getUsers().forEach(System.out::println);
			}
			case "get" -> {
				System.out.println(library.getUser(UUID.fromString(parts[2])));
			}
			case "delete" -> {
				boolean result = library.deleteUser(UUID.fromString(parts[2]));
				System.out.println("User deleted: " + result);
			}
			default -> System.out.println("Unknown subcommand: " + subCommand);
		}
	}
	
	private void handleMediaCommand(String @NotNull [] parts) {
		String subCommand = parts[1];
		switch (subCommand) {
			case "create" -> {
				String type = parts[2];
				String title = parts[3];
				switch (type) {
					case "audio" -> {
						String author = parts[4];
						int duration = Integer.parseInt(parts[5]);
						int numberExist = Integer.parseInt(parts[6]);
						Media media = library.createAudioBook(title, Set.of(), author, duration, numberExist);
						System.out.println("AudioBook created: " + media);
					}
					case "book" -> {
						String author = parts[4];
						int pages = Integer.parseInt(parts[5]);
						int numberExist = Integer.parseInt(parts[6]);
						Media media = library.createBook(title, Set.of(), author, pages, numberExist);
						System.out.println("Book created: " + media);
					}
					case "ebook" -> {
						int numberExist = Integer.parseInt(parts[4]);
						Media media = library.createEBook(title, Set.of(), numberExist);
						System.out.println("EBook created: " + media);
					}
					case "film" -> {
						String director = parts[4];
						Set<String> actors = Set.of(parts[5].split(","));
						int duration = Integer.parseInt(parts[6]);
						int numberExist = Integer.parseInt(parts[7]);
						Media media = library.createFilm(title, Set.of(), director, actors, duration, numberExist);
						System.out.println("Film created: " + media);
					}
					default -> System.out.println("Unknown media type: " + type);
				}
			}
			case "list" -> {
				library.getMedia().forEach(System.out::println);
			}
			case "get" -> {
				System.out.println(library.getMedia(UUID.fromString(parts[2])));
			}
			case "delete" -> {
				boolean result = library.deleteMedia(UUID.fromString(parts[2]));
				System.out.println("Media deleted: " + result);
			}
			default -> System.out.println("Unknown subcommand: " + subCommand);
		}
	}
	
	private void handleLendingCommand(String @NotNull [] parts) {
		String subCommand = parts[1];
		switch (subCommand) {
			case "create" -> {
				UUID userId = UUID.fromString(parts[2]);
				UUID mediaId = UUID.fromString(parts[3]);
				LocalDateTime startLending = LocalDateTime.parse(parts[4]);
				LocalDateTime endLending = LocalDateTime.parse(parts[5]);
				System.out.println("Lending created: " + library.createLending(userId, mediaId, startLending, endLending));
			}
			case "list" -> {
				UUID userId = UUID.fromString(parts[2]);
				library.getUser(userId).getLendings().forEach(System.out::println);
			}
			case "get" -> {
				System.out.println(library.getLending(UUID.fromString(parts[2])));
			}
		}
	}
}
