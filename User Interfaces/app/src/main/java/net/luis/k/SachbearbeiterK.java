package net.luis.k;

import net.luis.Sachbearbeiter;

public class SachbearbeiterK {
	
	public String gibPasswort(String benutzername) {
		return Sachbearbeiter.gib(benutzername).gibPasswort();
	}
	
	public boolean gibBerechtigung(String benutzername) {
		return Sachbearbeiter.gib(benutzername).istAdmin();
	}
}
