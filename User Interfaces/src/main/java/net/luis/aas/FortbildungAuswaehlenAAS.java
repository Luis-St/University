package net.luis.aas;

import net.luis.k.FortbildungAuswaehlenK;

public class FortbildungAuswaehlenAAS {
	
	private static FortbildungAuswaehlenAAS OBJ = new FortbildungAuswaehlenAAS();
	private FortbildungAuswaehlenK kontrolle;
	
	private FortbildungAuswaehlenAAS() {
		this.kontrolle = new FortbildungAuswaehlenK();
	}
	
	public static FortbildungAuswaehlenAAS getInstance() {
		return OBJ;
	}
	
	public void oeffnen() {}
	
	public void praesentiereFortbildungenNamen() {}
	
	public void selektiereFortbildung() {}
}
