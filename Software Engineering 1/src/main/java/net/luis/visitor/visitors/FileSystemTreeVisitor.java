package net.luis.visitor.visitors;

import net.luis.visitor.*;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-St
 *
 */

public class FileSystemTreeVisitor implements FileSystemVisitor {

	@Override
	public void visit(@NotNull FileSystemEntry entry) {
		StringBuilder builder = new StringBuilder();
		this.display(entry, builder);
		System.out.println(builder.toString());
	}
	
	private void display(@NotNull FileSystemEntry entry, @NotNull StringBuilder builder) {
		if (entry instanceof File file) {
			builder.append(file.name());
		} else if (entry instanceof Folder folder) {
			builder.append(folder.name()).append("/");
			folder.children().forEach(child -> {
				builder.append("\n  ");
				
				StringBuilder childBuilder = new StringBuilder();
				FileSystemTreeVisitor.this.display(child, childBuilder);
				builder.append(childBuilder.toString().replaceAll("\n", "\n  "));
			});
		} else {
			throw new IllegalArgumentException("Unknown FileSystemEntry type: " + entry.getClass());
		}
	}
}
