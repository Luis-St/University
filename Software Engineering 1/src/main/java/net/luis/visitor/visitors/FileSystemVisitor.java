package net.luis.visitor.visitors;

import net.luis.visitor.FileSystemEntry;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-St
 *
 */

public interface FileSystemVisitor {

	void visit(@NotNull FileSystemEntry entry);
}
