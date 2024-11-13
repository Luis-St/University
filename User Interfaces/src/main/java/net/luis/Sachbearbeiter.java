package net.luis;

import java.util.*;

public class Sachbearbeiter {
	
	private static final Map<String, Sachbearbeiter> dieSachbearbeiter = new HashMap<>();
	private final Map<String, Fortbildung> bestandeneFortbildungen = new HashMap<>();
	private final Map<String, Fortbildung> belegteFortbildungen = new HashMap<>();
	
	private String benutzername;
	private String passwort;
	private boolean istAdmin;
	
	Sachbearbeiter(String benutzername, String passwort, boolean isAdmin) {
		this.setzeBenutzername(benutzername);
		this.setzePasswort(passwort);
		this.setzeBerechtigung(isAdmin);
	}
	
	static String[] gibAlleNamen() {
		return dieSachbearbeiter.keySet().toArray(new String[0]);
	}
	
	static Sachbearbeiter gib(String benutzername) {
		if (!dieSachbearbeiter.containsKey(benutzername)) {
			throw new IllegalArgumentException("Sacharbeiter nicht gefunden");
		}
		return dieSachbearbeiter.get(benutzername);
	}
	
	static void fuegeHinzu(Sachbearbeiter sachbearbeiter) {
		if (dieSachbearbeiter.containsKey(sachbearbeiter.gibBenutzername())) {
			throw new IllegalArgumentException("Sachbearbeiter mit benutzername existiert bereits");
		}
		dieSachbearbeiter.put(sachbearbeiter.gibBenutzername(), sachbearbeiter);
		
	}
	
	static boolean loesche(String benutzername) {
		if (pruefeLoeschen(benutzername)) {
			dieSachbearbeiter.remove(benutzername);
			return true;
		}
		return false;
	}
	
	void setzeBenutzername(String benutzername) {
		if (pruefeBenutzername(benutzername)) {
			this.benutzername = benutzername;
		} else {
			throw new IllegalArgumentException("Falscher Benutzername");
		}
	}
	
	String gibBenutzername() {
		return this.benutzername;
	}
	
	void setzePasswort(String passwort) {
		if (pruefePasswort(passwort)) {
			this.passwort = passwort;
		} else {
			throw new IllegalArgumentException("Passwort entspricht nicht den Anforderungen");
		}
	}
	
	String gibPasswort() {
		return this.passwort;
	}
	
	void setzeBerechtigung(boolean istAdmin) {
		this.istAdmin = istAdmin;
	}
	
	boolean istAdmin() {
		return this.istAdmin;
	}
	
	static boolean pruefeLoeschen(String benutzername) {
		if (dieSachbearbeiter.containsKey(benutzername)) {
			if (dieSachbearbeiter.get(benutzername).istAdmin()) {
				int adminCount = 0;
				for (Map.Entry<String, Sachbearbeiter> entry : dieSachbearbeiter.entrySet()) {
					String key = entry.getKey();
					Sachbearbeiter value = entry.getValue();
					
					if (value.istAdmin) {
						adminCount++;
						if (adminCount > 1) {
							break;
						}
					}
				}
				return adminCount >= 2;
			}
			return true;
		}
		return false;
	}
	
	static boolean pruefeBenutzername(String benutzername) {
		return benutzername.matches("[a-zA-Z_äöüÄÖÜß]+");
	}
	
	static boolean pruefePasswort(String passwort) {
		if (passwort.length() > 9) {
			long lowerCaseCount = passwort.chars().filter(ch -> Character.isLowerCase(ch) || ch == 'ä' || ch == 'ö' || ch == 'ü' || ch == 'ß').count();
			long upperCaseCount = passwort.chars().filter(ch -> Character.isUpperCase(ch) || ch == 'Ä' || ch == 'Ö' || ch == 'Ü').count();
			long digitCount = passwort.chars().filter(Character::isDigit).count();
			long specialCount = passwort.chars().filter(ch -> !Character.isLetterOrDigit(ch)).count();
			
			return lowerCaseCount > 1 && upperCaseCount > 1 && digitCount > 1 && specialCount > 1;
		}
		return false;
	}
	
	static boolean mindestensEinSachbearbeiterExistiert() {
		return !dieSachbearbeiter.isEmpty();
	}
	
	static boolean mindestensEinAdminExistiert() {
		if (mindestensEinSachbearbeiterExistiert()) {
			for (Map.Entry<String, Sachbearbeiter> entry : dieSachbearbeiter.entrySet()) {
				String key = entry.getKey();
				Sachbearbeiter value = entry.getValue();
				
				if (value.istAdmin) {
					return true;
				}
			}
		}
		return false;
	}
	
	static boolean mindestensEineFortbildungszuordnungExistiert() {
		if (mindestensEinSachbearbeiterExistiert()) {
			for (Map.Entry<String, Sachbearbeiter> entry : dieSachbearbeiter.entrySet()) {
				String key = entry.getKey();
				Sachbearbeiter value = entry.getValue();
				
				if (!value.belegteFortbildungen.isEmpty() && !value.bestandeneFortbildungen.isEmpty()) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Sachbearbeiter that)) return false;
		return Objects.equals(this.benutzername, that.benutzername);
	}
}
