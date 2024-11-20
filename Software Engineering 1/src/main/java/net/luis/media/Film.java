package net.luis.media;

import net.luis.Media;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Set;
import java.util.UUID;

/**
 *
 * @author Luis-St
 *
 */

public class Film extends Media implements OnlineMedia, Streamable {
	
	private final String director;
	private final Set<String> actors;
	private final int duration;
	
	public Film(@NotNull String title, @NotNull Set<String> genres, @NotNull String director, @NotNull Set<String> actors, int duration, int numberExist) {
		super(title, genres, "film", numberExist);
		this.director = director;
		this.actors = actors;
		this.duration = duration;
	}
	
	public @NotNull String getDirector() {
		return this.director;
	}
	
	public @NotNull @Unmodifiable Set<String> getActors() {
		return Set.copyOf(this.actors);
	}
	
	@Override
	public void stream(@NotNull UUID userId) {}
	
	@Override
	public void pause() {}
	
	@Override
	public void resume() {}
	
	@Override
	public boolean isPaused() {
		return false;
	}
	
	@Override
	public int getDuration() {
		return this.duration;
	}
}
