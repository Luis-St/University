package net.luis.k;

import net.luis.Sachbearbeiter;

public class SachbearbeiterErfassenK {
	
	public void erzeugeSachbearbeiter(String benutzername, String passwort, boolean istAdmin) {
		Sachbearbeiter.erzeuge(benutzername, passwort, istAdmin);
	}
}
