package net.luis.k;

import net.luis.Sachbearbeiter;

public class SachbearbeiterLoeschenK {
	
	public String loescheSachbearbeiter(String benutzername) {
		return Sachbearbeiter.gib(benutzername).loesche();
	}
}
