package net.luis.observer;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.*;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Luis-St
 *
 */

public class Folder implements FileSystemEntry {
	
	private final String name;
	private final List<Consumer<String>> observers = Lists.newArrayList();
	final List<FileSystemEntry> children = Lists.newLinkedList();
	private Folder parent;
	
	Folder(@NotNull String name, @Nullable FileSystemEntry parent, FileSystemEntry @NotNull ... children) {
		this.name = name;
		if (parent != null) {
			if (!(parent instanceof Folder parentFolder)) {
				throw new IllegalArgumentException("Files must have a parent");
			}
			this.parent = parentFolder;
		}
		Collections.addAll(this.children, children);
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
		return this.children.stream().mapToLong(FileSystemEntry::size).sum();
	}
	
	@Override
	public void add(@NotNull FileSystemEntry child) {
		this.children.add(child);
		this.observers.forEach(observer -> observer.accept(child.path()));
	}
	
	@Override
	public @UnknownNullability FileSystemEntry get(@NotNull String name) {
		return this.children.stream().filter(child -> child.name().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
	
	@Override
	public @NotNull List<String> list() {
		return this.children.stream().map(entry -> {
			if (entry instanceof Folder folder) {
				return folder.name() + "/";
			}
			return entry.name();
		}).toList();
	}
	
	@Override
	public void remove(@NotNull FileSystemEntry child) {
		this.children.remove(child);
		this.observers.forEach(observer -> observer.accept(child.path()));
	}
	
	@Override
	public void move(@NotNull FileSystemEntry newParent) {
		if (!(newParent instanceof Folder folder)) {
			throw new IllegalArgumentException("Cannot move a folder into a file");
		}
		if (this.parent == null) {
			throw new IllegalArgumentException("Cannot move the root folder");
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
	@SuppressWarnings("DuplicatedCode")
	public void display(@NotNull StringBuilder builder) {
		builder.append(this.name()).append(" (").append(this.size()).append(" MB)");
		this.children.forEach(child -> {
			builder.append("\n\t");
			
			StringBuilder childBuilder = new StringBuilder();
			child.display(childBuilder);
			builder.append(childBuilder.toString().replaceAll("\n", "\n\t"));
		});
	}
}
