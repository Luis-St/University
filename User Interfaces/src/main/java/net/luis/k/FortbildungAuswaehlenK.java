package net.luis.k;
import net.luis.Fortbildung;
import net.luis.Sachbearbeiter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class FortbildungAuswaehlenK {
	
	public static String[] gibAlleNamen() {
		return Fortbildung.gibAlleNamen();
	}

	public String[] gibZuBelegendeFortbildungenNamen(String benutzername) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(benutzername);
		Collection<Fortbildung> fortbildungen = Fortbildung.gibDieFortbildungen().values();
		Set<String> nochZuBelegendeFortbildungen = new LinkedHashSet<>();
		
		for (Fortbildung fortbildung : fortbildungen) {
			if (pruefeBelegen(sachbearbeiter, fortbildung)) {
				nochZuBelegendeFortbildungen.add(fortbildung.gibName());
			}
		}
				
		return nochZuBelegendeFortbildungen.toArray(new String[0]);
	}

	public String[] gibZuBestehendeFortbildungenNamen(String benutzername) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(benutzername);
		Collection<Fortbildung> fortbildungen = Fortbildung.gibDieFortbildungen().values();
		Set<String> nochZuBestehendeFortbildungen = new LinkedHashSet<>();
		
		for (Fortbildung fortbildung : fortbildungen) {
			if (pruefeBestehen(sachbearbeiter, fortbildung)) {
				nochZuBestehendeFortbildungen.add(fortbildung.gibName());
			}
		}
				
		return nochZuBestehendeFortbildungen.toArray(new String[0]);
	}
	
	private boolean pruefeBelegen(Sachbearbeiter sachbearbeiter, Fortbildung fortbildung) {
		if (sachbearbeiter.istBelegt(fortbildung)) {
			return false;
		} else if (sachbearbeiter.istBestanden(fortbildung)) {
			return false;
		} else if (!alleVoraussetzungenVorhanden(sachbearbeiter, fortbildung)) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean alleVoraussetzungenVorhanden(Sachbearbeiter sachbearbeiter, Fortbildung fortbildung) {
		Set<Fortbildung> voraussetzungen = fortbildung.gibVoraussetzungen();
		
		for (Fortbildung voraussetzung : voraussetzungen) {
			if (!sachbearbeiter.istBestanden(voraussetzung)) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean pruefeBestehen(Sachbearbeiter sachbearbeiter, Fortbildung fortbildung) {
		if (!sachbearbeiter.istBelegt(fortbildung)) {
			return false;
		} else if (sachbearbeiter.istBestanden(fortbildung)) {
			return false;
		} else {
			return true;
		}
	}
}
