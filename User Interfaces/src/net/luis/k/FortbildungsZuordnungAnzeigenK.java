package net.luis.k;

import net.luis.Fortbildung;
import net.luis.Sachbearbeiter;

import java.util.Collection;

public class FortbildungsZuordnungAnzeigenK {
	
	public Collection<String> gibBelegteFortbildung(String sachbearbeiter) {
		return Sachbearbeiter.gib(sachbearbeiter).gibBelegteFortbildungen().stream().map(Fortbildung::gibName).toList();
	}
	
	public Collection<String> gibBestandeneFortbildung(String sachbearbeiter) {
		return Sachbearbeiter.gib(sachbearbeiter).gibBestandeneFortbildungen().stream().map(Fortbildung::gibName).toList();
	}
}
