package net.luis.task6;

import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.*;

public class TseSignatureVerification {
	
	static final String SIG_B64 = "b6m7r2PAlErjk9c30c4bkPirpqNy20hs41LrJuyoduodY73PB1D6JvQRrRNZ+j4PA0wYKiUXN5Q87TslbNCRdCo73EP0KUJ3mdd1if8FFnPWMG92Mh4yNw4vLv0rSwgG";
	static final String PK_B64 = "BBiZGshmNFzXEf0HSvI57ShpiZ6c2m3NPKVVpbiTHcNvgK0gEgQgCf3PMMvZBz725Acdwyck5ApP3biOaiIYEwajMxIQsedqiq++esE0ssm0vp94mNt+UwZ4yFPzU3haGw==";
	
	static final String CLIENT_ID = "P915560150";
	static final String PROCESS_DATA = "Beleg^0.00_1.65_0.00_0.00_0.00^1.65:Bar";
	static final String PROCESS_TYPE = "Kassenbeleg-V1";
	static final long TRANSACTION_NUM = 124226;
	static final long SIGNATURE_COUNTER = 387826;
	static final String LOG_TIME_ISO = "2022-10-31T06:29:58.000Z"; // End-/Logzeit
	
	static final byte[] CERTIFIED_DATA_TYPE = hexBytes("060904007F000703070101"); // OID 0.4.0.127.0.7.3.7.1.1
	static final byte[] SIGNATURE_ALGORITHM = hexBytes("300C060A04007F00070101040104"); // OID 0.4.0.127.0.7.1.1.4.1.4 (SHA384)
	
	static void main(String[] args) throws Exception {
		System.out.println("==================================================================");
		System.out.println(" TSE-Signatur-Verifikation (ECDSA-signierter Kassenbon)");
		System.out.println("==================================================================");
		
		// a)
		byte[] sig = Base64.getDecoder().decode(SIG_B64);
		byte[] pk = Base64.getDecoder().decode(PK_B64);
		int fs = (pk.length - 1) / 2;                       // Feldgroesse in Byte (48 -> 384 Bit)
		BigInteger r = new BigInteger(1, Arrays.copyOfRange(sig, 0, sig.length / 2));
		BigInteger s = new BigInteger(1, Arrays.copyOfRange(sig, sig.length / 2, sig.length));
		BigInteger dx = new BigInteger(1, Arrays.copyOfRange(pk, 1, 1 + fs));
		BigInteger dy = new BigInteger(1, Arrays.copyOfRange(pk, 1 + fs, pk.length));
		
		System.out.println("\n--- a) Inhalt des QR-Codes ---");
		System.out.println("Signatur:  " + sig.length + " Byte  (ecdsa-plain -> r||s, je " + sig.length / 2 + " Byte)");
		System.out.println("PublicKey: " + pk.length + " Byte  (0x" + String.format("%02X", pk[0]) + " || Dx || Dy)");
		System.out.println("r  = " + hex(r));
		System.out.println("s  = " + hex(s));
		System.out.println("Dx = " + hex(dx));
		System.out.println("Dy = " + hex(dy));
		String serial = HexFormat.of().formatHex(sha256(pk));
		System.out.println("serialNumber = SHA256(PublicKey) = " + serial);
		
		// b)
		long logTime = Instant.parse(LOG_TIME_ISO).getEpochSecond();
		byte[] m = buildLogMessage(CLIENT_ID, PROCESS_DATA, PROCESS_TYPE, TRANSACTION_NUM, SIGNATURE_COUNTER, logTime, sha256(pk));
		byte[] hBytes = sha384(m);
		BigInteger h = new BigInteger(1, hBytes);
		
		System.out.println("\n--- b) Log-Message und Hash ---");
		System.out.println("logTime (unix) = " + logTime + " = 0x" + Long.toHexString(logTime).toUpperCase());
		System.out.println("M (" + m.length + " Byte) = " + HexFormat.of().formatHex(m));
		System.out.println("h = SHA384(M)  = " + HexFormat.of().formatHex(hBytes));
		
		// c)
		System.out.println("\n--- c) Bestimmung der elliptischen Kurve ---");
		String[] candidates = { "secp384r1", "brainpoolP384r1" };
		X9ECParameters chosen = null;
		String chosenName = null;
		for (String name : candidates) {
			X9ECParameters x9 = ECNamedCurveTable.getByName(name);
			ECPoint d = x9.getCurve().createPoint(dx, dy);
			boolean gOk = x9.getG().isValid();
			boolean dOk = d.isValid();
			System.out.printf("%-16s  G auf Kurve: %-5b | D auf Kurve: %-5b%n", name, gOk, dOk);
			if (dOk) {
				chosen = x9;
				chosenName = name;
			}
		}
		if (chosen == null) {
			System.out.println("Keine der getesteten Kurven passt -> Abbruch.");
			return;
		}
		System.out.println("=> verwendete Kurve: " + chosenName);
		
		// d)
		System.out.println("\n--- d) Verifikation der Signatur ---");
		boolean valid = verify(chosen, h, r, s, dx, dy);
		System.out.println("Signatur " + (valid ? "GUELTIG" : "UNGUELTIG"));
		
		// e)
		System.out.println("\n--- e) Selbst erzeugter Bon ---");
		selfCreatedExample();
		
		System.out.println("\n==================================================================");
	}
	
	static byte[] buildLogMessage(String clientId, String processData, String processType, long transactionNumber, long signatureCounter, long logTime, byte[] serialNumber) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		writeAll(out,
			tlv(0x02, new byte[] { 0x02 }),
			CERTIFIED_DATA_TYPE,
			tlv(0x80, "FinishTransaction".getBytes()),
			tlv(0x81, clientId.getBytes()),
			tlv(0x82, processData.getBytes()),
			tlv(0x83, processType.getBytes()),
			tlv(0x85, unsignedBytes(transactionNumber)),
			tlv(0x04, serialNumber),
			SIGNATURE_ALGORITHM,
			tlv(0x02, unsignedBytes(signatureCounter)),
			tlv(0x02, unsignedBytes(logTime))
		);
		return out.toByteArray();
	}
	
	static byte[] tlv(int tag, byte[] value) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(tag);
		out.write(value.length);
		out.writeBytes(value);
		return out.toByteArray();
	}
	
	static byte[] unsignedBytes(long value) {
		byte[] b = BigInteger.valueOf(value).toByteArray();
		if (b.length > 1 && b[0] == 0) {
			return Arrays.copyOfRange(b, 1, b.length);
		}
		return b;
	}
	
	static boolean verify(X9ECParameters x9, BigInteger h, BigInteger r, BigInteger s, BigInteger dx, BigInteger dy) {
		BigInteger n = x9.getN();
		ECCurve curve = x9.getCurve();
		ECPoint g = x9.getG();
		ECPoint d = curve.createPoint(dx, dy);
		
		if (r.signum() <= 0 || r.compareTo(n) >= 0 || s.signum() <= 0 || s.compareTo(n) >= 0) {
			return false;
		}
		if (!d.isValid()) {
			return false;
		}
		
		BigInteger w = s.modInverse(n);
		BigInteger u1 = h.multiply(w).mod(n);
		BigInteger u2 = r.multiply(w).mod(n);
		ECPoint l = g.multiply(u1).add(d.multiply(u2)).normalize();
		BigInteger lx = l.getAffineXCoord().toBigInteger();
		
		System.out.println("Lx mod n = " + hex(lx.mod(n)));
		System.out.println("r        = " + hex(r));
		return lx.mod(n).equals(r);
	}
	
	static void selfCreatedExample() throws Exception {
		X9ECParameters x9 = ECNamedCurveTable.getByName("brainpoolP384r1");
		BigInteger n = x9.getN();
		ECPoint g = x9.getG();
		
		BigInteger d = new BigInteger("1154E898CC38E41B6CB83558DE410C8B7591733C01BF36040BD94BE726960FF471B7C3C829AF6E589C97DE2F2F3B6D9B", 16);
		BigInteger k = new BigInteger("3A1F0C77D2B45E8819AC6F03B7E14FE3DB7FCAFE0CBD10E8E826E03436D646AAEF87B2E247D4AF1E1D1C64F068CF45FF", 16).mod(n);
		
		ECPoint dPub = g.multiply(d).normalize();
		BigInteger dx = dPub.getAffineXCoord().toBigInteger();
		BigInteger dy = dPub.getAffineYCoord().toBigInteger();
		byte[] pk = dPub.getEncoded(false);
		
		long logTime = Instant.parse("2026-06-05T10:15:00.000Z").getEpochSecond();
		byte[] m = buildLogMessage("DEMO-KASSE-01", "Beleg^3.50_0.00_0.00_0.00_0.00^3.50:Bar", "Kassenbeleg-V1", 1, 1, logTime, sha256(pk));
		BigInteger h = new BigInteger(1, sha384(m));
		
		BigInteger r = g.multiply(k).normalize().getAffineXCoord().toBigInteger().mod(n);
		BigInteger s = k.modInverse(n).multiply(h.add(r.multiply(d))).mod(n);
		
		System.out.println("d  = " + hex(d));
		System.out.println("Dx = " + hex(dx));
		System.out.println("Dy = " + hex(dy));
		System.out.println("h  = " + HexFormat.of().formatHex(sha384(m)));
		System.out.println("r  = " + hex(r));
		System.out.println("s  = " + hex(s));
		boolean valid = verify(x9, h, r, s, dx, dy);
		System.out.println("Signatur " + (valid ? "GUELTIG" : "UNGUELTIG"));
	}
	
	static void writeAll(ByteArrayOutputStream out, byte[]... parts) {
		for (byte[] p : parts) out.writeBytes(p);
	}
	
	static byte[] sha256(byte[] data) throws Exception {
		return MessageDigest.getInstance("SHA-256").digest(data);
	}
	
	static byte[] sha384(byte[] data) throws Exception {
		return MessageDigest.getInstance("SHA-384").digest(data);
	}
	
	static byte[] hexBytes(String hex) {
		int len = hex.length();
		byte[] out = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			out[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
		}
		return out;
	}
	
	static String hex(BigInteger value) {
		return value.toString(16).toUpperCase();
	}
}
