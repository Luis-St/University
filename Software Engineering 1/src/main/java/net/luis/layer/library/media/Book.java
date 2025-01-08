package net.luis.layer.library.media;

import net.luis.layer.library.Media;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 *
 * @author Luis-St
 *
 */

public class Book extends Media {
	
	private final String author;
	private final int pages;
	
	public Book(@NotNull String title, @NotNull Set<String> genres, @NotNull String author, int pages, int numberExist) {
		super(title, genres, "book", numberExist);
		this.author = author;
		this.pages = pages;
	}
	
	public @NotNull String getAuthor() {
		return this.author;
	}
	
	public int getPages() {
		return this.pages;
	}
}
