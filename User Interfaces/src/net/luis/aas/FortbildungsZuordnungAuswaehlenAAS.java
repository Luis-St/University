package net.luis.aas;

import net.luis.Auswaehlen;
import net.luis.k.FortbildungsZuordnungAuswaehlenK;

public class FortbildungsZuordnungAuswaehlenAAS {
	
	public static final FortbildungsZuordnungAuswaehlenAAS INSTANCE = new FortbildungsZuordnungAuswaehlenAAS();

	private FortbildungsZuordnungAuswaehlenK kontrolle = new FortbildungsZuordnungAuswaehlenK();
	
	private FortbildungsZuordnungAuswaehlenAAS() {}
	
	public void oeffnen() {}
}
