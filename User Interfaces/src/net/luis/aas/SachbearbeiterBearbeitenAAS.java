package net.luis.aas;

import net.luis.Eingabe;
import net.luis.Sachbearbeiter;
import net.luis.k.SachbearbeiterBearbeitenK;

import javax.swing.*;

public class SachbearbeiterBearbeitenAAS {
	
	public static final SachbearbeiterBearbeitenAAS INSTANZ = new SachbearbeiterBearbeitenAAS();
	
	private final SachbearbeiterBearbeitenK kontrolle = new SachbearbeiterBearbeitenK();
	
	private SachbearbeiterBearbeitenAAS() {}
	
	public void oeffnen(JFrame fenster) {
		String name = SachbearbeiterAuswaehlenAAS.INSTANCE.selektiereSachbearbeiter();
		System.out.println("Sachbearbeiter " + name + " bearbeiten:");
		
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(name);
		String username = Eingabe.eingeben("Neuer Benutzername eingeben: (X to cancel)");
		if ("X".equals(username)) {
			return;
		}
		
		this.kontrolle.schreibeSachbearbeiter(name, username, sachbearbeiter.gibPasswort(), sachbearbeiter.istAdmin());
		this.ausgefuehrt(name);
	}
	
	public void ausgefuehrt(String name) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(name);
		if (sachbearbeiter != null) {
			System.out.println("Bearbeiten erfolgreich, neuer Benutzername: " + sachbearbeiter.gibBenutzername());
		} else {
			System.out.println("Bearbeiten fehlgeschlagen");
		}
	}
}
