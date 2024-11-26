package net.luis.aas;

import net.luis.Auswaehlen;
import net.luis.k.SachbearbeiterAuswaehlenK;

public class SachbearbeiterAuswaehlenAAS {
	
	public static final SachbearbeiterAuswaehlenAAS INSTANCE = new SachbearbeiterAuswaehlenAAS();
	
	private final SachbearbeiterAuswaehlenK kontrolle = new SachbearbeiterAuswaehlenK();
	
	public String selektiereSachbearbeiter() {
		System.out.println("Sachbearbeiter auswählen:");
		String[] namen = this.kontrolle.gibSachbearbeiterNamen();
		int index = Auswaehlen.optionAuswaehlen(namen);
		return namen[index];
	}
}
