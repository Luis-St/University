package prog;

import fachwerk.Fachwerk;

/**
 * Musterlösung – Aufgabe "Längster Stab".
 *
 * Für ein vom Anwender gewähltes Fachwerk (0–4) wird der längste Stab
 * ermittelt und dessen Nummer sowie Länge ausgegeben. Die Stablänge folgt
 * aus den Koordinaten von Anfangs- und Endknoten (Satz des Pythagoras):
 *
 *     l = sqrt((x_e - x_a)^2 + (y_e - y_a)^2)
 *
 * Es geht ausschließlich um die Geometrie des Fachwerks – ein Aufruf von
 * berechneGroessen() ist daher NICHT erforderlich.
 *
 * Benötigt fachwerk.jar sowie die Klasse Tastatureingabe im Klassenpfad,
 * z. B.:  javac -cp fachwerk.jar src/prog/LongestBar.java
 *
 * Diese Datei ist die korrigierte Fassung der fehlerhaften Vorlage aus der
 * Aufgabenstellung (acht eingebaute Fehler, siehe Tutorexemplar).
 */
public class LongestBar {
	
	public static void main(String[] args) {
		Fachwerk fw = null;
		System.out.println("Welches Fachwerk wollen Sie rechnen (0-4)?");
		int wahl = ConsoleReader.readInt();
		fw = new Fachwerk(wahl);
		
		double max = 0;
		int nr = 0;
		
		for (int i = 0; i < fw.gibAnzahlStaebe(); i++) {
			int a = fw.gibStab(i).gibKnotenNrA();
			int e = fw.gibStab(i).gibKnotenNrE();
			double dx = fw.gibKnoten(e).gibX() - fw.gibKnoten(a).gibX();
			double dy = fw.gibKnoten(e).gibY() - fw.gibKnoten(a).gibY();
			double laenge = Math.sqrt(dx * dx + dy * dy);
			if (laenge > max) {
				max = laenge;
				nr = i;
			}
		}
		System.out.println("Laengster Stab: Stab " + nr + " mit Laenge " + max);
	}
}
