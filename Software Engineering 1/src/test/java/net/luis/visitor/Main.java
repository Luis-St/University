package net.luis.visitor;

import net.luis.visitor.visitors.FileSystemDetailVisitor;
import net.luis.visitor.visitors.FileSystemTreeVisitor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author Luis-St
 *
 */

public class Main {
	
	private static final Random RNG = new Random(0L);
	
	public static void main(String[] args) {
		Folder root = createRoot();
		
		Folder bin = createFolder("bin", root, "root", "root");
		File ls = createFile("ls", bin, "root", "root");
		File cp = createFile("cp", bin, "root", "root");
		File mv = createFile("mv", bin, "root", "root");
		File rm = createFile("rm", bin, "root", "root");
		
		Folder home = createFolder("home", root, "root", "root");
		Folder user1 = createFolder("user1", home, "user1", "user");
		File u1Desktop = createFile(".desktop", user1, "user1", "user");
		File u1SSHConfig = createFile(".ssh", user1, "user1", "user");
		File jsonFile = createFile("test.json", user1, "user1", "user");
		
		Folder user2 = createFolder("user2", home, "user2", "user");
		File u2Desktop = createFile(".desktop", user2, "user2", "user");
		File u2SSHConfig = createFile(".ssh", user2, "user2", "user");
		File xmlFile = createFile("test.xml", user2, "user2", "user");
		
		System.out.println("# File System Tree:");
		root.visit(new FileSystemTreeVisitor());
		System.out.println();
		System.out.println("# File System Details (" + root.path() + "):");
		root.visit(new FileSystemDetailVisitor());
		System.out.println();
		System.out.println("# File System Details (" + u1Desktop.path() + "):");
		u1Desktop.visit(new FileSystemDetailVisitor());
		System.out.println();
		System.out.println("# File System Details (" + u2SSHConfig.path() + "):");
		u2SSHConfig.visit(new FileSystemDetailVisitor());
	}
	
	private static @NotNull Folder createRoot() {
		Folder root = new Folder("root", null);
		root.details().put("owner", "root");
		root.details().put("group", "root");
		root.details().put("created", LocalDateTime.now().toString());
		root.details().put("modified", LocalDateTime.now().toString());
		return root;
	}
	
	private static @NotNull File createFile(@NotNull String name, @NotNull Folder parent, @NotNull String owner, @NotNull String group) {
		File file = new File(name, parent);
		file.details().put("owner", owner);
		file.details().put("group", group);
		file.details().put("created", LocalDateTime.now().toString());
		file.details().put("modified", LocalDateTime.now().toString());
		file.details().put("size", RNG.nextInt(1000) + "kb");
		return file;
	}
	
	private static @NotNull Folder createFolder(@NotNull String name, @NotNull Folder parent, @NotNull String owner, @NotNull String group) {
		Folder folder = new Folder(name, parent);
		folder.details().put("owner", owner);
		folder.details().put("group", group);
		folder.details().put("created", LocalDateTime.now().toString());
		folder.details().put("modified", LocalDateTime.now().toString());
		return folder;
	}
}
