package net.luis.aas;

import net.luis.Eingabe;
import net.luis.Sachbearbeiter;
import net.luis.k.SachbearbeiterBearbeitenK;

public class AdminSachbearbeiterBearbeitenAAS {
	
	public static final AdminSachbearbeiterBearbeitenAAS INSTANZ = new AdminSachbearbeiterBearbeitenAAS();
	
	private final SachbearbeiterBearbeitenK kontrolle = new SachbearbeiterBearbeitenK();
	
	private AdminSachbearbeiterBearbeitenAAS() {}
	
	public void oeffnen() {
		String name = SachbearbeiterAuswaehlenAAS.INSTANCE.selektiereSachbearbeiter();
		
		String username = Eingabe.eingeben("Neuer Benutzername eingeben: (^X to cancel, ^S not modify username)");
		if ("^X".equals(username)) {
			return;
		}
		if ("^S".equals(username)) {
			username = Sachbearbeiter.gib(name).gibBenutzername();
		}
		
		String passwort = Eingabe.eingeben("Neues Passwort eingeben: (^X to cancel, ^S not modify password)");
		if ("X".equals(passwort)) {
			return;
		}
		if ("^S".equals(passwort)) {
			passwort = Sachbearbeiter.gib(name).gibPasswort();
		}
		
		String admin;
		do {
			admin = Eingabe.eingeben("Soll der Sachbearbeiter Admin sein? Y/N (^X to cancel, ^S not modify admin)");
		} while (!"Y".equals(admin) && !"N".equals(admin) && !"^X".equals(admin) && !"^S".equals(admin));
		if ("^X".equals(admin)) {
			return;
		}
		if ("^S".equals(admin)) {
			admin = Sachbearbeiter.gib(name).istAdmin() ? "Y" : "N";
		}
		
		this.kontrolle.schreibeSachbearbeiter(name, username, passwort, "Y".equals(admin));
	}
}
