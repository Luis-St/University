package net.luis.library.media;

import net.luis.library.Media;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

/**
 *
 * @author Luis-St
 *
 */

public class AudioBook extends Media implements OnlineMedia, Streamable {
	
	private final String author;
	private final int duration;
	
	public AudioBook(@NotNull String title, @NotNull Set<String> genres, @NotNull String author, int duration, int numberExist) {
		super(title, genres, "audio book", numberExist);
		this.author = author;
		this.duration = duration;
	}
	
	public @NotNull String getAuthor() {
		return this.author;
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
