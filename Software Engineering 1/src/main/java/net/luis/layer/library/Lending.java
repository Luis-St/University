package net.luis.layer.library;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
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
	
	//region Object overrides
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Lending lending)) return false;
		
		if (this.extended != lending.extended) return false;
		if (!this.id.equals(lending.id)) return false;
		if (!this.userId.equals(lending.userId)) return false;
		if (!this.mediaId.equals(lending.mediaId)) return false;
		if (!this.startLending.equals(lending.startLending)) return false;
		return this.endLending.equals(lending.endLending);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.userId, this.mediaId, this.startLending, this.endLending);
	}
	
	@Override
	public String toString() {
		return "Lending{id=" + this.id +
			", userId=" + this.userId +
			", mediaId=" + this.mediaId +
			", startLending=" + this.startLending +
			", endLending=" + this.endLending +
			", extended=" + this.extended +
			"}";
	}
	//endregion
}
