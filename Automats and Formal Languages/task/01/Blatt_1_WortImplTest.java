package hob;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Blatt_1_WortImplTest {
	
	@Test
	void testKonstruktor() {
		String s = "Automaten und Formale Sprachen";
		Wort w1 = new WortImpl(s);
		for (int i = 1; i <= w1.laenge(); i++) {
			assertEquals(w1.position(i), s.charAt(i - 1));
		}
		Wort w2 = new WortImpl("");
		Wort w3 = new WortImpl("");
		assertEquals(w2, w3);
	}
	
	@Test
	void testLaenge() {
		assertEquals(30, new WortImpl("Automaten und Formale Sprachen").laenge());
		assertEquals(1, new WortImpl("A").laenge());
		assertEquals(3, new WortImpl("   ").laenge());
		assertEquals(0, new WortImpl("").laenge());
	}
	
	@Test
	void testPosition() {
		assertEquals('a', new WortImpl("a").position(1));
		assertEquals('a', new WortImpl("ba").position(2));
		assertEquals('c', new WortImpl("abc").position(3));
		assertEquals(' ', new WortImpl("          ").position(6));
		assertNotEquals('x', new WortImpl("          ").position(6));
		assertNotEquals('c', new WortImpl("abc").position(1));
		assertNotEquals('c', new WortImpl("abc").position(2));
	}
	
	@Test
	void testEquals() {
		assertEquals(new WortImpl("Automaten"), new WortImpl("Automaten"));
		assertEquals(new WortImpl(""), new WortImpl(""));
		assertEquals(new WortImpl(" "), new WortImpl(" "));
		assertNotEquals(new WortImpl(""), new WortImpl(" "));
		assertNotEquals(new WortImpl(" "), new WortImpl(""));
	}
	
	@Test
	void testKonkateniere() {
		assertEquals(new WortImpl(""), new WortImpl("").concat(new WortImpl("").concat(new WortImpl(""))));
		assertEquals(new WortImpl("xyz"), new WortImpl("xyz").concat(new WortImpl("")));
		assertEquals(new WortImpl("xyz"), new WortImpl("").concat(new WortImpl("xyz")));
		assertEquals(new WortImpl("xyz123"), new WortImpl("xyz").concat(new WortImpl("123")));
		assertEquals(new WortImpl("abc"), new WortImpl("a").concat(new WortImpl("b").concat(new WortImpl("c"))));
		assertNotEquals(new WortImpl("ba"), new WortImpl("a").concat(new WortImpl("b")));
		assertNotEquals(new WortImpl("b"), new WortImpl(" ").concat(new WortImpl("b")));
		assertNotEquals(new WortImpl("b"), new WortImpl(" ").concat(new WortImpl("b")));
		assertEquals(new WortImpl("b"), new WortImpl("").concat(new WortImpl("b")));
		assertEquals(new WortImpl("b"), new WortImpl("b").concat(new WortImpl("")));
	}
	
	@Test
	void testAnzahl() {
		Wort w1 = new WortImpl("___ aaabbbcccaaa");
		assertEquals(6, w1.anzahl('a'));
		assertEquals(0, w1.anzahl('d'));
		assertEquals(1, w1.anzahl(' '));
		assertEquals(3, w1.anzahl('_'));
		assertEquals(0, new WortImpl("").anzahl('d'));
	}
	
	@Test
	void testTausche() {
		Wort w1 = new WortImpl("hoch");
		Wort w2 = new WortImpl("aaabbbccc");
		
		assertEquals(new WortImpl("_oc_"), w1.tausche('h', '_'));
		assertEquals(new WortImpl("aaabbbccc"), w2.tausche('d', 'c'));
		assertEquals(new WortImpl("aaabbbccc"), w2.tausche('c', 'c'));
		assertEquals(w2, w2.tausche('a', 'd').tausche('d', 'a'));
	}
	
	@Test
	void testIstTeilWort() {
		Wort w1 = new WortImpl("abcdef");
		Wort w2 = new WortImpl("aaaabbbbb");
		
		assertEquals(1, w1.istTeilwortVon(w1));
		assertEquals(0, w1.istTeilwortVon(new WortImpl("aaabbbcccd")));
		assertEquals(3, w1.istTeilwortVon(new WortImpl("cde")));
		assertEquals(3, w2.istTeilwortVon(new WortImpl("aabb")));
		assertEquals(0, w2.istTeilwortVon(new WortImpl("aaaaa")));
		assertEquals(0, w2.istTeilwortVon(new WortImpl("bbbbbb")));
		assertEquals(5, w2.istTeilwortVon(new WortImpl("bbbbb")));
		assertEquals(3, new WortImpl("ababba").istTeilwortVon(new WortImpl("abb")));
	}
	
	@Test
	void testErsetze() {
		Wort w = new WortImpl("abcdefg");
		Wort w1 = new WortImpl("abcdefg");
		Wort w2 = new WortImpl("abcdefgh");
		
		assertEquals(w, w.ersetze(w1, w1));
		assertEquals(w, w.ersetze(w, w1));
		assertEquals(w2, w.ersetze(w1, w2));
		assertEquals(new WortImpl("abdcefg"), w.ersetze(new WortImpl("cd"), new WortImpl("dc")));
		
		Wort v1 = new WortImpl("Winter");
		Wort v2 = v1.ersetze(new WortImpl("te"), new WortImpl("ne"));
		assertEquals(new WortImpl("Winner"), v2);
		assertEquals(new WortImpl("Sommer"), v1.ersetze(v1, new WortImpl("Sommer")));
		assertEquals(new WortImpl("Winter"),
			v1.ersetze(v1, new WortImpl("Sommer")).ersetze(new WortImpl("Sommer"), new WortImpl("Winter")));
	}
	
	@Test
	void testteilwort() {
		Wort w1 = new WortImpl("Guten Morgen");
		Wort w2 = w1.teilwort(7, 6);
		assertEquals(new WortImpl("Morgen"), w2);
		
		Wort w3 = w2.teilwort(2, 2);
		assertEquals(new WortImpl("or"), w3);
		assertEquals(new WortImpl(""), w1.teilwort(7, 6).teilwort(2, 0));
		
		assertEquals(new WortImpl("Guten Morgen"), w1.teilwort(1, 12));
		assertNotEquals(new WortImpl("Guten Morgen"), w1.teilwort(1, 11));
	}
}
