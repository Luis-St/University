package net.luis.k;

import net.luis.Fortbildung;
import net.luis.Sachbearbeiter;

import java.util.*;

public class FortbildungszuordnungAuswaehlenK {
	
	public String[] gibBestandeneFortbildungenNamen(String benutzername) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(benutzername);
		return wandleInStrings(sachbearbeiter.gibBestandeneFortbildungen()).toArray(new String[0]);
	}
	
	public String[] gibBelegteFortbildungenNamen(String benutzername) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(benutzername);
		return wandleInStrings(sachbearbeiter.gibBelegteFortbildungen()).toArray(new String[0]);
	}
	
	public Set<String> wandleInStrings(Collection<Fortbildung> fortbildungen) {
		Set<String> strings = new LinkedHashSet<>();
		
		for (Fortbildung fortbildung : fortbildungen) {
			strings.add(fortbildung.gibName());
		}
		
		return strings;
	}
}
