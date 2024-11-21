package net.luis.aas;

import net.luis.k.FortbildungsZuordnungAuswaehlenK;

public class FortbildungsZuordnungAuswaehlenAAS {
	
	private static FortbildungsZuordnungAuswaehlenAAS OBJ = new FortbildungsZuordnungAuswaehlenAAS();
	private FortbildungsZuordnungAuswaehlenK kontrolle;
	
	private FortbildungsZuordnungAuswaehlenAAS() {
		this.kontrolle = new FortbildungsZuordnungAuswaehlenK();
	}
	
	public static FortbildungsZuordnungAuswaehlenAAS getInstance() {
		return OBJ;
	}
	
	public void oeffnen() {}
}
