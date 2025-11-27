package net.luis.composite;

import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-St
 *
 */

public interface FileSystemEntry {
	
	@NotNull String name();
	
	long size();
	
	void display(@NotNull StringBuilder builder);
}
