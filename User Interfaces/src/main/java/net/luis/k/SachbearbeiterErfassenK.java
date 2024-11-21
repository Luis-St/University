package net.luis.k;

import net.luis.Sachbearbeiter;

public class SachbearbeiterErfassenK {
	
	public String erzeugeSachbearbeiter(String benutzername, String
		passwort, boolean istAdmin) {
		String fehlermeldung = Sachbearbeiter.erzeuge(benutzername, passwort, istAdmin);
		
		return fehlermeldung;
	}
}
