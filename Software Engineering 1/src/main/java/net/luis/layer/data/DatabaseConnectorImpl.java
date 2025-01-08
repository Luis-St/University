package net.luis.layer.data;

import com.google.common.collect.Maps;
import net.luis.layer.library.*;
import net.luis.layer.library.media.*;
import net.luis.layer.library.pay.PaymentMethod;
import org.jetbrains.annotations.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author Luis-St
 *
 */

public class DatabaseConnectorImpl implements DatabaseConnector {
	
	private final Map<UUID, User> users = Maps.newHashMap();
	private final Map<UUID, Media> media = Maps.newHashMap();
	private final Map<UUID, Lending> lendings = Maps.newHashMap();
	
	@Override
	public @NotNull @Unmodifiable List<User> getUsers() {
		return List.copyOf(this.users.values());
	}
	
	@Override
	public @UnknownNullability User getUser(@NotNull UUID userId) {
		return this.users.get(userId);
	}
	
	@Override
	public @NotNull User createUser(@NotNull String firstName, @NotNull String lastName, @NotNull LocalDateTime birthDate, @NotNull Address address, @NotNull PaymentMethod paymentMethod) {
		User user = new User(firstName, lastName, birthDate, address, paymentMethod);
		this.users.put(user.getId(), user);
		return user;
	}
	
	@Override
	public boolean deleteUser(@NotNull UUID userId) {
		return this.users.remove(userId) != null;
	}
	
	@Override
	public @NotNull @Unmodifiable List<Media> getMedia() {
		return List.copyOf(this.media.values());
	}
	
	@Override
	public @UnknownNullability Media getMedia(@NotNull UUID mediaId) {
		return this.media.get(mediaId);
	}
	
	@Override
	public @NotNull Media createAudioBook(@NotNull String title, @NotNull Set<String> genres, @NotNull String author, int duration, int numberExist) {
		AudioBook audioBook = new AudioBook(title, genres, author, duration, numberExist);
		this.media.put(audioBook.getId(), audioBook);
		return audioBook;
	}
	
	@Override
	public @NotNull Media createBook(@NotNull String title, @NotNull Set<String> genres, @NotNull String author, int pages, int numberExist) {
		Book book = new Book(title, genres, author, pages, numberExist);
		this.media.put(book.getId(), book);
		return book;
	}
	
	@Override
	public @NotNull Media createEBook(@NotNull String title, @NotNull Set<String> genres, int numberExist) {
		EBook eBook = new EBook(title, genres, numberExist);
		this.media.put(eBook.getId(), eBook);
		return eBook;
	}
	
	@Override
	public @NotNull Media createFilm(@NotNull String title, @NotNull Set<String> genres, @NotNull String director, @NotNull Set<String> actors, int duration, int numberExist) {
		Film film = new Film(title, genres, director, actors, duration, numberExist);
		this.media.put(film.getId(), film);
		return film;
	}
	
	@Override
	public boolean deleteMedia(@NotNull UUID mediaId) {
		return this.media.remove(mediaId) != null;
	}
	
	@Override
	public @UnknownNullability Lending getLending(@NotNull UUID lendingId) {
		return this.lendings.get(lendingId);
	}
	
	@Override
	public @NotNull Lending createLending(@NotNull UUID userId, @NotNull UUID mediaId, @NotNull LocalDateTime startLending, @NotNull LocalDateTime endLending) {
		Lending lending = new Lending(userId, mediaId, startLending, endLending);
		this.lendings.put(lending.getId(), lending);
		this.users.get(userId).addLending(lending);
		return lending;
	}
	
	@Override
	public boolean deleteLending(@NotNull UUID lendingId) {
		Lending lending = this.lendings.get(lendingId);
		if (lending != null) {
			this.getMedia(lending.getMediaId()).decreaseNumberLent();
			this.lendings.remove(lendingId);
			this.users.get(lending.getUserId()).removeLending(lendingId);
			return true;
		}
		return false;
	}
}
