package wise2526.ka;

import static prog.Picture.*;
import static prog.Picture.show;

// Lösung der Klausuraufgabe 28 - Bildverarbeitung
// Aufgabe: Schreibe eine Klassenmethode f, die in einem Bild die linke Hälfte mit der rechten Hälfte vertauscht.
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
// Hinweis zur Aufgabe:
//  - Es darf davon ausgegangen werden, dass die Breite des Bildes eine gerade Zahl ist
//  - Die Methode soll keinen Rückgabewert haben (void)
//
// Beispiel:
//  Wenn das Bild so aussieht: [Linke Hälfte L] [Rechte Hälfte R]
//  Dann soll das Ergebnis sein: [Rechte Hälfte R] [Linke Hälfte L]
public class Aufgabe28 {
	
	public static void main(String[] args) {
		
		// Laden eines Bildes von der Festplatte
		// Alternativ loadResource für Bilder die im selben Ordner wie die .java Datei liegen
		int[][] image = loadFromFileSystem("./MyPicture.jpg");
		
		// Aufruf der Methode f mit dem geladenen Bild
		// Die Methode vertauscht die linke und rechte Hälfte des Bildes direkt
		f(image);
	}
	
	
	// Klassenmethode zum Vertauschen der linken und rechten Bildhälfte
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
		
		// Äußere Schleife: Durchläuft nur die linke Hälfte des Bildes (x-Koordinaten)
		// WICHTIG: Wir gehen nur bis breite / 2, nicht bis breite!
		// Warum nur die linke Hälfte?
		//  - Wir vertauschen jeden Pixel aus der linken Hälfte mit dem entsprechenden Pixel aus der rechten Hälfte
		//  - Wenn wir durch das ganze Bild laufen würden, würden wir die Pixel zweimal vertauschen
		//  - Das würde bedeuten, dass am Ende alles wieder am ursprünglichen Platz ist!
		//
		// Beispiel mit breite = 800:
		//  - breite / 2 = 400
		//  - Wir laufen von x = 0 bis x = 399 (die linke Hälfte)
		//  - Für jeden dieser Pixel wird der entsprechende Pixel in der rechten Hälfte gefunden und vertauscht
		//
		// Aufbau der Schleife:
		// for (Initialisierung; Bedingung; Inkrementierung) { Anweisungen }
		//  - Initialisierung: int x = 0; -> Startwert der x-Koordinate ist 0 (linker Rand)
		//  - Bedingung: x < breite / 2; -> Schleife läuft nur durch die linke Hälfte
		//  - Inkrementierung: x++ -> Erhöht x nach jedem Durchlauf um 1 (nächste Spalte)
		for (int x = 0; x < breite / 2; x++) {
			
			// Innere Schleife: Durchläuft alle y-Koordinaten (Zeilen) für die aktuelle x-Koordinate
			// Diese Schleife geht durch die gesamte Höhe des Bildes
			// Für jede Zeile wird ein Pixel aus der linken mit einem Pixel aus der rechten Hälfte vertauscht
			// Aufbau der Schleife:
			// for (Initialisierung; Bedingung; Inkrementierung) { Anweisungen }
			//  - Initialisierung: int y = 0; -> Startwert der y-Koordinate ist 0 (oberer Rand)
			//  - Bedingung: y < hoehe; -> Schleife läuft durch alle Zeilen
			//  - Inkrementierung: y++ -> Erhöht y nach jedem Durchlauf um 1 (nächste Zeile)
			for (int y = 0; y < hoehe; y++) {
				
				// Berechnung der x-Koordinate des Zielpixels in der rechten Bildhälfte
				// Für jeden Pixel in der linken Hälfte gibt es einen entsprechenden Pixel in der rechten Hälfte
				//
				// Beispiel mit breite = 800:
				//  - Wenn x = 0 (ganz links), dann soll mit x = 400 (Mitte + 0) vertauscht werden
				//  - Wenn x = 1, dann soll mit x = 401 (Mitte + 1) vertauscht werden
				//  - Wenn x = 399 (letzter Pixel der linken Hälfte), dann soll mit x = 799 (ganz rechts) vertauscht werden
				//
				// Formel: zielPixelX = x + (breite / 2)
				//  - x ist die aktuelle Position in der linken Hälfte (0 bis breite/2 - 1)
				//  - breite / 2 ist der Offset zur rechten Hälfte (die Mitte des Bildes)
				//  - zielPixelX ist die entsprechende Position in der rechten Hälfte
				int zielPixelX = x + (breite / 2);
				//int zielPixelX = breite - 1 - x;
				//int zielPixelX = x - breite - 1;
				
				// Speicherung des Farbwerts des aktuellen Pixels aus der linken Hälfte
				// p[x][y] greift auf den Pixel an Position (x, y) zu
				// Wir müssen diesen Wert zwischenspeichern, da wir ihn sonst verlieren würden
				//
				// Warum ist das notwendig?
				//  - Wir wollen p[x][y] und p[zielPixelX][y] vertauschen
				//  - Wenn wir direkt p[x][y] = p[zielPixelX][y] schreiben, verlieren wir den ursprünglichen Wert von p[x][y]
				//  - Deshalb speichern wir den Wert vorher in einer temporären Variable
				//
				// Dies ist ein klassisches "Swap"-Muster (Vertauschen von zwei Werten)
				int aktuellesPixel = p[x][y];
				
				// Erster Schritt des Vertauschens:
				// Kopiere den Pixel aus der rechten Hälfte in die linke Hälfte
				// p[x][y] erhält den Wert von p[zielPixelX][y]
				//
				// Nach dieser Zeile:
				//  - p[x][y] hat den Wert der rechten Hälfte (korrekt!)
				//  - p[zielPixelX][y] hat noch seinen ursprünglichen Wert (noch nicht vertauscht)
				//  - aktuellesPixel enthält den ursprünglichen Wert von p[x][y] (gesichert)
				p[x][y] = p[zielPixelX][y];
				
				// Zweiter Schritt des Vertauschens:
				// Kopiere den gespeicherten Pixel aus der linken Hälfte in die rechte Hälfte
				// p[zielPixelX][y] erhält den Wert aus aktuellesPixel (der ursprüngliche Wert von p[x][y])
				//
				// Nach dieser Zeile:
				//  - p[x][y] hat den Wert der rechten Hälfte (korrekt!)
				//  - p[zielPixelX][y] hat den ursprünglichen Wert der linken Hälfte (korrekt!)
				//  - Der Vertausch ist vollständig abgeschlossen!
				//
				// WICHTIG: Das klassische Swap-Muster mit drei Schritten:
				//  1. temp = a;        (Sichere den Wert von a)
				//  2. a = b;           (Überschreibe a mit b)
				//  3. b = temp;        (Setze b auf den gesicherten Wert von a)
				p[zielPixelX][y] = aktuellesPixel;
				
			}
			
		}
		
		show(p);
	}
	
}
