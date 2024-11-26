package net.luis.as;

import net.luis.Auswaehlen;
import net.luis.Eingabe;
import net.luis.aas.*;

public class AdminAS {
	
	private static final String[] MENUE = {
		"Sachbearbeiter erfassen",
		"Sachbearbeiter bearbeiten",
		"Sachbearbeiter loeschen",
		"Fortbildung zuordnen",
		"Fortbildungszuordnung löschen",
		"Fortbildungszuordnung anzeigen",
		"Logout"
	};
	
	public void oeffnen() {
		loop: while (true) {
			System.out.println("Admin-Menü:");
			int option = Auswaehlen.optionAuswaehlen(MENUE);
			switch (option) {
				case 0 -> this.sachbearbeiterErfassen();
				case 1 -> this.sachbearbeiterBearbeiten();
				case 2 -> this.sachbearbeiterLoeschen();
				case 3 -> this.fortbildungZuordnen();
				case 4 -> this.fortbildungsZuordnungLoeschen();
				case 5 -> this.fortbildungsZuordnungAnzeigen();
				default -> {break loop;}
			}
			System.out.println();
		}
	}
	
	private void sachbearbeiterErfassen() {
		SachbearbeiterErfassenAAS.INSTANZ.oeffnen();
	}
	
	private void sachbearbeiterLoeschen() {
		SachbearbeiterLoeschenAAS.INSTANZ.oeffnen();
	}
	
	private void sachbearbeiterBearbeiten() {
		AdminSachbearbeiterBearbeitenAAS.INSTANZ.oeffnen();
	}
	
	private void fortbildungZuordnen() {
		FortbildungZuordnenAAS.INSTANZ.oeffnen();
	}
	
	private void fortbildungsZuordnungLoeschen() {
		FortbildungsZuordnungLoeschenAAS.INSTANZ.oeffnen();
	}
	
	private void fortbildungsZuordnungAnzeigen() {
		FortbildungsZuordnungAnzeigenAAS.INSTANZ.oeffnen();
	}
}
