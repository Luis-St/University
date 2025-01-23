package net.luis.k;

import net.luis.Fortbildung;
import net.luis.Sachbearbeiter;

import java.util.Collection;

public class FortbildungsZuordnungAnzeigenK {
	
	public String[] gibBelegteFortbildung(String sachbearbeiter) {
		return Sachbearbeiter.gib(sachbearbeiter).gibBelegteFortbildungen().stream().map(Fortbildung::gibName).toArray(String[]::new);
	}
	
	public String[] gibBestandeneFortbildung(String sachbearbeiter) {
		return Sachbearbeiter.gib(sachbearbeiter).gibBestandeneFortbildungen().stream().map(Fortbildung::gibName).toArray(String[]::new);
	}
}
