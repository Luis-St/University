package net.luis.aas;

import net.luis.Eingabe;
import net.luis.Sachbearbeiter;
import net.luis.as.AdminAS;
import net.luis.as.NormalAS;
import net.luis.k.LoginK;

public class LoginAAS {
	
	private static LoginAAS OBJ = new LoginAAS();
	private LoginK kontrolle;
	Sachbearbeiter sachbearbeiterToLogin;
	
	private LoginAAS() {
		this.kontrolle = new LoginK();
	}
	
	public static LoginAAS getInstance() {
		return OBJ;
	}
	
	public void oeffnen() {
		
		System.out.println("Login Vorgang gestartet.");
		
		while (true) {
			try {
				String name = Eingabe.eingeben("Name eingeben:");
				sachbearbeiterToLogin = Sachbearbeiter.gib(name);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		
		String passwort = Eingabe.eingeben("Passwort eingeben:");
		int counter = 1;
		while (true) {
			if (counter > 2) {
				throw new IllegalArgumentException("Passwort zu oft falsch eingegeben.");
			}
			if (!sachbearbeiterToLogin.gibPasswort().equals(passwort)) {
				passwort = Eingabe.eingeben("Passwort falsch. \nPasswort eingeben:");
				counter++;
			} else {
				break;
			}
		}
		
		String admin = Eingabe.eingeben("Admin? Y/N");
		Boolean isAdmin = false;
		
		while (!admin.equals("Y") && !admin.equals("N")) {
			admin = Eingabe.eingeben("Falsche Eingabe. \nAdmin? Y/N");
		}
		if (admin.equals("Y")) {
			if (sachbearbeiterToLogin.istAdmin()) {
				System.out.println("Erfolgreich eingeloggt als Admin: " + sachbearbeiterToLogin.gibBenutzername());
				new AdminAS().oeffnen();
			} else {
				System.out.println("Erfolgreich eingeloggt als Nutzer: " + sachbearbeiterToLogin.gibBenutzername());
				new NormalAS().oeffnen();
			}
		} else {
			System.out.println("Erfolgreich eingeloggt als Nutzer: " + sachbearbeiterToLogin.gibBenutzername());
			new NormalAS().oeffnen();
		}
	}
}
