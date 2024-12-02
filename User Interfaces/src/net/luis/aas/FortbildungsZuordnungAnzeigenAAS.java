package net.luis.aas;

import net.luis.Auswaehlen;
import net.luis.k.FortbildungsZuordnungAnzeigenK;

public class FortbildungsZuordnungAnzeigenAAS {
	
	public static final FortbildungsZuordnungAnzeigenAAS INSTANZ = new FortbildungsZuordnungAnzeigenAAS();
	
	private final FortbildungsZuordnungAnzeigenK kontrolle = new FortbildungsZuordnungAnzeigenK();
	
	private FortbildungsZuordnungAnzeigenAAS() {}
	
	public void oeffnen() {
		String sachbearbeiter = SachbearbeiterAuswaehlenAAS.INSTANCE.selektiereSachbearbeiter();
		
		System.out.println("Welche Zuordnung möchten Sie anzeigen?");
		ZuordnungsTyp typ = ZuordnungsTyp.values()[Auswaehlen.optionAuswaehlen("Belegt", "Bestanden") - 1];
		if (typ == ZuordnungsTyp.BELEGT) {
			this.kontrolle.gibBelegteFortbildung(sachbearbeiter).forEach(System.out::println);
		} else if (typ == ZuordnungsTyp.BESTANDEN) {
			this.kontrolle.gibBestandeneFortbildung(sachbearbeiter).forEach(System.out::println);
		}
	}
	
	private enum ZuordnungsTyp {
		
		BELEGT, BESTANDEN;
	}
}
