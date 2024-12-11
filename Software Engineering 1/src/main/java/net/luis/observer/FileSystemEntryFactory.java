package net.luis.observer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 *
 * @author Luis-St
 *
 */

public class FileSystemEntryFactory {

	public static @NotNull FileSystemEntry createFileSystemEntry(@NotNull String name, @Nullable FileSystemEntry parent, long size, boolean isFolder) {
		if (isFolder) {
			if (size > 0) {
				throw new IllegalArgumentException("Folders cannot have a size");
			}
			return new Folder(name, parent);
		} else {
			Objects.requireNonNull(parent, "Files must have a parent");
			return new File(name, parent, size);
		}
	}
}
