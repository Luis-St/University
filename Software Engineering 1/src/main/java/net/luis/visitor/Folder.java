package net.luis.visitor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.Consumer;

/**
 *
 * @author Luis-St
 *
 */

public class Folder implements FileSystemEntry {
	
	private final String name;
	final List<FileSystemEntry> children = Lists.newLinkedList();
	private final Map<String, Object> details = Maps.newLinkedHashMap();
	private final @Nullable Folder parent;
	
	public Folder(@NotNull String name, @Nullable FileSystemEntry parent, FileSystemEntry @NotNull ... children) {
		this.name = name;
		if (parent != null) {
			if (!(parent instanceof Folder parentFolder)) {
				throw new IllegalArgumentException("Parent must be a folder");
			}
			this.parent = parentFolder;
			this.parent.children.add(this);
		} else {
			this.parent = null;
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
	public @NotNull @Unmodifiable List<FileSystemEntry> children() {
		return List.copyOf(this.children);
	}
	
	@Override
	public @NotNull Map<String, Object> details() {
		return this.details;
	}
}
