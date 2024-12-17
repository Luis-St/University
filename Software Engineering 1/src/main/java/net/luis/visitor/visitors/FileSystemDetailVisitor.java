package net.luis.visitor.visitors;

import net.luis.visitor.FileSystemEntry;
import net.luis.visitor.Folder;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-St
 *
 */

public class FileSystemDetailVisitor implements FileSystemVisitor {

	@Override
	public void visit(@NotNull FileSystemEntry entry) {
		System.out.println("Details of " + entry.path());
		System.out.println("=====================================");
		System.out.println("Name: " + entry.name());
		System.out.println("Parent: " + (entry.parent() == null ? "/" : entry.parent().path()));
		if (entry instanceof Folder) {
			System.out.println("Children:" + entry.children().stream().map(FileSystemEntry::name).reduce("", (result, element) -> result + "\n - " + element));
		}
		System.out.println("=====================================");
		entry.details().forEach((key, value) -> System.out.println(key + ": " + value));
	}
}
