package net.luis.layer.library;

import net.luis.layer.data.DatabaseConnector;
import net.luis.layer.data.DatabaseConnectorImpl;
import net.luis.layer.library.pay.PaymentMethod;
import org.jetbrains.annotations.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Supplier;

/**
 *
 * @author Luis-St
 *
 */

public class Library {
	
	private static final Library INSTANCE = new Library();
	
	private final DatabaseConnector databaseConnector = new DatabaseConnectorImpl();
	
	private Library() {}
	
	public static @NotNull Library getInstance() {
		return INSTANCE;
	}
	
	public @NotNull @Unmodifiable List<User> getUsers() {
		return this.databaseConnector.getUsers();
	}
	
	public @UnknownNullability User getUser(@NotNull UUID userId) {
		return this.databaseConnector.getUser(userId);
	}
	
	public @NotNull User createUser(@NotNull String firstName, @NotNull String lastName, @NotNull LocalDateTime birthDate, @NotNull Address address, @NotNull PaymentMethod paymentMethod) {
		return this.databaseConnector.createUser(firstName, lastName, birthDate, address, paymentMethod);
	}
	
	public boolean deleteUser(@NotNull UUID userId) {
		return this.databaseConnector.deleteUser(userId);
	}
	
	public @NotNull @Unmodifiable List<Media> getMedia() {
		return this.databaseConnector.getMedia();
	}
	
	public @UnknownNullability Media getMedia(@NotNull UUID mediaId) {
		return this.databaseConnector.getMedia(mediaId);
	}
	
	public @NotNull Media createAudioBook(@NotNull String title, @NotNull Set<String> genres, @NotNull String author, int duration, int numberExist) {
		return this.databaseConnector.createAudioBook(title, genres, author, duration, numberExist);
	}
	
	public @NotNull Media createBook(@NotNull String title, @NotNull Set<String> genres, @NotNull String author, int pages, int numberExist) {
		return this.databaseConnector.createBook(title, genres, author, pages, numberExist);
	}
	
	public @NotNull Media createEBook(@NotNull String title, @NotNull Set<String> genres, int numberExist) {
		return this.databaseConnector.createEBook(title, genres, numberExist);
	}
	
	public @NotNull Media createFilm(@NotNull String title, @NotNull Set<String> genres, @NotNull String director, @NotNull Set<String> actors, int duration, int numberExist) {
		return this.databaseConnector.createFilm(title, genres, director, actors, duration, numberExist);
	}
	
	public boolean deleteMedia(@NotNull UUID mediaId) {
		return this.databaseConnector.deleteMedia(mediaId);
	}
	
	public @UnknownNullability Lending getLending(@NotNull UUID lendingId) {
		return this.databaseConnector.getLending(lendingId);
	}
	
	public @NotNull Lending createLending(@NotNull UUID userId, @NotNull UUID mediaId, @NotNull LocalDateTime startLending, @NotNull LocalDateTime endLending) {
		return this.databaseConnector.createLending(userId, mediaId, startLending, endLending);
	}
	
	public boolean deleteLending(@NotNull UUID lendingId) {
		return this.databaseConnector.deleteLending(lendingId);
	}
}
