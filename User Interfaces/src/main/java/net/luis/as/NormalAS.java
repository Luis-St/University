package net.luis.as;

import net.luis.Auswaehlen;
import net.luis.Eingabe;
import net.luis.aas.*;

public class NormalAS {
	
	private static final String[] MENUE = {
		"Sachbearbeiter bearbeiten",
		"Fortbildung zuordnen",
		"Fortbildungszuordnung löschen",
		"Fortbildungszuordnung anzeigen",
		"Logout"
	};
	
	public void oeffnen() {
		loop: while (true) {
			System.out.println("Normal-Menü:");
			int option = Auswaehlen.optionAuswaehlen(MENUE);
			switch (option) {
				case 1 -> this.sachbearbeiterBearbeiten();
				case 2 -> this.fortbildungZuordnen();
				case 3 -> this.fortbildungsZuordnungLoeschen();
				case 4 -> this.fortbildungsZuordnungAnzeigen();
				default -> {break loop;}
			}
			System.out.println();
		}
	}
	
	private void sachbearbeiterBearbeiten() {
		SachbearbeiterBearbeitenAAS.INSTANZ.oeffnen();
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
