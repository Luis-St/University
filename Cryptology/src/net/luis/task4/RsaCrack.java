package net.luis.task4;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

import java.math.BigInteger;
import java.util.Base64;

public class RsaCrack {
	
	public static void main(String[] args) throws Exception {
		BigInteger n = new BigInteger(
			"603635800462885598752877986931302412146569928178844383964012690494145791005837376076174956672633253393368357332775486629226884384657324983073858320747992574814527314651346318764662938648301976664917085352537795986934731278817785521253567908909194480527235299279423377702855024837737888225969280018765445316732297326259224750147529734098920307190109927913954435358077910210477876424858420743806553245466999679450209361802058731364394477196838039833145113984336912015954404540562698432623864445784189635526131388914987602891691429745209273725222618760768310111314169988069853955962416415647510403230735611574654862642397447893433693649047981275581451024688815071538627203100429417296455395504954838745218763996916719092954393858447636349786607281160125544329254617975957879313446070104945636734204599284305853069675109886657390437943388634896025713479960797425129942945700488088471960793676529786437393043777530344126120758377405497987417733126976914746066263620666935215239546106109856192882398760934799648321207633227704720958677762861197414922696493678860723170500062091690033908352468083594947176516487473750989146427763615551639465233844930053988421117977694664942834786092551246442716142742650322301837949024610729487979477095739"
		);
		BigInteger e = BigInteger.valueOf(65537);
		BigInteger cRsa = new BigInteger(
			"121441247743761641396283800868073070588217863441664480968027590062509845187683489964236539681227824879015146127630594706677158668178896719116782972670933501807288464416719483895269459039403600995721565415985013699606091603046542290299609889445533494572012750047184066706806051482174421539602123073930876290425809033898477942258638748819898522774202901547863848296984929471911211516955326776084511831902887387382757016632909093141306378975340251964636329257911683048141943298052267711812183459525054201236200188889363052900979184727099767169535468501017554429095164874136785142608032837438213706696759244618193525709883510214718317014048698767492772962855626539067236196035499027452527378540577141733075709746033530997849899980536367174307849434833207030475358220104290538612550808639472042084368892998822641551972589932190208605422806604511753501955535041866526074071487776850802498967253792828337011243937577818720417884343208025962433841778314931854045750812996815942263850960998766654531733970879194936848758718592569255436463906878908400649916775746502442690000661470161099894904848213042083927327128468535942796303306578890134560201949263584502790532126813585791099219911912757619988047017488169841814208213719776150317693322789"
		);
		
		System.out.println("n bit length: " + n.bitLength());
		System.out.println("e = " + e);
		System.out.println();
		
		BigInteger sqrtN = n.sqrt();
		BigInteger a = sqrtN.add(BigInteger.ONE);
		BigInteger p, q;
		int iterations = 0;
		
		while (true) {
			iterations++;
			BigInteger bSq = a.multiply(a).subtract(n);
			BigInteger[] sqrtResult = bSq.sqrtAndRemainder();
			if (sqrtResult[1].equals(BigInteger.ZERO)) {
				BigInteger b = sqrtResult[0];
				p = a.add(b);
				q = a.subtract(b);
				break;
			}
			a = a.add(BigInteger.ONE);
			if (iterations > 1000000) throw new RuntimeException("Fermat failed");
		}
		
		System.out.println("Faktorisierung nach " + iterations + " Iteration(en)");
		System.out.println("p = " + p);
		System.out.println("q = " + q);
		System.out.println("p bit length: " + p.bitLength());
		System.out.println("q bit length: " + q.bitLength());
		System.out.println("p*q == n: " + p.multiply(q).equals(n));
		System.out.println("|p-q| bit length: " + p.subtract(q).abs().bitLength());
		System.out.println();
		
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		BigInteger d = e.modInverse(phi);
		System.out.println("d = " + d);
		System.out.println("d bit length: " + d.bitLength());
		System.out.println();
		
		BigInteger kInt = cRsa.modPow(d, n);
		byte[] kBytes = toFixedLength(kInt, 16);
		System.out.println("AES-Schlüssel k (hex): " + bytesToHex(kBytes));
		System.out.println();
		
		byte[] aesCT = Base64.getDecoder().decode("OCZaBxssu0HVEGnc10auq67OzKcEqQ1jtU7x+fq/L28=");
		byte[] iv = new byte[16];
		
		System.out.println("AES-CT (hex): " + bytesToHex(aesCT));
		System.out.println("IV (hex): " + bytesToHex(iv));
		
		BufferedBlockCipher cipher = new BufferedBlockCipher(
			CBCBlockCipher.newInstance(AESEngine.newInstance())
		);
		cipher.init(false, new ParametersWithIV(new KeyParameter(kBytes), iv));
		
		byte[] output = new byte[cipher.getOutputSize(aesCT.length)];
		int len = cipher.processBytes(aesCT, 0, aesCT.length, output, 0);
		len += cipher.doFinal(output, len);
		
		// 1-0-Padding entfernen: letztes 0x80 Byte finden, alles danach sind 0x00
		int padStart = len;
		for (int i = len - 1; i >= 0; i--) {
			if (output[i] == (byte) 0x80) {
				padStart = i;
				break;
			} else if (output[i] != 0x00) {
				break;
			}
		}
		
		byte[] plaintext = new byte[padStart];
		System.arraycopy(output, 0, plaintext, 0, padStart);
		
		System.out.println("\nEntschlüsselter Plaintext (hex): " + bytesToHex(plaintext));
		System.out.println("Entschlüsselter Plaintext: " + new String(plaintext, "UTF-8"));
	}
	
	static byte[] toFixedLength(BigInteger val, int length) {
		byte[] raw = val.toByteArray();
		byte[] result = new byte[length];
		int srcStart = Math.max(0, raw.length - length);
		int dstStart = Math.max(0, length - raw.length);
		System.arraycopy(raw, srcStart, result, dstStart, Math.min(raw.length, length));
		return result;
	}
	
	static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) sb.append(String.format("%02X", b));
		return sb.toString();
	}
}
