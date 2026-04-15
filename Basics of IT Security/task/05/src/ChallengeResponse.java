import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class ChallengeResponse {

	private static final SecureRandom random = new SecureRandom();

	static void main(String[] args) throws NoSuchAlgorithmException {
		Server server = new Server();
		server.register("alice", "passwort123");
		server.register("bob", "geheim456");

		System.out.println("=== Erfolgreiche Anmeldung alice ===");
		login(server, "alice", "passwort123");

		System.out.println();
		System.out.println("=== Erfolgreiche Anmeldung bob ===");
		login(server, "bob", "geheim456");

		System.out.println();
		System.out.println("=== Falsches Passwort alice ===");
		login(server, "alice", "falsch");

		System.out.println();
		System.out.println("=== Replay-Angriff (Wiederverwendung Challenge) ===");
		replayAttack(server);
	}

	private static void login(Server server, String user, String password) throws NoSuchAlgorithmException {
		String challenge = server.requestChallenge(user);
		if (challenge == null) {
			System.out.println("Benutzer unbekannt.");
			return;
		}
		System.out.println("Server -> Client | Challenge: " + challenge);

		String response = Client.computeResponse(challenge, password);
		System.out.println("Client -> Server | Response:  " + response);

		boolean ok = server.verify(user, challenge, response);
		System.out.println("Ergebnis: " + (ok ? "Login erfolgreich" : "Login abgelehnt"));
	}

	private static void replayAttack(Server server) throws NoSuchAlgorithmException {
		String challenge = server.requestChallenge("alice");
		String response = Client.computeResponse(challenge, "passwort123");
		System.out.println("Erste Verwendung Challenge=" + challenge);
		System.out.println("  -> " + (server.verify("alice", challenge, response) ? "akzeptiert" : "abgelehnt"));
		System.out.println("Zweite Verwendung derselben Challenge=" + challenge);
		System.out.println("  -> " + (server.verify("alice", challenge, response) ? "akzeptiert" : "abgelehnt"));
	}

	static class Server {
		private final Map<String, String> passwords = new HashMap<>();
		private final Map<String, String> openChallenges = new HashMap<>();
		private final Set<String> usedChallenges = new HashSet<>();

		public void register(String user, String password) {
			this.passwords.put(user, password);
		}

		public String requestChallenge(String user) {
			if (!this.passwords.containsKey(user)) {
				return null;
			}
			
			byte[] nonce = new byte[16];
			random.nextBytes(nonce);
			
			long timestamp = System.currentTimeMillis();
			String challenge = HexFormat.of().formatHex(nonce) + "-" + timestamp;
			this.openChallenges.put(user, challenge);
			return challenge;
		}

		public boolean verify(String user, String challenge, String response) throws NoSuchAlgorithmException {
			String expectedChallenge = this.openChallenges.get(user);
			if (expectedChallenge == null || !expectedChallenge.equals(challenge)) {
				return false;
			}
			
			if (this.usedChallenges.contains(challenge)) {
				return false;
			}
			
			String password = this.passwords.get(user);
			String expected = sha256(challenge + password);
			this.openChallenges.remove(user);
			
			if (expected.equals(response)) {
				this.usedChallenges.add(challenge);
				return true;
			}
			return false;
		}
	}

	static class Client {
		public static String computeResponse(String challenge, String password) throws NoSuchAlgorithmException {
			return sha256(challenge + password);
		}
	}

	private static String sha256(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
		return HexFormat.of().formatHex(hash);
	}
}
