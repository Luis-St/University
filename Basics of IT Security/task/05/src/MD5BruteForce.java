import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5BruteForce {

    private static final String TARGET_HASH = "2B85116183BF5D56056CAA0DDFD0DF86";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        long startTime = System.currentTimeMillis();

        for (int length = 1; length <= 10; length++) {
            System.out.println("Teste Länge " + length + "...");
            String result = bruteForce(md5, new char[length], 0);
            if (result != null) {
                long duration = System.currentTimeMillis() - startTime;
                System.out.println("Passwort gefunden: " + result);
                System.out.println("Laufzeit: " + duration + " ms");
                return;
            }
        }

        System.out.println("Kein Passwort gefunden.");
    }

    private static String bruteForce(MessageDigest md5, char[] current, int pos) {
        if (pos == current.length) {
            String candidate = new String(current);
            md5.update(candidate.getBytes());
            byte[] hash = md5.digest();
            String hexValue = byteArrayToHex(hash);
            if (hexValue.equals(TARGET_HASH)) {
                return candidate;
            }
            return null;
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            current[pos] = c;
            String result = bruteForce(md5, current, pos + 1);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a)
            sb.append(String.format("%02x", b));
        return sb.toString().toUpperCase();
    }
}
