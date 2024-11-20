package net.luis;

import com.google.common.collect.Maps;
import net.luis.pay.PaymentMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
	
	private final Map<UUID, User> users = Maps.newHashMap();
	private final Map<UUID, Media> media = Maps.newHashMap();
	private final Map<UUID, Lending> lendings = Maps.newHashMap();
	
	private Library() {}
	
	public static @NotNull Library getInstance() {
		return INSTANCE;
	}
	
	public @NotNull User getUser(@NotNull UUID userId) {
		return this.users.get(userId);
	}
	
	public @NotNull User createUser(@NotNull String firstName, @NotNull String lastName, @NotNull LocalDateTime birthDate, @NotNull Address address, @NotNull PaymentMethod paymentMethod) {
		User user = new User(firstName, lastName, birthDate, address, paymentMethod);
		this.users.put(user.getId(), user);
		return user;
	}
	
	public @NotNull Media getMedia(@NotNull UUID mediaId) {
		return this.media.get(mediaId);
	}
	
	public @NotNull Media createMedia(@NotNull Supplier<? extends Media> mediaSupplier) {
		Media media = mediaSupplier.get();
		this.media.put(media.getId(), media);
		return media;
	}
	
	public @NotNull Lending getLending(@NotNull UUID lendingId) {
		return this.lendings.get(lendingId);
	}
	
	public @Nullable Lending lendMedia(@NotNull UUID mediaId, @NotNull UUID userId, @NotNull LocalDateTime startLending, @NotNull LocalDateTime endLending) {
		return this.getMedia(mediaId).lend(userId, startLending, endLending);
	}
	
	public @NotNull Lending lendMedia(@NotNull Supplier<Lending> lendingSupplier) {
		Lending lending = lendingSupplier.get();
		this.lendings.put(lending.getId(), lending);
		this.users.get(lending.getUserId()).addLending(lending);
		return lending;
	}
	
	public boolean returnMedia(@NotNull UUID lendingId) {
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
