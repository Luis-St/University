package net.luis.visitor;

import com.google.common.collect.Lists;
import net.luis.visitor.visitors.FileSystemDetailVisitor;
import net.luis.visitor.visitors.FileSystemVisitor;
import org.jetbrains.annotations.*;

import java.util.*;
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
		return struct.stream().map(FileSystemEntry::name).collect(Collectors.joining("/", "/", ""));
	}
	
	@Nullable FileSystemEntry parent();
	
	default @NotNull List<FileSystemEntry> children() {
		throw new UnsupportedOperationException();
	}
	
	@NotNull Map<String, Object> details();
	
	default void visit(@NotNull FileSystemVisitor visitor) {
		visitor.visit(this);
	}
}
