package net.luis;

import net.luis.Fortbildung;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Sachbearbeiter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	// Invariante: mindestens ein Administrator muss in dieSachbearbeiter vorhanden
	// sein
	private static Map<String, Sachbearbeiter> dieSachbearbeiter = new HashMap<>();
	
	public static String[] gibAlleNamen() {
		return dieSachbearbeiter.keySet().toArray(new String[0]);
	}
	
	public static Sachbearbeiter gib(String benutzername) {
		return dieSachbearbeiter.get(benutzername);
	}
	
	// fuegeHinzu ist private wegen der
	// Invariante: jeder Sachbearbeiter, der erzeugt wird, wird auch
	// der Map dieSachbearbeiter hinzugefuegt
	// fuegeHinzu soll daher nur von erzeuge aufgerufen werden
	
	private static void fuegeHinzu(Sachbearbeiter sachbearbeiter) {
		dieSachbearbeiter.put(sachbearbeiter.gibBenutzername(), sachbearbeiter);
	}
	
	public static boolean mindestensEinSachbearbeiterExistiert() {
		return !dieSachbearbeiter.isEmpty();
	}
	
	public static boolean mindestensEinAdminExistiert() {
		
		for (Sachbearbeiter s : dieSachbearbeiter.values()) {
			if (s.istAdmin() == true) {
				return true;
			}
		}
		
		return false;
	}
	
	public static String pruefeBenutzername(String benutzername) {
		boolean istOk = !dieSachbearbeiter.containsKey(benutzername);
		
		if (!istOk) {
			return "Den Benutzer " + benutzername + " gibt es bereits";
		}
		
		String regAusdruck = "[a-zäüößA-ZÄÜÖ_]+";
		istOk = benutzername.matches(regAusdruck);
		if (istOk) {
			return null;
		} else {
			return "Der Benutzername entspricht leider nicht den Vorgaben f r Benutzernamen";
		}
	}
	
	public static String pruefePasswort(String passwort) {
		String regAusdruck = ".*[A-ZÄÜÖ].*[A-ZÄÜÖ].*";
		boolean istOk = passwort.matches(regAusdruck);
		regAusdruck = ".*[a-zäüöß].*[a-zäüöß].*";
		istOk = istOk && passwort.matches(regAusdruck);
		regAusdruck = ".*\\d.*\\d.*";
		istOk &= passwort.matches(regAusdruck);
		// laut Wikipedia ist ein Sonderzeichen ein Zeichen, das weder Buchstabe noch
		// Ziffer ist
		String sonderzeichen = "[^a-zäüößA-ZÄÜÖ0-9]";
		regAusdruck = ".*" + sonderzeichen + ".*" + sonderzeichen + ".*";
		istOk &= passwort.matches(regAusdruck);
		istOk &= passwort.length() >= 10;
		if (istOk) {
			return null;
		} else {
			return "Das Passwort entspricht leider nicht den Vorgaben";
		}
	}
	
	// Invariante: jeder Sachbearbeiter, der erzeugt wird, wird auch
	// der Map dieSachbearbeiter hinzugefuegt
	// wenn das erzeugen klappt, wird null zurueckgegeben
	// wenn es nicht klappt, wird eine Fehlermeldung zurueckgegeben
	public static String erzeuge(String benutzername, String passwort, boolean istAdmin) {
		String fehlermeldung = pruefeBenutzername(benutzername);
		if (fehlermeldung == null) {
			fehlermeldung = pruefePasswort(passwort);
			if (fehlermeldung == null) {
				Sachbearbeiter s = new Sachbearbeiter(benutzername, passwort, istAdmin);
				fuegeHinzu(s);
				return null;
			}
		}
		return fehlermeldung;
	}
	
	private String benutzername;
	private String passwort;
	private boolean istAdmin = false;
	
	private Map<String, Fortbildung> bestandeneFortbildungen = new HashMap<>();
	private Map<String, Fortbildung> belegteFortbildungen = new HashMap<>();
	
	public boolean istBestanden(Fortbildung fortbildung) {
		return bestandeneFortbildungen.containsKey(fortbildung.gibName());
	}
	
	public boolean istBelegt(Fortbildung fortbildung) {
		return belegteFortbildungen.containsKey(fortbildung.gibName());
	}
	
	public String gibBenutzername() {
		return benutzername;
	}
	
	public String setzeBenutzername(String neuerBenutzername) {
		String fehlermeldung = null;
		if (!neuerBenutzername.equals(this.benutzername)) {
			fehlermeldung = pruefeBenutzername(neuerBenutzername);
		}
		
		if (fehlermeldung == null) {
			// der Schluessel der Map aendert sich
			dieSachbearbeiter.remove(this.benutzername);
			this.benutzername = neuerBenutzername;
			dieSachbearbeiter.put(neuerBenutzername, this);
		}
		return fehlermeldung;
	}
	
	public String gibPasswort() {
		return passwort;
	}
	
	public String setzePasswort(String passwort) {
		String fehlermeldung = pruefePasswort(passwort);
		
		if (fehlermeldung == null) {
			this.passwort = passwort;
		}
		return fehlermeldung;
	}
	
	public boolean istAdmin() {
		return istAdmin;
	}
	
	public String setzeIstAdmin(boolean istAdmin) {
		boolean alteBerechtigung = this.istAdmin;
		this.istAdmin = istAdmin;
		if (mindestensEinAdminExistiert()) {
			return null;
		} else {
			// rollback
			this.istAdmin = alteBerechtigung;
			return "Der Sachbearbeiter " + gibBenutzername() + " ist der letzte Administrator im System und kann "
				+ "daher keine geringere Berechtigung annehmen";
		}
	}
	
	private Sachbearbeiter(String benutzername, String passwort, boolean istAdmin) {
		this.benutzername = benutzername;
		this.passwort = passwort;
		this.istAdmin = istAdmin;
	}
	
	public boolean mindestensEineFortbildungszuordnungExistiert() {
		boolean existiert = !this.belegteFortbildungen.isEmpty();
		return existiert |= !this.bestandeneFortbildungen.isEmpty();
	}
	
	public String loesche() {
		dieSachbearbeiter.remove(gibBenutzername());
		// mindestens ein Admin muss in dieFortbildungen existieren
		if (mindestensEinAdminExistiert()) {
			return null;
		} else {
			dieSachbearbeiter.put(gibBenutzername(), this);
			return "Der Sachbearbeiter " + gibBenutzername() + " ist der letzte Administrator im System und kann "
				+ "daher nicht gel scht werden";
		}
	}
	
	public void belege(Fortbildung fortbildung) {
		belegteFortbildungen.put(fortbildung.gibName(), fortbildung);
	}
	
	public void bestehe(Fortbildung fortbildung) {
		belegteFortbildungen.remove(fortbildung.gibName());
		bestandeneFortbildungen.put(fortbildung.gibName(), fortbildung);
	}
	
	public void fortbildungsbelegungLoeschen(Fortbildung fortbildung) {
		belegteFortbildungen.remove(fortbildung.gibName());
	}
	
	public void fortbildungsbestehenLoeschen(Fortbildung fortbildung) {
		bestandeneFortbildungen.remove(fortbildung.gibName());
	}
	
	public static void alleNamenAusgeben() {
		String[] alleNamen = Sachbearbeiter.gibAlleNamen();
		for (String name : alleNamen) {
			System.out.println(name);
		}
		
	}
	
	public Collection<Fortbildung> gibBestandeneFortbildungen() {
		return bestandeneFortbildungen.values();
	}
	
	public Collection<Fortbildung> gibBelegteFortbildungen() {
		return belegteFortbildungen.values();
	}
	
	private static String dateiname = "./Entitaeten.ser";
	
	public static void abspeichern() {
		try (ObjectOutputStream aus = new ObjectOutputStream(new FileOutputStream(dateiname))) {
			aus.writeObject(dieSachbearbeiter);
			aus.writeObject(Fortbildung.gibDieFortbildungen());
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void einlesen() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dateiname))) {
			dieSachbearbeiter = (Map<String, Sachbearbeiter>) in.readObject();
			Map<String, Fortbildung> dieFortbildungen = (Map<String, Fortbildung>) in.readObject();
			Fortbildung.setzeDieFortbildungen(dieFortbildungen);
		} catch (FileNotFoundException ex) {
			System.out.println("Speicherdatei (noch) nicht vorhanden!");
			
			// zum initialen Login und fuer die Invariante
			erzeuge("admin", "aaAA11&&aa", true);
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
