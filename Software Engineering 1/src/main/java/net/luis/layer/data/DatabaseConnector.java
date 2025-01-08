package net.luis.layer.data;

import net.luis.layer.library.*;
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

public interface DatabaseConnector {
	
	//region User
	@NotNull @Unmodifiable List<User> getUsers();
	
	@UnknownNullability User getUser(@NotNull UUID userId);
	
	@NotNull User createUser(@NotNull String firstName, @NotNull String lastName, @NotNull LocalDateTime birthDate, @NotNull Address address, @NotNull PaymentMethod paymentMethod);
	
	boolean deleteUser(@NotNull UUID userId);
	//endregion
	
	//region Media
	@NotNull @Unmodifiable List<Media> getMedia();
	
	@UnknownNullability Media getMedia(@NotNull UUID mediaId);
	
	@NotNull Media createAudioBook(@NotNull String title, @NotNull Set<String> genres, @NotNull String author, int duration, int numberExist);
	
	@NotNull Media createBook(@NotNull String title, @NotNull Set<String> genres, @NotNull String author, int pages, int numberExist);
	
	@NotNull Media createEBook(@NotNull String title, @NotNull Set<String> genres, int numberExist);
	
	@NotNull Media createFilm(@NotNull String title, @NotNull Set<String> genres, @NotNull String director, @NotNull Set<String> actors, int duration, int numberExist);
	
	boolean deleteMedia(@NotNull UUID mediaId);
	//endregion
	
	//region Lending
	@UnknownNullability Lending getLending(@NotNull UUID lendingId);
	
	@NotNull Lending createLending(@NotNull UUID userId, @NotNull UUID mediaId, @NotNull LocalDateTime startLending, @NotNull LocalDateTime endLending);
	
	boolean deleteLending(@NotNull UUID lendingId);
	//endregion
}
