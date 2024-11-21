package net.luis;


public class FortbildungZuordnenK {
	
	public void belegeFortbildung(String sachbearbeiterName, String fortbildungName) {
		Fortbildung fortbildung = Fortbildung.gib(fortbildungName);
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(sachbearbeiterName);
		sachbearbeiter.belege(fortbildung);
	}
	
	public void besteheFortbildung(String sachbearbeiterName, String fortbildungName) {
		Fortbildung fortbildung = Fortbildung.gib(fortbildungName);
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(sachbearbeiterName);
		sachbearbeiter.bestehe(fortbildung);
	}
}
