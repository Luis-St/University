package net.luis.library.media;

import net.luis.library.Media;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 *
 * @author Luis-St
 *
 */

public class EBook extends Media implements OnlineMedia {
	
	public EBook(@NotNull String title, @NotNull Set<String> genres, int numberExist) {
		super(title, genres, "ebook", numberExist);
	}
}
