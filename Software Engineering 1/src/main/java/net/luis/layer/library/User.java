package net.luis.layer.library;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.luis.layer.library.pay.PaymentMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

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
	
	public void addLending(@NotNull Lending lending) {
		this.lendings.put(lending.getId(), lending);
	}
	
	public void removeLending(@NotNull UUID lendingId) {
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
	
	//region Object overrides
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof User user)) return false;
		
		if (!this.id.equals(user.id)) return false;
		if (!this.firstName.equals(user.firstName)) return false;
		if (!this.lastName.equals(user.lastName)) return false;
		if (!this.birthDate.equals(user.birthDate)) return false;
		if (!this.address.equals(user.address)) return false;
		if (!this.paymentMethod.equals(user.paymentMethod)) return false;
		if (!this.lendings.equals(user.lendings)) return false;
		return this.reminders.equals(user.reminders);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.firstName, this.lastName, this.birthDate, this.address, this.paymentMethod, this.lendings, this.reminders);
	}
	
	@Override
	public String toString() {
		return "User{id=" + this.id +
			", firstName='" + this.firstName + '\'' +
			", lastName='" + this.lastName + '\'' +
			", birthDate=" + this.birthDate +
			", address=" + this.address +
			", paymentMethod=" + this.paymentMethod +
			", lendings=" + this.lendings +
			", reminders=" + this.reminders +
			"}";
	}
	//endregion
}
