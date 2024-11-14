package net.luis;

/**
 *
 * @author Luis-St
 *
 */

public class SachbearbeiterErfassenK {
	
	Sachbearbeiter erzeugeSachbearbeiter(String benutzername, String passwort, boolean istAdmin) {
		Sachbearbeiter sachbearbeiter = new Sachbearbeiter(benutzername, passwort, istAdmin);
		Sachbearbeiter.fuegeHinzu(sachbearbeiter);
		return sachbearbeiter;
	}
}
