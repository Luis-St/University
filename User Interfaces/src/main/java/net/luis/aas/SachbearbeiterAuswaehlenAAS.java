package net.luis.aas;

import net.luis.k.SachbearbeiterAuswaehlenK;

public class SachbearbeiterAuswaehlenAAS {
	
	private static SachbearbeiterAuswaehlenAAS OBJ = new SachbearbeiterAuswaehlenAAS();
	private SachbearbeiterAuswaehlenK kontrolle;
	
	private SachbearbeiterAuswaehlenAAS() {
		this.kontrolle = new SachbearbeiterAuswaehlenK();
	}
	
	public static SachbearbeiterAuswaehlenAAS getInstance() {
		return OBJ;
	}
	
	public void oeffnen() {}
	
	public void praesentiereFortbildungenNamen() {}
	
	public void selektiereSachbearbeiter() {}
}
