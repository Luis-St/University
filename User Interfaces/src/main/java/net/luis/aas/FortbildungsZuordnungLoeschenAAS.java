package net.luis.aas;

import net.luis.Auswaehlen;
import net.luis.k.FortbildungsZuordnungLoeschenK;

/**
 *
 * @author Luis-St
 *
 */

public class FortbildungsZuordnungLoeschenAAS {
	
	public static final FortbildungsZuordnungLoeschenAAS INSTANZ = new FortbildungsZuordnungLoeschenAAS();
	
	private FortbildungsZuordnungLoeschenK kontrolle = new FortbildungsZuordnungLoeschenK();
	
	private FortbildungsZuordnungLoeschenAAS() {}
	
	public void oeffnen() {
		String sachbearbeiter = SachbearbeiterAuswaehlenAAS.INSTANCE.selektiereSachbearbeiter();
		String fortbildung = FortbildungAuswaehlenAAS.INSTANCE.selektiereFortbildung();
		
		System.out.println("Aus welcher Zuordnung möchten Sie die Fortbildung löschen?");
		ZuordnungsTyp typ = ZuordnungsTyp.values()[Auswaehlen.optionAuswaehlen("Belegt", "Bestanden")];
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
