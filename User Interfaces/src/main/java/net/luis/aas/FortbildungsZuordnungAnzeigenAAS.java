package net.luis.aas;

import net.luis.k.FortbildungsZuordnungAnzeigenK;

public class FortbildungsZuordnungAnzeigenAAS {
	
	private static FortbildungsZuordnungAnzeigenAAS OBJ = new FortbildungsZuordnungAnzeigenAAS();
	private FortbildungsZuordnungAnzeigenK kontrolle;
	
	private FortbildungsZuordnungAnzeigenAAS() {
		this.kontrolle = new FortbildungsZuordnungAnzeigenK();
	}
	
	public static FortbildungsZuordnungAnzeigenAAS getInstance() {
		return OBJ;
	}
	
	public void oeffnen() {}
}
