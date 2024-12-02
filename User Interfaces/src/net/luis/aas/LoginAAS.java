package net.luis.aas;

import net.luis.Eingabe;
import net.luis.Sachbearbeiter;
import net.luis.as.AdminAS;
import net.luis.as.NormalAS;
import net.luis.k.LoginK;

public class LoginAAS {
	
	public static final LoginAAS INSTANZ = new LoginAAS();
	
	Sachbearbeiter sachbearbeiter;
	
	private LoginAAS() {}
	
	public void oeffnen() {
		System.out.println("Login Vorgang gestartet.");
		do {
			String input = Eingabe.eingeben("Name eingeben:");
			try {
				this.sachbearbeiter = Sachbearbeiter.gib(input);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (this.sachbearbeiter == null);
		
		for (int i = 0; true; i++) {
			String passwort = Eingabe.eingeben("Passwort eingeben:");
			if (LoginK.passwortPasstZuBenutzername(this.sachbearbeiter.gibBenutzername(), passwort)) {
				break;
			}
			if (i == 2) {
				throw new IllegalArgumentException("Passwort zu oft falsch eingegeben.");
			}
			System.out.println("Passwort falsch!");
		}
		
		String admin;
		do {
			admin = Eingabe.eingeben("Einloggen als Admin? Y/N");
		} while (!"Y".equalsIgnoreCase(admin) && !"N".equalsIgnoreCase(admin));
		
		if (!LoginK.gewaehlteBerechtigungPasstZuSachbearbeiter(this.sachbearbeiter.gibBenutzername(), "Y".equalsIgnoreCase(admin))) {
			throw new IllegalStateException("Berechtigung passt nicht zum Sachbearbeiter.");
		}
		
		if ("Y".equalsIgnoreCase(admin)) {
			if (LoginK.istAdmin(this.sachbearbeiter.gibBenutzername())) {
				System.out.println("Erfolgreich eingeloggt als Admin: " + this.sachbearbeiter.gibBenutzername());
				new AdminAS().oeffnen();
			} else {
				throw new IllegalStateException("Nicht berechtigt als Admin einzuloggen.");
			}
		} else {
			System.out.println("Erfolgreich eingeloggt als Nutzer: " + this.sachbearbeiter.gibBenutzername());
			new NormalAS().oeffnen();
		}
	}
}
