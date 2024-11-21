package net.luis;
import java.util.LinkedHashSet;
import java.util.Set;

class FortbildungAnzeigenK extends FortbildungZuordnenK {
	String[] gibBelegteFortbildungen(String ausgewaehlterBenutzername) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(ausgewaehlterBenutzername);
		Set<String> fortbildungenNamen = new LinkedHashSet<>();
		
		for (Fortbildung belegteFortbildung : sachbearbeiter.gibBelegteFortbildungen()) {
			fortbildungenNamen.add(belegteFortbildung.gibName());
		}
		
		return fortbildungenNamen.toArray(new String[0]);
	}

	String[] gibBestandeneFortbildungen(String ausgewaehlterBenutzername) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(ausgewaehlterBenutzername);
		Set<String> fortbildungenNamen = new LinkedHashSet<>();
		
		for (Fortbildung bestandeneFortbildung : sachbearbeiter.gibBestandeneFortbildungen()) {
			fortbildungenNamen.add(bestandeneFortbildung.gibName());
		}
		
		return fortbildungenNamen.toArray(new String[0]);
	}
}
