package net.luis.util;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 *
 * @author Luis-St
 *
 */

public class Utils {
	
	public static <T> T make(@NotNull T object, @NotNull Consumer<T> consumer) {
		consumer.accept(object);
		return object;
	}
	
	public static @NotNull List<List<String>> readWords(@NotNull File file) {
		List<List<String>> words = new ArrayList<>();
		try (Stream<String> stream = Files.lines(file.toPath())) {
			for (String line : stream.toList()) {
				List<String> wordsInLine = new ArrayList<>();
				StringBuilder tmp = new StringBuilder();
				for (int i = 0; i < line.length(); i++) {
					char c = line.charAt(i);
					if (Character.isLetter(c)) {
						tmp.append(c);
					} else {
						if (tmp.length() > 0) {
							wordsInLine.add(tmp.toString());
							tmp = new StringBuilder();
						}
					}
				}
				if (tmp.length() > 0) {
					wordsInLine.add(tmp.toString());
				}
				words.add(wordsInLine);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return words;
	}
	
}
