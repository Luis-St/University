package net.luis.observer;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.*;

import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Luis-St
 *
 */

public class File implements FileSystemEntry {
	
	private final String name;
	private final long size;
	private final List<Consumer<String>> observers = Lists.newArrayList();
	private Folder parent;
	
	File(@NotNull String name, @NotNull FileSystemEntry parent, long size) {
		this.name = name;
		if (!(parent instanceof Folder parentFolder)) {
			throw new IllegalArgumentException("Files must have a parent");
		}
		this.parent = parentFolder;
		this.size = size;
	}
	
	@Override
	public @NotNull String name() {
		return this.name;
	}
	
	@Override
	public @Nullable FileSystemEntry parent() {
		return this.parent;
	}
	
	@Override
	public long size() {
		return this.size;
	}
	
	@Override
	public void move(@NotNull FileSystemEntry newParent) {
		if (!(newParent instanceof Folder folder)) {
			throw new IllegalArgumentException("Cannot move a file into another file");
		}
		this.parent.children.remove(this);
		this.parent = folder;
		folder.children.add(this);
		this.observers.forEach(observer -> observer.accept(this.path()));
	}
	
	@Override
	public void attach(@NotNull Consumer<String> observer) {
		this.observers.add(observer);
	}
	
	@Override
	public void detach(@NotNull Consumer<String> observer) {
		this.observers.remove(observer);
	}
	
	@Override
	public void display(@NotNull StringBuilder builder) {
		builder.append(this.name()).append(" (").append(this.size()).append(" MB)");
	}
}
