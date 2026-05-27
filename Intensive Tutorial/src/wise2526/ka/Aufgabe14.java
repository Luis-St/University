package wise2526.ka;

import prog.Picture;

import static prog.Picture.*;

// Lösung der Klausuraufgabe 14 - Bildverarbeitung
// Aufgabe: Schreibe eine Klassenmethode f, die die Pixel am Rand eines Bildes schwarz färbt.
//          Ein Bitmap wird in einem zweidimensionalen int-Array p gespeichert.
//          Jede Farbe wird durch eine Zahl vom Typ int dargestellt.
//          Die Variable p[x][y] steht für die Farbe des Bildes an der Koordinate (x, y).
//
// Das Koordinatensystem des Bildes:
//  - Die Breite des Bildes ist p.length
//  - Die Höhe des Bildes ist p[0].length
//  - x liegt im Bereich von 0 bis p.length-1
//  - y liegt im Bereich von 0 bis p[0].length-1
//  - Der Ursprung (0, 0) befindet sich links oben
//  - p[x][y] enthält die Farbe des Pixels an der Position (x, y)
//
// Der Wert 0 steht für die Farbe schwarz.
public class Aufgabe14 {
	
	public static void main(String[] args) {
		
		// Laden eines Bildes von der Festplatte
		// Alternativ loadResource für Bilder die im selben Ordner wie die .java Datei liegen
		int[][] image = loadFromFileSystem("./MyPicture.jpg");
		
		// Aufruf der Methode f mit dem geladenen Bild
		// Die Methode verändert das Bild direkt (die Pixel am Rand werden schwarz gefärbt)
		f(image);
	}
	
	
	// Klassenmethode zur Einfärbung der Randpixel eines Bildes
	// Klassenmethode:
	//  - public: Die Methode ist von außerhalb der Klasse aufrufbar
	//  - static: Die Methode kann direkt aufgerufen werden ohne ein Objekt zu erstellen
	//  - void: Die Methode gibt keinen Wert zurück (return ist nicht erforderlich)
	//         Sie verändert stattdessen das übergebene Array direkt
	//  - f: Name der Methode (kann frei gewählt werden)
	//  - (int[][] p): Die Methode erwartet einen Parameter:
	//                 - p: ein zweidimensionales Array von int-Werten (das Bild)
	//                      Die erste Dimension repräsentiert die x-Koordinate (Breite)
	//                      Die zweite Dimension repräsentiert die y-Koordinate (Höhe)
	public static void f(int[][] p) {
		
		// Ermittlung der Breite des Bildes
		// p.length gibt die Anzahl der Elemente in der ersten Dimension zurück
		// Dies entspricht der Anzahl der Spalten (x-Koordinaten) des Bildes
		// Beispiel: Wenn das Bild 800 Pixel breit ist, dann ist breite = 800
		int breite = p.length;
		
		// Ermittlung der Höhe des Bildes
		// p[0].length gibt die Anzahl der Elemente in der zweiten Dimension zurück
		// Dies entspricht der Anzahl der Zeilen (y-Koordinaten) des Bildes
		// Wir verwenden p[0], da alle Spalten die gleiche Höhe haben
		// Beispiel: Wenn das Bild 600 Pixel hoch ist, dann ist hoehe = 600
		int hoehe = p[0].length;
		
		// Äußere Schleife: Durchläuft alle x-Koordinaten (Spalten) des Bildes
		// Die Schleife läuft von 0 bis breite-1 (also durch alle Spalten)
		// In jedem Durchlauf wird eine Spalte von oben nach unten durchgegangen
		// Aufbau der Schleife:
		// for (Initialisierung; Bedingung; Inkrementierung) { Anweisungen }
		//  - Initialisierung: int x = 0; -> Startwert der x-Koordinate ist 0 (linker Rand)
		//  - Bedingung: x < breite; -> Schleife läuft, solange x kleiner als die Breite ist
		//  - Inkrementierung: x++ -> Erhöht x nach jedem Durchlauf um 1 (nächste Spalte)
		for (int x = 0; x < breite; x++) {
			
			// Innere Schleife: Durchläuft alle y-Koordinaten (Zeilen) für die aktuelle x-Koordinate
			// Die Schleife läuft von 0 bis hoehe-1 (also durch alle Zeilen)
			// In jedem Durchlauf wird ein einzelner Pixel betrachtet
			// Aufbau der Schleife:
			// for (Initialisierung; Bedingung; Inkrementierung) { Anweisungen }
			//  - Initialisierung: int y = 0; -> Startwert der y-Koordinate ist 0 (oberer Rand)
			//  - Bedingung: y < hoehe; -> Schleife läuft, solange y kleiner als die Höhe ist
			//  - Inkrementierung: y++ -> Erhöht y nach jedem Durchlauf um 1 (nächste Zeile)
			for (int y = 0; y < hoehe; y++) {
				
				// Überprüfung, ob der aktuelle Pixel am Rand des Bildes liegt
				// Ein Pixel liegt am Rand, wenn eine der folgenden Bedingungen erfüllt ist:
				//  1. y == 0: Der Pixel liegt am oberen Rand (erste Zeile)
				//  2. y == hoehe - 1: Der Pixel liegt am unteren Rand (letzte Zeile)
				//  3. x == 0: Der Pixel liegt am linken Rand (erste Spalte)
				//  4. x == breite - 1: Der Pixel liegt am rechten Rand (letzte Spalte)
				//
				// Der || Operator bedeutet "ODER":
				//  - Die gesamte Bedingung ist wahr, wenn mindestens eine der Einzelbedingungen wahr ist
				//  - Beispiel: Wenn x = 0 (linker Rand), dann ist die Bedingung wahr, egal was y ist
				//
				// Warum "hoehe - 1" und "breite - 1"?
				//  - Arrays in Java beginnen bei Index 0
				//  - Ein Array mit 10 Elementen hat die Indizes 0, 1, 2, ..., 9
				//  - Der letzte Index ist also immer (Länge - 1)
				if (y == 0 || y == hoehe - 1 || x == 0 || x == breite - 1) {
					
					// Setzen der Farbe des Randpixels auf schwarz
					// p[x][y] greift auf den Pixel an Position (x, y) zu
					// 0 ist der Farbwert für schwarz (alle Farbkanäle auf 0)
					// Die Zuweisung überschreibt die ursprüngliche Farbe des Pixels
					//
					// Farbdarstellung in Java:
					//  - Farben werden als int-Werte im Format 0xRRGGBB gespeichert
					//  - RR = Rotwert (00-FF in Hexadezimal, 0-255 in Dezimal)
					//  - GG = Grünwert (00-FF in Hexadezimal, 0-255 in Dezimal)
					//  - BB = Blauwert (00-FF in Hexadezimal, 0-255 in Dezimal)
					//  - 0 (oder 0x000000) bedeutet: Rot=0, Grün=0, Blau=0 → schwarz
					//  - Beispiele: 0xFF0000 = rot, 0x00FF00 = grün, 0x0000FF = blau
					p[x][y] = 0;
					
				}
				
			}
			
		}
		
		show(p);
	}
	
}
