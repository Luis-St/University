package net.luis.visitor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @author Luis-St
 *
 */

public class File implements FileSystemEntry {
	
	private final Map<String, Object> details = Maps.newLinkedHashMap();
	private final String name;
	private final Folder parent;
	
	public File(@NotNull String name, @NotNull FileSystemEntry parent) {
		this.name = name;
		if (!(parent instanceof Folder parentFolder)) {
			throw new IllegalArgumentException("Parent must be a folder");
		}
		this.parent = parentFolder;
		this.parent.children.add(this);
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
	public @NotNull Map<String, Object> details() {
		return this.details;
	}
}
