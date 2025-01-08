package net.luis.k;

import net.luis.Sachbearbeiter;

public class SachbearbeiterAuswaehlenK {
	
	public String[] gibSachbearbeiterNamen() {
		return Sachbearbeiter.gibAlleNamen();
	}
	
	public Sachbearbeiter gibSachbearbeiter(String benutzername) {
		return Sachbearbeiter.gib(benutzername);
	}
}
