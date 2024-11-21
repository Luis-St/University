package net.luis.as;

import net.luis.Eingabe;
import net.luis.aas.SachbearbeiterBearbeitenAAS;

public class NormalAS {
	
	public void oeffnen() {
		while (true) {
			System.out.println("--------------------------------");
			System.out.println("Menue");
			System.out.println("--------------------------------");
			System.out.println("Folgende Funktionen stehen zur Verfügung:");
			System.out.println("Sachbearbeiter bearbeiten (B)");
			System.out.println("Fortbildung zuordnen (Z)");
			System.out.println("Fortbildungszuordnung löschen (L)");
			System.out.println("Fortbildungszuordnung anzeigen (A)");
			System.out.println("Logout (X)");
			
			String eingabe = Eingabe.eingeben("B / Z / L / A / X");
			
			while (!eingabe.equals("B") && !eingabe.equals("Z") && !eingabe.equals("L") && !eingabe.equals("A") && !eingabe.equals("X")) {
				eingabe = Eingabe.eingeben("Falsche Eingabe. \nB / Z / L / A / X");
			}
			if (eingabe.equals("B")) {
				sachbearbeiterBearbeiten();
			} else if (eingabe.equals("Z")) {
				fortbildungZuordnen();
			} else if (eingabe.equals("L")) {
				fortbildungsZuordnungLoeschen();
			} else if (eingabe.equals("A")) {
				fortbildungsZuordnungAnzeigen();
			} else {
				break;
			}
		}
	}
	
	private void sachbearbeiterBearbeiten() {
		SachbearbeiterBearbeitenAAS.getInstance().modifiziereSachbearbeiter();
	}
	
	private void fortbildungZuordnen() {}
	
	private void fortbildungsZuordnungLoeschen() {}
	
	private void fortbildungsZuordnungAnzeigen() {}
}
