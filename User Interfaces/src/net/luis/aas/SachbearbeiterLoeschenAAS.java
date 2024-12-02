package net.luis.aas;

import net.luis.Eingabe;
import net.luis.Sachbearbeiter;
import net.luis.k.SachbearbeiterLoeschenK;

import javax.swing.*;

public class SachbearbeiterLoeschenAAS {
	
	public static final SachbearbeiterLoeschenAAS INSTANZ = new SachbearbeiterLoeschenAAS();
	
	private final SachbearbeiterLoeschenK kontrolle = new SachbearbeiterLoeschenK();
	
	private SachbearbeiterLoeschenAAS() {}
	
	public void oeffnen(JFrame fenster) {
		String name = SachbearbeiterAuswaehlenAAS.INSTANCE.selektiereSachbearbeiter();
		try {
			this.kontrolle.loescheSachbearbeiter(name);
			this.ausgefuehrt(name);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void ausgefuehrt(String name) {
		if (Sachbearbeiter.gib(name) == null) {
			System.out.println("Löschen erfolgreich.");
		} else {
			System.out.println("Löschen fehlgeschlagen");
		}
	}
}
