package net.luis.k;

import net.luis.Sachbearbeiter;

public class SachbearbeiterBearbeitenK {
	
	public void schreibeSachbearbeiter(String alterBenutzername, String neuerBenutzername, String passwort, boolean istAdmin) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(alterBenutzername);
		sachbearbeiter.setzeBenutzername(neuerBenutzername);
		
		String altesPasswort = sachbearbeiter.gibPasswort();
		try {
			sachbearbeiter.setzePasswort(passwort);
			sachbearbeiter.setzeIstAdmin(istAdmin);
		} catch (Exception e) {
			sachbearbeiter.setzePasswort(altesPasswort);
			sachbearbeiter.setzeBenutzername(alterBenutzername);
			throw e;
		}
	}
	
	public boolean gibBerechtigung(String altenBenutzernamen) {
		return new SachbearbeiterK().gibBerechtigung(altenBenutzernamen);
	}
}
