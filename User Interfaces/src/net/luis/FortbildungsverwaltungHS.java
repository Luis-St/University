package net.luis;

import net.luis.aas.LoginAAS;

import java.util.Scanner;

public class FortbildungsverwaltungHS {
	
	public static void main(String[] args) { // aaAA11&&aa
		Sachbearbeiter.einlesen();
		Fortbildung mathematik1 = new Fortbildung("Mathematik 1");
		Fortbildung allgemeineBwl = new Fortbildung("Allgemeine BWL");
		Fortbildung mathematik2 = new Fortbildung("Mathematik 2", mathematik1);
		new Fortbildung("Kostenrechnung", mathematik2, allgemeineBwl);
		while (true) {
			try {
				LoginAAS.INSTANZ.oeffnen();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				String eingabe = Eingabe.eingeben("Moechten Sie es noch einmal versuchen? (j/n)");
				if (!"j".equalsIgnoreCase(eingabe)) {
					break;
				}
			}
		}
		Sachbearbeiter.abspeichern();
	}
}
