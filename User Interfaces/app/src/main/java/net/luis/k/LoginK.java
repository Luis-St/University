package net.luis.k;

import net.luis.Sachbearbeiter;

public class LoginK {
	
	public static boolean passwortPasstZuBenutzername(String benutzername, String passwort) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(benutzername);
		if (sachbearbeiter == null) {
			return false;
		} else {
			return sachbearbeiter.gibPasswort().equals(passwort);
		}
	}
	
	public static boolean gewaehlteBerechtigungPasstZuSachbearbeiter(String benutzername, boolean istAdmin) {
		if (istAdmin) {
			return istAdmin(benutzername);
		} else {
			return true;
		}
	}
	
	public static boolean istAdmin(String benutzername) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(benutzername);
		if (sachbearbeiter == null) {
			return false;
		}
		return sachbearbeiter.istAdmin();
	}
}
