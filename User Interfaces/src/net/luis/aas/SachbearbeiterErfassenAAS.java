package net.luis.aas;

import net.luis.Eingabe;
import net.luis.Sachbearbeiter;
import net.luis.k.SachbearbeiterErfassenK;

import javax.swing.*;

public class SachbearbeiterErfassenAAS {
	
	public static final SachbearbeiterErfassenAAS INSTANZ = new SachbearbeiterErfassenAAS();
	
	private final SachbearbeiterErfassenK kontrolle = new SachbearbeiterErfassenK();
	
	private SachbearbeiterErfassenAAS() {}
	
	public void oeffnen(JFrame fenster) {
		String name = Eingabe.eingeben("Name eingeben:");
		String passwort = Eingabe.eingeben("Passwort eingeben:");
		
		String admin;
		do {
			admin = Eingabe.eingeben("Admin? Y/N");
		} while (!"Y".equalsIgnoreCase(admin) && !"N".equalsIgnoreCase(admin));
		
		this.kontrolle.erzeugeSachbearbeiter(name, passwort, "Y".equalsIgnoreCase(admin));
		this.ausgefuehrt(name);
	}
	
	public void ausgefuehrt(String name) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(name);
		if (name != null) {
			System.out.println("Sachbearbeiter " + sachbearbeiter.gibBenutzername() + " erfolgreich erfasst");
		} else {
			System.out.println("Erfassen fehlgeschlagen");
		}
	}
}
