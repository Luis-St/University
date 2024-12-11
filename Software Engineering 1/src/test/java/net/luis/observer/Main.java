package net.luis.observer;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

import static net.luis.observer.FileSystemEntryFactory.*;

/**
 *
 * @author Luis-St
 *
 */

public class Main {
	
	private static final FileSystemEntry ROOT = createFileSystemEntry("root", null, 0, true);
	private static final Map<String, Consumer<String>> ATTACHED = Maps.newHashMap();
	private static FileSystemEntry current = ROOT;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print(current.path() + " > ");
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			String command = parts[0];
			switch (command) {
				case "touch": {
					createFile(parts[1], Long.parseLong(parts[2]));
					break;
				}
				case "mkdir": {
					createFolder(parts[1]);
					break;
				}
				case "cd": {
					changeDirectory(parts[1]);
					break;
				}
				case "ls": {
					listEntries();
					break;
				}
				case "rm": {
					deleteEntry(parts[1]);
					break;
				}
				case "attach": {
					UUID id = UUID.randomUUID();
					Consumer<String> observer = s -> System.out.println("Ovserved by " + id + ": " + s);
					ATTACHED.put(id.toString(), observer);
					current.attach(observer);
					System.out.println("Attached with id: " + id);
					break;
				}
				case "detach": {
					Consumer<String> observer = ATTACHED.get(parts[1]);
					current.detach(observer);
					break;
				}
				case "exit": {
					scanner.close();
					return;
				}
				default: {
					System.out.println("Unknown command");
					break;
				}
			}
		}
	}
	
	private static void createFile(@NotNull String name, long size) {
		FileSystemEntry file = createFileSystemEntry(name, current, size, false);
		current.add(file);
	}
	
	private static void createFolder(@NotNull String name) {
		FileSystemEntry folder = createFileSystemEntry(name, current, 0, true);
		current.add(folder);
	}
	
	private static void changeDirectory(@NotNull String name) {
		if ("..".equals(name)) {
			FileSystemEntry parent = current.parent();
			if (parent != null) {
				current = parent;
				return;
			}
			System.err.println("Already at root");
			return;
		}
		FileSystemEntry entry = current.get(name);
		if (entry instanceof Folder) {
			current = entry;
		}
	}
	
	private static void listEntries() {
		current.list().forEach(System.out::println);
	}
	
	private static void deleteEntry(@NotNull String name) {
		FileSystemEntry entry = current.get(name);
		if (entry == null) {
			System.err.println("No such entry: " + name);
			return;
		}
		current.remove(entry);
	}
}
