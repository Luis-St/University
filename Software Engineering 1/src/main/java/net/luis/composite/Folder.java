package net.luis.composite;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Luis-St
 *
 */

public class Folder implements FileSystemEntry {
	
	private final String name;
	private final List<FileSystemEntry> children = Lists.newLinkedList();
	
	public Folder(@NotNull String name, FileSystemEntry @NotNull ... children) {
		this.name = name;
		Collections.addAll(this.children, children);
	}
	
	public void add(@NotNull FileSystemEntry children) {
		this.children.add(children);
	}
	
	@Override
	public @NotNull String name() {
		return this.name;
	}
	
	@Override
	public long size() {
		return this.children.stream().mapToLong(FileSystemEntry::size).sum();
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
