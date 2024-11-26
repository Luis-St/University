package net.luis.k;

import net.luis.Fortbildung;
import net.luis.Sachbearbeiter;

public class FortbildungsZuordnungLoeschenK {
	
	public void loescheBelegteFortbildung(String sachbearbeiter, String fortbildung) {
		Sachbearbeiter.gib(sachbearbeiter).fortbildungsbelegungLoeschen(Fortbildung.gib(fortbildung));
	}
	
	public void loescheBestandeneFortbildung(String sachbearbeiter, String fortbildung) {
		Sachbearbeiter.gib(sachbearbeiter).fortbildungsbestehenLoeschen(Fortbildung.gib(fortbildung));
	}
}
