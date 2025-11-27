package net.luis.composite;

import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-St
 *
 */

public record File(@NotNull String name, long size) implements FileSystemEntry {
	
	@Override
	public void display(@NotNull StringBuilder builder) {
		builder.append(this.name()).append(" (").append(this.size()).append(" MB)");
	}
}
