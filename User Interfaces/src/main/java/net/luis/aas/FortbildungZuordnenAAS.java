package net.luis.aas;

import net.luis.k.FortbildungZuordnenK;

public class FortbildungZuordnenAAS {
	
	private static FortbildungZuordnenAAS OBJ = new FortbildungZuordnenAAS();
	FortbildungZuordnenK kontrolle;
	
	private FortbildungZuordnenAAS() {
		this.kontrolle = new FortbildungZuordnenK();
	}
	
	public static FortbildungZuordnenAAS getInstance() {
		return OBJ;
	}
}
