package net.luis.aas;

import net.luis.Eingabe;
import net.luis.Sachbearbeiter;
import net.luis.k.SachbearbeiterLoeschenK;

public class SachbearbeiterLoeschenAAS {
	
	private static SachbearbeiterLoeschenAAS OBJ = new SachbearbeiterLoeschenAAS();
	SachbearbeiterLoeschenK kontrolle;
	
	private SachbearbeiterLoeschenAAS() {
		this.kontrolle = new SachbearbeiterLoeschenK();
	}
	
	public static SachbearbeiterLoeschenAAS getInstance() {
		return OBJ;
	}
	
	public void oeffnen() {
		String name = Eingabe.eingeben("Zu löschender Nutzer:");
		try {
			kontrolle.loescheSachbearbeiter(name);
			ausgefuehrt(name);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void ausgefuehrt(String name) {
		try {
			Sachbearbeiter.gib(name);
			System.out.println("Löschen fehlgeschlagen");
		} catch (IllegalArgumentException e) {
			System.out.println("Löschen erfolgreich.");
		}
	}
}
