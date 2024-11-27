package net.luis.library;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author Luis-St
 *
 */

public class Reminder {
	
	private final UUID id;
	private final UUID userId;
	private final UUID mediaId;
	private final String reason;
	private final LocalDateTime creationDate = LocalDateTime.now();
	private final LocalDateTime startLending;
	private final LocalDateTime endLending;
	private final double amount;
	
	public Reminder(@NotNull UUID userId, @NotNull UUID mediaId, @NotNull String reason, @NotNull LocalDateTime startLending, @NotNull LocalDateTime endLending, double amount) {
		this.id = UUID.randomUUID();
		this.userId = userId;
		this.mediaId = mediaId;
		this.reason = reason;
		this.startLending = startLending;
		this.endLending = endLending;
		this.amount = amount;
	}
	
	public @NotNull UUID getId() {
		return this.id;
	}
	
	public @NotNull UUID getUserId() {
		return this.userId;
	}
	
	public @NotNull User getUser() {
		return Library.getInstance().getUser(this.userId);
	}
	
	public @NotNull UUID getMediaId() {
		return this.mediaId;
	}
	
	public @NotNull String getReason() {
		return this.reason;
	}
	
	public @NotNull LocalDateTime getStartLending() {
		return this.startLending;
	}
	
	public @NotNull LocalDateTime getEndLending() {
		return this.endLending;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public boolean isStillLent() {
		User user = this.getUser();
		if (user.hasLentMedia(this.mediaId)) {
			Optional<Lending> optional = user.getLendings().stream().filter(l -> l.getMediaId().equals(this.mediaId)).findFirst();
			if (optional.isPresent()) {
				Lending lending = optional.get();
				return lending.getStartLending() == this.startLending && lending.getEndLending() == this.endLending;
			}
			return false;
		}
		return false;
	}
	
	public boolean pay() {
		return this.getUser().getPaymentMethod().pay(this.amount);
	}
}
