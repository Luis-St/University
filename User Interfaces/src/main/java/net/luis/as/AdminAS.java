package net.luis.as;

import net.luis.Eingabe;
import net.luis.aas.*;

public class AdminAS {
	
	public void oeffnen() {
		while (true) {
			System.out.println("--------------------------------");
			System.out.println("Menue");
			System.out.println("--------------------------------");
			System.out.println("Folgende Funktionen stehen zur Verfügung:");
			System.out.println("Sachbearbeiter erfassen (SE)");
			System.out.println("Sachbearbeiter bearbeiten (SB)");
			System.out.println("Sachbearbeiter loeschen (SL)");
			System.out.println("Fortbildung zuordnen (FZ)");
			System.out.println("Fortbildungszuordnung löschen (FL)");
			System.out.println("Fortbildungszuordnung anzeigen (FA)");
			System.out.println("Logout (X)");
			
			String eingabe = Eingabe.eingeben("SE / SB / SL / FZ / FL / FA / X");
			
			while (!eingabe.equals("SE") && !eingabe.equals("SB") && !eingabe.equals("SL") && !eingabe.equals("FZ") && !eingabe.equals("FL") && !eingabe.equals("FA") && !eingabe.equals("X")) {
				eingabe = Eingabe.eingeben("Falsche Eingabe. \nSE / SB / SL / FZ / FL / FA / X");
			}
			if (eingabe.equals("SE")) {
				sachbearbeiterErfassen();
			} else if (eingabe.equals("SB")) {
				sachbearbeiterBearbeiten();
			} else if (eingabe.equals("SL")) {
				sachbearbeiterLoeschen();
			} else if (eingabe.equals("FZ")) {
				fortbildungZuordnen();
			} else if (eingabe.equals("FL")) {
				fortbildungsZuordnungLoeschen();
			} else if (eingabe.equals("FA")) {
				fortbildungsZuordnungAnzeigen();
			} else {
				break;
			}
		}
	}
	
	private void sachbearbeiterErfassen() {
		SachbearbeiterErfassenAAS.getInstance().oeffnen();
	}
	
	private void sachbearbeiterLoeschen() {
		SachbearbeiterLoeschenAAS.getInstance().oeffnen();
	}
	
	private void sachbearbeiterBearbeiten() {
		AdminSachbearbeiterBearbeitenAAS.getInstance().oeffnen();
	}
	
	private void fortbildungZuordnen() {}
	
	private void fortbildungsZuordnungLoeschen() {}
	
	private void fortbildungsZuordnungAnzeigen() {}
}
