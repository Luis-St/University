package net.luis;

import net.luis.media.Book;
import net.luis.pay.PayPal;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

/**
 *
 * @author Luis-St
 *
 */

public class Main {
	
	//region UUIDs
	private static UUID lordOfTheRings;
	private static UUID chamberOfSecrets;
	private static UUID prisonerOfAzkaban;
	private static UUID gobletOfFire;
	private static UUID orderOfThePhoenix;
	private static UUID halfBloodPrince;
	private static UUID deathlyHallows;
	private static UUID sorcerersStone;
	private static UUID theHobbit;
	private static UUID hungerGames;
	private static UUID daVinciCode;
	private static UUID catcherInTheRye;
	private static UUID greatGatsby;
	private static UUID theShining;
	//endregion
	
	public static void main(String @NotNull [] args) {
		Library library = Library.getInstance();
		
		lordOfTheRings = library.createMedia(createBook("The Lord of the Rings", Set.of("Fantasy"), "J.R.R. Tolkien", 1200, 3)).getId();
		chamberOfSecrets = library.createMedia(createBook("Harry Potter - Chamber of Secrets", Set.of("Fantasy"), "J.K. Rowling", 350, 3)).getId();
		prisonerOfAzkaban = library.createMedia(createBook("Harry Potter - Prisoner of Azkaban", Set.of("Fantasy"), "J.K. Rowling", 400, 4)).getId();
		gobletOfFire = library.createMedia(createBook("Harry Potter - Goblet of Fire", Set.of("Fantasy"), "J.K. Rowling", 500, 5)).getId();
		orderOfThePhoenix = library.createMedia(createBook("Harry Potter - Order of the Phoenix", Set.of("Fantasy"), "J.K. Rowling", 600, 6)).getId();
		halfBloodPrince = library.createMedia(createBook("Harry Potter - Half-Blood Prince", Set.of("Fantasy"), "J.K. Rowling", 700, 7)).getId();
		deathlyHallows = library.createMedia(createBook("Harry Potter - Deathly Hallows", Set.of("Fantasy"), "J.K. Rowling", 800, 8)).getId();
		sorcerersStone = library.createMedia(createBook("Harry Potter - Sorcerer's Stone", Set.of("Fantasy"), "J.K. Rowling", 300, 5)).getId();
		theHobbit = library.createMedia(createBook("The Hobbit", Set.of("Fantasy"), "J.R.R. Tolkien", 300, 2)).getId();
		hungerGames = library.createMedia(createBook("Hunger Games", Set.of("Dystopian"), "Suzanne Collins", 400, 4)).getId();
		daVinciCode = library.createMedia(createBook("The Da Vinci Code", Set.of("Mystery"), "Dan Brown", 500, 1)).getId();
		catcherInTheRye = library.createMedia(createBook("The Catcher in the Rye", Set.of("Drama"), "J.D. Salinger", 200, 2)).getId();
		greatGatsby = library.createMedia(createBook("The Great Gatsby", Set.of("Drama"), "F. Scott Fitzgerald", 250, 3)).getId();
		theShining = library.createMedia(createBook("The Shining", Set.of("Horror"), "Stephen King", 400, 2)).getId();
		
		Address address = new Address("Robert-Gerwig-Platz", 1, "78120", "Furtwangen im Schwarzwald", "Baden-Württemberg", "Germany");
		User user1 = library.createUser("User", "1", LocalDateTime.of(2001, 10, 1, 0, 0), address, new PayPal("user1", "*****"));
		User user2 = library.createUser("User", "2", LocalDateTime.of(2004, 4, 11, 0, 0), address, new PayPal("user2", "*****"));
		
		lentBooksUser1(library, user1);
		lentBooksUser2(library, user2);
		
		assert library.getMedia(lordOfTheRings).getNumberExist() == 3;
		assert library.getUser(user1.getId()).getLendings().size() == 5;
		assert library.getUser(user2.getId()).getLendings().size() == 3;
	}
	
	private static @NotNull Supplier<Book> createBook(@NotNull String name, @NotNull Set<String> genres, @NotNull String author, int pages, int numberExist) {
		return () -> new Book(name, genres, author, pages, numberExist);
	}
	
	private static void lentBooksUser1(@NotNull Library library, @NotNull User user) {
		library.lendMedia(chamberOfSecrets, user.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(14));
		library.lendMedia(prisonerOfAzkaban, user.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(30));
		library.lendMedia(gobletOfFire, user.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(14));
		library.lendMedia(orderOfThePhoenix, user.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(60));
		library.lendMedia(halfBloodPrince, user.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(14));
	}
	
	private static void lentBooksUser2(@NotNull Library library, @NotNull User user) {
		library.lendMedia(deathlyHallows, user.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(14));
		library.lendMedia(lordOfTheRings, user.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(30));
		library.lendMedia(theHobbit, user.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(5));
	}
}
