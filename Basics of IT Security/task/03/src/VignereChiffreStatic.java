import java.util.Arrays;
import java.util.HashSet;

public class VignereChiffreStatic {
	
	private static final String[] keywords = {
		"die", "der", "und", "in", "zu", "den", "das", "nicht", "von", "sie", "ist", "des",
		"sich", "mit", "dem", "dass", "er", "es", "ein", "ich", "auf", "so", "eine", "auch",
		"als", "an", "nach", "wie", "im", "für", "fuer", "fur"
	};
	
	private static final HashSet<String> germanKeywordsSet = new HashSet<>(Arrays.asList(keywords));
	private static final double THRESHOLD = 0.3;
	private static final int A_INT = (int) 'A';
	
	public static void main(String[] args) {
		String plainText = "DIE VORLESUNG GRUNDLAGEN DER IT SICHERHEIT IST SEHR INTERESSANT";
		String key = "HFUW";
		String cipherText = encrypt(plainText, key);
		
		System.out.println("Plain Text: " + plainText);
		System.out.println("Key: " + key);
		System.out.println("Cipher Text: " + cipherText);
		System.out.println();
		
		bruteForce(cipherText);
	}
	
	public static void bruteForce(String cipherText) {
		int found = 0;
		for (char a = 'A'; a <= 'Z'; a++) {
			for (char b = 'A'; b <= 'Z'; b++) {
				for (char c = 'A'; c <= 'Z'; c++) {
					for (char d = 'A'; d <= 'Z'; d++) {
						String key = "" + a + b + c + d;
						String plainText = decrypt(cipherText, key);
						
						if (isProbablyReadable(plainText)) {
							found++;
							System.out.println("Key: " + key + " -> " + plainText);
						}
					}
				}
			}
		}
		
		System.out.println("Found " + found + " possible readable results.");
	}
	
	public static String encrypt(String text, String keyForEncryption) {
		char[] chars = text.toUpperCase().toCharArray();
		char[] key = keyForEncryption.toUpperCase().toCharArray();
		
		int keyLength = key.length;
		for (int i = 0; i < chars.length; i++) {
			char charToShift = key[i % keyLength];
			chars[i] = shift(chars[i], charToShift);
		}
		return String.valueOf(chars);
	}
	
	public static String decrypt(String text, String keyForDecryption) {
		char[] chars = text.toUpperCase().toCharArray();
		char[] key = keyForDecryption.toUpperCase().toCharArray();
		
		int keyLength = key.length;
		for (int i = 0; i < chars.length; i++) {
			char charToShift = key[i % keyLength];
			chars[i] = deShift(chars[i], charToShift);
		}
		return String.valueOf(chars);
	}
	
	private static char shift(char ch, char k) {
		if (ch >= 'A' && ch <= 'Z') {
			return (char) ((((int) ch - A_INT) + ((int) k - A_INT)) % 26 + A_INT);
		} else {
			return ch;
		}
	}
	
	private static char deShift(char ch, char k) {
		if (ch >= 'A' && ch <= 'Z') {
			return (char) ((((int) ch - A_INT) - ((int) k - A_INT) + 26) % 26 + (int) 'A');
		} else {
			return ch;
		}
	}
	
	protected static int occurrencesOfKeywords(String s) {
		String[] words = s.toLowerCase().replaceAll("\\.", "").split(" ");
		
		int hits = 0;
		for (String word : words) {
			if (germanKeywordsSet.contains(word)) {
				hits++;
			}
		}
		return hits;
	}
	
	public static boolean isProbablyReadable(String s) {
		int hits = occurrencesOfKeywords(s);
		int word = s.toLowerCase().replaceAll("\\.", "").split(" ").length;
		double percentage = ((double) hits) / word;
		return percentage >= THRESHOLD;
	}
}
