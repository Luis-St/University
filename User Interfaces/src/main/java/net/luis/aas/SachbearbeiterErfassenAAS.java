package net.luis.aas;

import net.luis.Eingabe;
import net.luis.k.SachbearbeiterErfassenK;

public class SachbearbeiterErfassenAAS {
	
	private static SachbearbeiterErfassenAAS OBJ = new SachbearbeiterErfassenAAS();
	SachbearbeiterErfassenK kontrolle;
	
	private SachbearbeiterErfassenAAS() {
		this.kontrolle = new SachbearbeiterErfassenK();
	}
	
	public static SachbearbeiterErfassenAAS getInstance() {
		return OBJ;
	}
	
	public void oeffnen() {
		String name = Eingabe.eingeben("Name eingeben:");
		String passwort = Eingabe.eingeben("Passwort eingeben:");
		String admin = Eingabe.eingeben("Admin? Y/N");
		Boolean isAdmin = false;
		
		while (!admin.equals("Y") && !admin.equals("N")) {
			admin = Eingabe.eingeben("Admin? Y/N");
		}
		if (admin == "Y") {
			isAdmin = true;
		}
		
		kontrolle.erzeugeSachbearbeiter(name, passwort, isAdmin);
	}
	
}
