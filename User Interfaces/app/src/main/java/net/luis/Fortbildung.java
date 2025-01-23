package net.luis;

import java.io.Serializable;
import java.util.*;

public class Fortbildung implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Map<String, Fortbildung> dieFortbildungen = new HashMap<>();
	
	public static Map<String, Fortbildung> gibDieFortbildungen() {
		return dieFortbildungen;
	}
	
	public static void setzeDieFortbildungen(Map<String, Fortbildung> fortbildungen) {
		dieFortbildungen = fortbildungen;
	}
	
	public static String[] gibAlleNamen() {
		return dieFortbildungen.keySet().toArray(new String[0]);
	}
	
	public static Fortbildung gib(String name) {
		return dieFortbildungen.get(name);
	}
	
	private final String name;
	private final Set<Fortbildung> voraussetzungen = new LinkedHashSet<>();
	
	public Fortbildung(String name, Fortbildung... vorausgesetzteFortbildungen) {
		this.name = name;
		Collections.addAll(this.voraussetzungen, vorausgesetzteFortbildungen);
		dieFortbildungen.put(this.gibName(), this);
	}
	
	public String gibName() {
		return this.name;
	}
	
	public boolean istVoraussetzungVon(Fortbildung fortbildung) {
		return fortbildung.voraussetzungen.contains(this);
	}
	
	public Set<Fortbildung> gibVoraussetzungen() {
		return this.voraussetzungen;
	}
}
