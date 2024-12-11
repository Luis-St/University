package net.luis.composite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Luis-St
 *
 */

public class Main {
	
	public static void main(String[] args) {
		Folder root = new Folder("root");
		
		File file1 = new File("file1", 100);
		File file2 = new File("file2", 200);
		File file3 = new File("file3", 300);
		File file4 = new File("file4", 400);
		File file5 = new File("file5", 500);
		
		Folder folder1 = new Folder("folder1");
		folder1.add(file1);
		folder1.add(file2);
		root.add(folder1);
		
		Folder folder2 = new Folder("folder2");
		folder2.add(file3);
		folder2.add(file4);
		root.add(folder2);
		
		Folder folder3 = new Folder("folder3");
		folder3.add(file5);
		folder2.add(folder3);
		
		StringBuilder builder = new StringBuilder();
		root.display(builder);
		System.out.println(builder.toString());
	}
}
