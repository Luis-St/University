package net.luis.media;

import net.luis.Media;
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
