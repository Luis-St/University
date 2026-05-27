package sose25;

/**
 *
 * @author Luis-St
 *
 */

public class Auto {
	
	// Eigenschaften (was hat jedes Auto?)
	public String farbe;
	public String marke;
	public int geschwindigkeit;
	
	// Konstruktoren (beschreibt, wie ein Auto erstellt wird)
	public Auto() {
		// Leerer Standard-Konstruktor
	}
	
	public Auto(String farbe, String marke, int geschwindigkeit) {
		this.farbe = farbe;
		this.marke = marke;
		this.geschwindigkeit = geschwindigkeit;
	}
	
	// Methoden (was kann jedes Auto tun?)
	public void fahren() {
		System.out.println("Das Auto fährt!");
	}
	
	public void hupen() {
		System.out.println("Huuup!");
	}
	
	public static void main(String[] args) {
		// Zwei verschiedene Auto-Objekte erstellen
		Auto meinAuto = new Auto();
		meinAuto.farbe = "rot";
		meinAuto.marke = "BMW";
		
		Auto deinAuto = new Auto();
		deinAuto.farbe = "blau";
		deinAuto.marke = "Mercedes";
		
		// Die Objekte können Aktionen ausführen
		meinAuto.fahren();
		deinAuto.hupen();
	}
}


