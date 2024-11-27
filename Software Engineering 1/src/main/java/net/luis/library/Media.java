package net.luis.library;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author Luis-St
 *
 */

public abstract class Media {
	
	private final UUID id;
	private final String title;
	private final Set<String> genres;
	private final String type;
	private int numberExist;
	private int numberLent;
	
	protected Media(@NotNull String title, @NotNull Set<String> genres, @NotNull String type, int numberExist) {
		this.id = UUID.randomUUID();
		this.title = title;
		this.genres = genres;
		this.type = type;
		this.numberExist = numberExist;
	}
	
	public @NotNull UUID getId() {
		return this.id;
	}
	
	public @NotNull String getTitle() {
		return this.title;
	}
	
	public @NotNull Set<String> getGenres() {
		return this.genres;
	}
	
	public boolean hasGenre(@NotNull String genre) {
		return this.genres.contains(genre);
	}
	
	public @NotNull String getType() {
		return this.type;
	}
	
	public int getNumberExist() {
		return this.numberExist;
	}
	
	public void increaseNumberExist() {
		this.numberExist++;
	}
	
	public void decreaseNumberExist() {
		this.numberExist--;
	}
	
	public void setNumberExist(int numberExist) {
		this.numberExist = numberExist;
	}
	
	public void increaseNumberLent() {
		this.numberLent++;
	}
	
	public void decreaseNumberLent() {
		this.numberLent--;
	}
	
	public boolean canBeLent() {
		return this.numberExist > this.numberLent;
	}
	
	public @Nullable Lending lend(@NotNull UUID userId, @NotNull LocalDateTime startLending, @NotNull LocalDateTime endLending) {
		if (this.canBeLent()) {
			Lending lending = new Lending(userId, this.id, startLending, endLending);
			this.increaseNumberLent();
			return Library.getInstance().lendMedia(() -> lending);
		}
		return null;
	}
}
