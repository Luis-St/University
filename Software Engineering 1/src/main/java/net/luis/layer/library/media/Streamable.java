package net.luis.layer.library.media;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 *
 * @author Luis-St
 *
 */

public interface Streamable {
	
	void stream(@NotNull UUID userId);
	
	void pause();
	
	void resume();
	
	boolean isPaused();
	
	int getDuration();
}
