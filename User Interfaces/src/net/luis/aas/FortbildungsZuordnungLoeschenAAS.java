package net.luis.aas;

import net.luis.Auswaehlen;
import net.luis.k.FortbildungsZuordnungLoeschenK;

import javax.swing.*;

public class FortbildungsZuordnungLoeschenAAS {
	
	public static final FortbildungsZuordnungLoeschenAAS INSTANZ = new FortbildungsZuordnungLoeschenAAS();
	
	private FortbildungsZuordnungLoeschenK kontrolle = new FortbildungsZuordnungLoeschenK();
	
	private FortbildungsZuordnungLoeschenAAS() {}
	
	public void oeffnen(JFrame fenster) {
		String sachbearbeiter = SachbearbeiterAuswaehlenAAS.INSTANCE.selektiereSachbearbeiter();
		String fortbildung = FortbildungAuswaehlenAAS.INSTANCE.selektiereFortbildung();
		
		System.out.println("Aus welcher Zuordnung möchten Sie die Fortbildung löschen?");
		ZuordnungsTyp typ = ZuordnungsTyp.values()[Auswaehlen.optionAuswaehlen("Belegt", "Bestanden") - 1];
		if (typ == ZuordnungsTyp.BELEGT) {
			this.kontrolle.loescheBelegteFortbildung(sachbearbeiter, fortbildung);
		} else if (typ == ZuordnungsTyp.BESTANDEN) {
			this.kontrolle.loescheBestandeneFortbildung(sachbearbeiter, fortbildung);
		}
	}
	
	private enum ZuordnungsTyp {
		
		BELEGT, BESTANDEN;
	}
}
