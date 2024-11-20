package net.luis;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 *
 * @author Luis-St
 *
 */

public class Lending {
	
	private final UUID id;
	private final UUID userId;
	private final UUID mediaId;
	private final LocalDateTime startLending;
	private final LocalDateTime endLending;
	private int extended;
	
	public Lending(@NotNull UUID userId, @NotNull UUID mediaId, @NotNull LocalDateTime startLending, @NotNull LocalDateTime endLending) {
		this.id = UUID.randomUUID();
		this.userId = userId;
		this.mediaId = mediaId;
		this.startLending = startLending;
		this.endLending = endLending;
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
	
	public @NotNull Media getMedia() {
		return Library.getInstance().getMedia(this.mediaId);
	}
	
	public @NotNull LocalDateTime getStartLending() {
		return this.startLending;
	}
	
	public @NotNull LocalDateTime getEndLending() {
		return this.endLending;
	}
	
	public int getDuration() {
		return (int) this.startLending.until(this.endLending, ChronoUnit.DAYS);
	}
	
	public boolean isExtended() {
		return this.extended > 0;
	}
	
	public int getTimesExtended() {
		return this.extended;
	}
	
	public @Nullable Reminder createReminder(@NotNull String message) {
		if (3 >this.extended) {
			return null;
		}
		return this.getUser().createReminder(message, this);
	}
}
