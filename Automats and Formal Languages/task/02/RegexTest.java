package task2;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class RegexTest {
	
	@Test
	public void testGleitkommazahlen() {
		String gleitkomma = "[+-]?(([123456789]\\d*)(\\.\\d+)?|0(\\.\\d+)?)([eE]\\d+)?";
		
		assertTrue(Pattern.matches(gleitkomma, "22"));
		assertTrue(Pattern.matches(gleitkomma, "+2"));
		assertTrue(Pattern.matches(gleitkomma, "-10000000"));
		assertTrue(Pattern.matches(gleitkomma, "2244444444"));
		assertTrue(Pattern.matches(gleitkomma, "+2.2"));
		assertTrue(Pattern.matches(gleitkomma, "-23.211111"));
		assertTrue(Pattern.matches(gleitkomma, "-23e1"));
		assertTrue(Pattern.matches(gleitkomma, "23E1"));
		assertTrue(Pattern.matches(gleitkomma, "-23E123456634"));
		assertTrue(Pattern.matches(gleitkomma, "-211113.124566E123456634"));
		assertTrue(Pattern.matches(gleitkomma, "0"));
		assertTrue(Pattern.matches(gleitkomma, "0.0"));
		
		assertFalse(Pattern.matches(gleitkomma, "00"));
		assertFalse(Pattern.matches(gleitkomma, "1."));
		assertFalse(Pattern.matches(gleitkomma, "|1"));
		assertFalse(Pattern.matches(gleitkomma, "|245"));
		assertFalse(Pattern.matches(gleitkomma, "23e"));
		assertFalse(Pattern.matches(gleitkomma, "23f1"));
		assertFalse(Pattern.matches(gleitkomma, ""));
		assertFalse(Pattern.matches(gleitkomma, ".0"));
		assertFalse(Pattern.matches(gleitkomma, "7..0"));
		assertFalse(Pattern.matches(gleitkomma, "e4"));
		assertFalse(Pattern.matches(gleitkomma, "++e4"));
		assertFalse(Pattern.matches(gleitkomma, "+-12"));
		assertFalse(Pattern.matches(gleitkomma, "+ 12"));
		assertFalse(Pattern.matches(gleitkomma, "1:0"));
		assertFalse(Pattern.matches(gleitkomma, "1.1.0"));
		
	}
	
	@Test
	public void testZeitpunkt() {
		String zeitpunkt = "\\d{4}-(0[123456789]|1[012])-([012]\\d|3[01])T(0?\\d|1\\d|2[01234]):\\d{2}:\\d{2}:\\d{3}";
		
		assertTrue(Pattern.matches(zeitpunkt, "2012-09-30T23:28:51:544"));
		assertTrue(Pattern.matches(zeitpunkt, "2012-12-30T12:28:51:544"));
		assertTrue(Pattern.matches(zeitpunkt, "0000-01-01T00:00:00:000"));
		assertTrue(Pattern.matches(zeitpunkt, "2020-12-31T23:59:59:999"));
		
		assertFalse(Pattern.matches(zeitpunkt, "    -  -  T  :  :  :   "));
		assertFalse(Pattern.matches(zeitpunkt, "2012-20-30T23:28:51:544"));
		assertFalse(Pattern.matches(zeitpunkt, "2012-10-32T23:28:51:544"));
		assertFalse(Pattern.matches(zeitpunkt, "012-12-30T22:28:51:544"));
		assertFalse(Pattern.matches(zeitpunkt, " 012-12-30T22:28:51:544"));
		assertFalse(Pattern.matches(zeitpunkt, "2012-00-30T22:28:51:544"));
		assertFalse(Pattern.matches(zeitpunkt, "2012-12-30t5:28:51:544"));
		assertFalse(Pattern.matches(zeitpunkt, "2012-12-30T30:28:51:544"));
		assertFalse(Pattern.matches(zeitpunkt, "2012-12-30T00:8:51:544"));
		assertFalse(Pattern.matches(zeitpunkt, "2012-12-30T21:28:1:544"));
		assertFalse(Pattern.matches(zeitpunkt, "2012-12-30T21:28:511:544"));
		assertFalse(Pattern.matches(zeitpunkt, "2012-12-30T21:28:51:5440"));
	}
	
	@Test
	public void testKFZKennzeichen() {
		String kennzeichen = ".{1,3} [A-Za-z]{1,2} ([1-9]\\d{0,2} [HE]|[1-9]\\d{0,3})$";
		
		assertTrue(Pattern.matches(kennzeichen, "VS HH 1000"));
		assertTrue(Pattern.matches(kennzeichen, "HH HH 1"));
		assertTrue(Pattern.matches(kennzeichen, "H HH 1"));
		assertTrue(Pattern.matches(kennzeichen, "HH HH 1 H"));
		assertTrue(Pattern.matches(kennzeichen, "T� HH 1 H"));
		
		assertFalse(Pattern.matches(kennzeichen, "HH HH 1 "));
		assertFalse(Pattern.matches(kennzeichen, "VS VS 0"));
		assertFalse(Pattern.matches(kennzeichen, "VS VS1 1"));
		assertFalse(Pattern.matches(kennzeichen, "VS 1"));
		assertFalse(Pattern.matches(kennzeichen, "VS ABC 1"));
		assertFalse(Pattern.matches(kennzeichen, "VS HH 0"));
		assertFalse(Pattern.matches(kennzeichen, "VS HH 10000"));
		assertFalse(Pattern.matches(kennzeichen, "VS HH 99 EH"));
		assertFalse(Pattern.matches(kennzeichen, "HVSA HH 1 "));
		assertFalse(Pattern.matches(kennzeichen, "HH HH 01"));
	}
}
