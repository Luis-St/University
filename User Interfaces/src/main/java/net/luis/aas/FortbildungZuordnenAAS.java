package net.luis.aas;

import net.luis.Auswaehlen;
import net.luis.k.FortbildungZuordnenK;
import net.luis.k.SachbearbeiterAuswaehlenK;

public class FortbildungZuordnenAAS {
	
	public static final FortbildungZuordnenAAS INSTANZ = new FortbildungZuordnenAAS();
	
	private final FortbildungZuordnenK kontrolle = new FortbildungZuordnenK();
	
	private FortbildungZuordnenAAS() {}
	
	public void oeffnen() {
		String sachbearbeiter = SachbearbeiterAuswaehlenAAS.INSTANCE.selektiereSachbearbeiter();
		String fortbildung = FortbildungAuswaehlenAAS.INSTANCE.selektiereFortbildung();
		
		System.out.println("Welche Zuordnung möchten Sie vornehmen?");
		ZuordnungsTyp typ = ZuordnungsTyp.values()[Auswaehlen.optionAuswaehlen("Belegt", "Bestanden") - 1];
		if (typ == ZuordnungsTyp.BELEGT) {
			this.kontrolle.belegeFortbildung(sachbearbeiter, fortbildung);
		} else if (typ == ZuordnungsTyp.BESTANDEN) {
			this.kontrolle.besteheFortbildung(sachbearbeiter, fortbildung);
		}
	}
	
	private enum ZuordnungsTyp {
		
		BELEGT, BESTANDEN;
	}
}
