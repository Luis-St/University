package net.luis.aas;

import net.luis.Auswaehlen;
import net.luis.k.FortbildungAuswaehlenK;

public class FortbildungAuswaehlenAAS {
	
	public static final FortbildungAuswaehlenAAS INSTANCE = new FortbildungAuswaehlenAAS();
	
	private final FortbildungAuswaehlenK kontrolle = new FortbildungAuswaehlenK();
	
	private FortbildungAuswaehlenAAS() {}
	
	public String selektiereFortbildung() {
		System.out.println("Fortbildung auswählen:");
		String[] namen = this.kontrolle.gibAlleNamen();
		int index = Auswaehlen.optionAuswaehlen(namen);
		return namen[index];
	}
}
