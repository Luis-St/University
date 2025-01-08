package net.luis.k;

import net.luis.Sachbearbeiter;

public class SachbearbeiterLoeschenK {
	
	public void loescheSachbearbeiter(String benutzername) {
		Sachbearbeiter.gib(benutzername).loesche();
	}
}
