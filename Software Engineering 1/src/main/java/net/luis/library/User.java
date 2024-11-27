package net.luis.library;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.luis.library.pay.PaymentMethod;
import org.jetbrains.annotations.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author Luis-St
 *
 */

public class User {
	
	private final UUID id;
	private final String firstName;
	private final String lastName;
	private final LocalDateTime birthDate;
	private final Address address;
	private final PaymentMethod paymentMethod;
	private final Map<UUID, Lending> lendings = Maps.newHashMap();
	private final Set<Reminder> reminders = Sets.newHashSet();
	
	public User(@NotNull String firstName, @NotNull String lastName, @NotNull LocalDateTime birthDate, @NotNull Address address, @NotNull PaymentMethod paymentMethod) {
		this.id = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.address = address;
		this.paymentMethod = paymentMethod;
	}
	
	public @NotNull UUID getId() {
		return this.id;
	}
	
	public @NotNull String getFirstName() {
		return this.firstName;
	}
	
	public @NotNull String getLastName() {
		return this.lastName;
	}
	
	public @NotNull LocalDateTime getBirthDate() {
		return this.birthDate;
	}
	
	public int getAge() {
		return LocalDateTime.now().getYear() - this.birthDate.getYear();
	}
	
	public @NotNull Address getAddress() {
		return this.address;
	}
	
	public @NotNull PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}
	
	public boolean hasLentMedia(@NotNull UUID mediaId) {
		return this.lendings.values().stream().anyMatch(lending -> lending.getMediaId().equals(mediaId));
	}
	
	public @NotNull @Unmodifiable Collection<Lending> getLendings() {
		return Collections.unmodifiableCollection(this.lendings.values());
	}
	
	public @NotNull Lending getLending(@NotNull UUID lendingId) {
		return this.lendings.get(lendingId);
	}
	
	void addLending(@NotNull Lending lending) {
		this.lendings.put(lending.getId(), lending);
	}
	
	void removeLending(@NotNull UUID lendingId) {
		this.lendings.remove(lendingId);
	}
	
	public @NotNull @Unmodifiable Set<Reminder> getReminders() {
		return this.reminders;
	}
	
	@NotNull Reminder createReminder(@NotNull String reason, @NotNull Lending lending) {
		Reminder reminder = new Reminder(this.getId(), lending.getMediaId(), reason, lending.getStartLending(), lending.getEndLending(), lending.getDuration() * 0.5);
		this.reminders.add(reminder);
		return reminder;
	}
}
