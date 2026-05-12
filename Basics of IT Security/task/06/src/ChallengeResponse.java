import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class ChallengeResponse {

    private final Map<String, String> users = new HashMap<>();
    private final SecureRandom random = new SecureRandom();

    public ChallengeResponse() {
        users.put("alice", "sicheresPasswort1");
        users.put("bob", "meinGeheimnis42");
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        ChallengeResponse system = new ChallengeResponse();

        System.out.println("=== Challenge-Response Authentifizierung ===\n");

        system.authenticate("alice", "sicheresPasswort1");
        system.authenticate("bob", "meinGeheimnis42");
        system.authenticate("alice", "falschesPasswort");
        system.authenticate("eve", "irgendwas");
    }

    public void authenticate(String username, String password) throws NoSuchAlgorithmException {
        System.out.println("--- Login-Versuch: " + username + " ---");
        System.out.println("1. Client sendet Benutzername: " + username);

        if (!users.containsKey(username)) {
            System.out.println("   Server: Benutzer nicht gefunden.");
            System.out.println("   => Authentifizierung FEHLGESCHLAGEN\n");
            return;
        }

        String challenge = generateChallenge();
        System.out.println("2. Server sendet Challenge: " + challenge);

        String response = computeHash(challenge + password);
        System.out.println("3. Client berechnet Response: SHA-256(Challenge + Passwort)");
        System.out.println("   Client sendet Response:   " + response);

        String expected = computeHash(challenge + users.get(username));
        System.out.println("4. Server berechnet erwartet: " + expected);

        if (response.equals(expected)) {
            System.out.println("   => Authentifizierung ERFOLGREICH\n");
        } else {
            System.out.println("   => Authentifizierung FEHLGESCHLAGEN\n");
        }
    }

    private String generateChallenge() {
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        return byteArrayToHex(bytes);
    }

    private static String computeHash(String input) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        sha256.update(input.getBytes());
        return byteArrayToHex(sha256.digest());
    }

    private static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a)
            sb.append(String.format("%02x", b));
        return sb.toString().toUpperCase();
    }
}
