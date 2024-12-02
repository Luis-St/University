package net.luis.k;

import net.luis.Fortbildung;
import net.luis.Sachbearbeiter;

import java.util.Collection;

public class FortbildungszuordnungLoeschenK {
	
	private String pruefeLoeschenVonFortbildungszuordnung(Sachbearbeiter sachbearbeiter, Fortbildung fortbildung) {
		Collection<Fortbildung> bestandeneFortbildungen = sachbearbeiter.gibBestandeneFortbildungen();
		
		for (Fortbildung bestandeneFortbildung : bestandeneFortbildungen) {
			if (fortbildung.istVoraussetzungVon(bestandeneFortbildung)) {
				return "Die Fortbildung " + fortbildung.gibName() + " ist leider Voraussetzung "
					+ " der bestandenen Fortbildung " + bestandeneFortbildung.gibName() + ". \nSie kann "
					+ "daher f�r den Sachbearbeiter " + sachbearbeiter.gibBenutzername() + " nicht gel�scht werden";
			}
		}
		
		return null;
	}
	
	public String loescheFortbildungszuordnung(String sachbearbeiterName, String fortbildungsName) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(sachbearbeiterName);
		Fortbildung fortbildung = Fortbildung.gib(fortbildungsName);
		
		if (sachbearbeiter.istBelegt(fortbildung)) {
			sachbearbeiter.fortbildungsbelegungLoeschen(fortbildung);
			return null;
		} else {
			String fehlermeldung = pruefeLoeschenVonFortbildungszuordnung(sachbearbeiter, fortbildung);
			if (fehlermeldung == null) {
				sachbearbeiter.fortbildungsbestehenLoeschen(fortbildung);
				return null;
			} else {
				return fehlermeldung;
			}
		}
	}
	
	public String[] gibBelegteFortbildungen(String ausgewaehlterBenutzername) {
		return new FortbildungszuordnungenAnzeigenK().gibBelegteFortbildungenNamen(ausgewaehlterBenutzername);
	}
	
	public String[] gibBestandeneFortbildungen(String ausgewaehlterBenutzername) {
		return new FortbildungszuordnungenAnzeigenK().gibBestandeneFortbildungenNamen(ausgewaehlterBenutzername);
	}
}
