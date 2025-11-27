package net.luis.observer;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.*;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 *
 * @author Luis-St
 *
 */

public interface FileSystemEntry {
	
	@NotNull String name();
	
	default @NotNull String path() {
		List<FileSystemEntry> struct = Lists.newArrayList(this);
		FileSystemEntry entry = this.parent();
		while (entry != null) {
			struct.add(entry);
			entry = entry.parent();
		}
		Collections.reverse(struct);
		return struct.stream().map(FileSystemEntry::name).collect(Collectors.joining("/"));
	}
	
	@Nullable FileSystemEntry parent();
	
	long size();
	
	default void add(@NotNull FileSystemEntry child) {
		throw new UnsupportedOperationException();
	}
	
	default @UnknownNullability FileSystemEntry get(@NotNull String name) {
		throw new UnsupportedOperationException();
	}
	
	default @NotNull List<String> list() {
		throw new UnsupportedOperationException();
	}
	
	default void remove(@NotNull FileSystemEntry child) {
		throw new UnsupportedOperationException();
	}
	
	void move(@NotNull FileSystemEntry newParent);
	
	void attach(@NotNull Consumer<String> observer);
	
	void detach(@NotNull Consumer<String> observer);
	
	void display(@NotNull StringBuilder builder);
}
