package task05;

import java.math.BigInteger;

public class RSA {

    public static void main(String[] args) {
        BigInteger p = BigInteger.valueOf(5);
        BigInteger q = BigInteger.valueOf(11);
        BigInteger message = BigInteger.valueOf(7);

        System.out.println("=== RSA Schlüsselerzeugung ===");
        System.out.println();

        System.out.println("Schritt 1: Zwei Primzahlen wählen");
        System.out.println("  p = " + p);
        System.out.println("  q = " + q);
        System.out.println();

        BigInteger n = p.multiply(q);
        System.out.println("Schritt 2: n = p * q = " + p + " * " + q + " = " + n);
        System.out.println();

        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        System.out.println("Schritt 3: φ(n) = (p-1) * (q-1) = " + p.subtract(BigInteger.ONE)
                + " * " + q.subtract(BigInteger.ONE) + " = " + phi);
        System.out.println();

        BigInteger e = BigInteger.valueOf(3);
        System.out.println("Schritt 4: e wählen mit gcd(e, φ(n)) = 1");
        System.out.println("  e = " + e);
        System.out.println("  gcd(" + e + ", " + phi + ") = " + e.gcd(phi));
        System.out.println();

        BigInteger d = e.modInverse(phi);
        System.out.println("Schritt 5: d berechnen mit e * d ≡ 1 mod φ(n)");
        System.out.println("  d = e^(-1) mod φ(n) = " + e + "^(-1) mod " + phi + " = " + d);
        System.out.println("  Probe: e * d mod φ(n) = " + e + " * " + d + " mod " + phi
                + " = " + e.multiply(d).mod(phi));
        System.out.println();

        System.out.println("Öffentlicher Schlüssel: (n, e) = (" + n + ", " + e + ")");
        System.out.println("Privater Schlüssel:     (n, d) = (" + n + ", " + d + ")");
        System.out.println();

        System.out.println("=== Verschlüsselung ===");
        BigInteger cipher = message.modPow(e, n);
        System.out.println("  Nachricht m = " + message);
        System.out.println("  c = m^e mod n = " + message + "^" + e + " mod " + n + " = " + cipher);
        System.out.println();

        System.out.println("=== Entschlüsselung ===");
        BigInteger decrypted = cipher.modPow(d, n);
        System.out.println("  m = c^d mod n = " + cipher + "^" + d + " mod " + n + " = " + decrypted);
        System.out.println();

        System.out.println("Entschlüsselung korrekt: " + message.equals(decrypted));
    }
}
