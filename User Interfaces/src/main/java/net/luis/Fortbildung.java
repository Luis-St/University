package net.luis;

import java.util.*;

public class Fortbildung {
	
	private static final Map<String, Fortbildung> dieFortbildungen = new HashMap<>();
	private final Set<Fortbildung> voraussetzungen = new HashSet<>();
	private final String name = "";
	
	static String[] gibAlleNamen() {
		return dieFortbildungen.keySet().toArray(new String[0]);
	}
	
	static Fortbildung gib(String name) {
		if (!dieFortbildungen.containsKey(name)) {
			throw new IllegalArgumentException("Sacharbeiter wurde nicht gefunden");
		}
		return dieFortbildungen.get(name);
	}
	
	void fuegeVoraussetzungHinzu(Fortbildung voraussetzung) {
		if (this.voraussetzungen.contains(voraussetzung)) {
			throw new IllegalArgumentException("Fortbildung ist bereits voraussetzung");
		}
		this.voraussetzungen.add(voraussetzung);
	}
	
	void loescheVoraussetzung(Fortbildung voraussetzung) {
		this.voraussetzungen.remove(voraussetzung);
	}
	
	boolean istVoraussetzung(Fortbildung voraussetzung) {
		return this.voraussetzungen.contains(voraussetzung);
	}
	
	Fortbildung[] gibVoraussetzungen() {
		return this.voraussetzungen.toArray(new Fortbildung[0]);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Fortbildung that)) return false;
		return Objects.equals(this.name, that.name);
	}
}
