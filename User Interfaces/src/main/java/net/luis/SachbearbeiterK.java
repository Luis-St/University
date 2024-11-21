package net.luis;

class SachbearbeiterK {
	String gibPasswort(String benutzername) {
		return Sachbearbeiter.gib(benutzername).gibPasswort();
	}
	
	boolean gibBerechtigung(String benutzername) {
		return Sachbearbeiter.gib(benutzername).istAdmin();		
	}

}
