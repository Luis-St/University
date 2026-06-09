package task05;

import java.math.BigInteger;

public class DiffieHellman {

    public static void main(String[] args) {
        BigInteger p = BigInteger.valueOf(23);
        BigInteger g = BigInteger.valueOf(5);
        BigInteger a = BigInteger.valueOf(4);
        BigInteger b = BigInteger.valueOf(3);

        System.out.println("=== Diffie-Hellman Schlüsselaustausch ===");
        System.out.println();
        System.out.println("Öffentliche Parameter:");
        System.out.println("  p = " + p + " (Primzahl)");
        System.out.println("  g = " + g + " (Generator)");
        System.out.println();

        System.out.println("Schritt 1: Geheime Schlüssel wählen");
        System.out.println("  Alice wählt a = " + a);
        System.out.println("  Bob wählt   b = " + b);
        System.out.println();

        BigInteger A = g.modPow(a, p);
        System.out.println("Schritt 2: Öffentliche Werte berechnen");
        System.out.println("  Alice: A = g^a mod p = " + g + "^" + a + " mod " + p + " = " + A);
        BigInteger B = g.modPow(b, p);
        System.out.println("  Bob:   B = g^b mod p = " + g + "^" + b + " mod " + p + " = " + B);
        System.out.println();

        System.out.println("Schritt 3: Öffentliche Werte austauschen");
        System.out.println("  Alice sendet A = " + A + " an Bob");
        System.out.println("  Bob sendet   B = " + B + " an Alice");
        System.out.println();

        BigInteger sAlice = B.modPow(a, p);
        BigInteger sBob = A.modPow(b, p);
        System.out.println("Schritt 4: Gemeinsamen Schlüssel berechnen");
        System.out.println("  Alice: s = B^a mod p = " + B + "^" + a + " mod " + p + " = " + sAlice);
        System.out.println("  Bob:   s = A^b mod p = " + A + "^" + b + " mod " + p + " = " + sBob);
        System.out.println();

        System.out.println("Ergebnis: Gemeinsamer Sitzungsschlüssel = " + sAlice);
        System.out.println("Schlüssel stimmen überein: " + sAlice.equals(sBob));
    }
}
