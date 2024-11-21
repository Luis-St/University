package net.luis;

class SachbearbeiterLoeschenK {
	String loescheSachbearbeiter(String benutzername) {
		return Sachbearbeiter.gib(benutzername).loesche();
	}

}
