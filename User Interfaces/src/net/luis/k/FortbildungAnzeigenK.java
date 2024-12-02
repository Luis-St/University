package net.luis.k;

import net.luis.Fortbildung;
import net.luis.Sachbearbeiter;

import java.util.LinkedHashSet;
import java.util.Set;

public class FortbildungAnzeigenK extends FortbildungZuordnenK {
	
	public String[] gibBelegteFortbildungen(String ausgewaehlterBenutzername) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(ausgewaehlterBenutzername);
		Set<String> fortbildungenNamen = new LinkedHashSet<>();
		
		for (Fortbildung belegteFortbildung : sachbearbeiter.gibBelegteFortbildungen()) {
			fortbildungenNamen.add(belegteFortbildung.gibName());
		}
		
		return fortbildungenNamen.toArray(new String[0]);
	}
	
	public String[] gibBestandeneFortbildungen(String ausgewaehlterBenutzername) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(ausgewaehlterBenutzername);
		Set<String> fortbildungenNamen = new LinkedHashSet<>();
		
		for (Fortbildung bestandeneFortbildung : sachbearbeiter.gibBestandeneFortbildungen()) {
			fortbildungenNamen.add(bestandeneFortbildung.gibName());
		}
		
		return fortbildungenNamen.toArray(new String[0]);
	}
}
