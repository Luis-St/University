package wise2526.ka;

import static prog.Picture.*;
import static prog.Picture.show;

// Lösung der Klausuraufgabe 16 - Bildverarbeitung
// Aufgabe: Schreibe eine Klassenmethode f, die einen Kreisring (Ring) in einem Bild schwarz färbt.
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
// Was ist ein Kreisring?
//  Ein Kreisring ist die Fläche zwischen zwei konzentrischen Kreisen (Kreise mit demselben Mittelpunkt):
//   - Der äußere Kreis hat einen größeren Radius (aussenRadius)
//   - Der innere Kreis hat einen kleineren Radius (innenRadius)
//   - Der Ring ist die Fläche dazwischen
//
// Mathematischer Hintergrund - Abstand eines Punktes vom Mittelpunkt:
//  Um zu prüfen, ob ein Punkt im Ring liegt, müssen wir seinen Abstand vom Mittelpunkt berechnen.
//  Die Formel für den Abstand zwischen zwei Punkten (x1, y1) und (x2, y2) ist:
//   Abstand = √((x2 - x1)² + (y2 - y1)²)  [Satz des Pythagoras]
//
//  Wir können die Wurzel weglassen, indem wir beide Seiten quadrieren:
//   Abstand² = (x2 - x1)² + (y2 - y1)²
//
//  Ein Punkt liegt im Ring, wenn:
//   innenRadius² ≤ Abstand² ≤ aussenRadius²
//
// Der Wert 0 steht für die Farbe schwarz.

public class Aufgabe16 {
	
	public static void main(String[] args) {
		
		int[][] image = loadResource("");
		
		// Aufruf der Methode f mit dem geladenen Bild
		// Die Methode zeichnet einen schwarzen Kreisring in das Bild
		f(image);
	}
	
	// Klassenmethode zum Zeichnen eines Kreisrings in ein Bild
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
		
		// Definition des Suchbereichs in x-Richtung (horizontal)
		// Anstatt durch das gesamte Bild zu gehen, können wir die Suche auf einen kleineren Bereich beschränken
		// Dies verbessert die Performance, da wir nur den Bereich prüfen, in dem der Ring tatsächlich liegt
		//
		// xStartpunkt: Die x-Koordinate, ab der wir mit der Suche beginnen
		// Beispiel: 180 bedeutet, wir beginnen bei Pixel 180 von links
		int xStartpunkt = 180;
		
		// xEndpunkt: Die x-Koordinate, bei der wir die Suche beenden
		// Beispiel: 320 bedeutet, wir hören bei Pixel 320 auf (nicht eingeschlossen)
		// Der Suchbereich in x-Richtung ist also 180 bis 319 (140 Pixel breit)
		int xEndpunkt = 320;
		
		// Definition des Suchbereichs in y-Richtung (vertikal)
		// Gleiche Idee wie bei x: Wir beschränken den Suchbereich, um Performance zu sparen
		//
		// yStartpunkt: Die y-Koordinate, ab der wir mit der Suche beginnen
		// Beispiel: 50 bedeutet, wir beginnen bei Pixel 50 von oben
		int yStartpunkt = 50;
		
		// yEndpunkt: Die y-Koordinate, bei der wir die Suche beenden
		// Beispiel: 190 bedeutet, wir hören bei Pixel 190 auf (nicht eingeschlossen)
		// Der Suchbereich in y-Richtung ist also 50 bis 189 (140 Pixel hoch)
		int yEndpunkt = 190;
		
		// Definition des Mittelpunkts des Kreisrings in x-Richtung
		// Dies ist die x-Koordinate des Zentrums, um das der Ring gezeichnet wird
		// Beispiel: 250 bedeutet, der Mittelpunkt liegt bei x = 250
		int mittelpunktX = 250;
		
		// Definition des Mittelpunkts des Kreisrings in y-Richtung
		// Dies ist die y-Koordinate des Zentrums, um das der Ring gezeichnet wird
		// Beispiel: 120 bedeutet, der Mittelpunkt liegt bei y = 120
		// Der Mittelpunkt des Rings ist also bei (250, 120)
		int mittelpunktY = 120;
		
		// Definition des inneren Radius des Kreisrings
		// Dies ist der Radius des inneren Kreises (das "Loch" in der Mitte)
		// Alle Punkte mit einem Abstand kleiner als innenRadius vom Mittelpunkt gehören NICHT zum Ring
		// Beispiel: 50 bedeutet, der innere Kreis hat einen Radius von 50 Pixeln
		int innenRadius = 50;
		
		// Definition des äußeren Radius des Kreisrings
		// Dies ist der Radius des äußeren Kreises (die äußere Grenze des Rings)
		// Alle Punkte mit einem Abstand größer als aussenRadius vom Mittelpunkt gehören NICHT zum Ring
		// Beispiel: 70 bedeutet, der äußere Kreis hat einen Radius von 70 Pixeln
		// Der Ring hat also eine "Dicke" von 70 - 50 = 20 Pixeln
		int aussenRadius = 70;
		
		// Berechnung des quadrierten inneren Radius
		// Warum quadrieren?
		//  - Um zu prüfen, ob ein Punkt im Ring liegt, müssen wir Abstände vergleichen
		//  - Die Abstandsformel enthält eine Quadratwurzel: Abstand = √((x2-x1)² + (y2-y1)²)
		//  - Quadratwurzeln sind rechenintensiv (langsam)
		//  - Stattdessen quadrieren wir beide Seiten der Ungleichung:
		//    Abstand ≥ innenRadius  wird zu  Abstand² ≥ innenRadius²
		//  - So sparen wir die Berechnung der Quadratwurzel!
		//
		// Beispiel: innenRadius = 50, also innenRadius2 = 50 * 50 = 2500
		int innenRadius2 = innenRadius * innenRadius;
		
		// Berechnung des quadrierten äußeren Radius
		// Gleiche Begründung wie beim inneren Radius: Wir vermeiden die Quadratwurzel
		// Beispiel: aussenRadius = 70, also aussenRadius2 = 70 * 70 = 4900
		int aussenRadius2 = aussenRadius * aussenRadius;
		
		// Äußere Schleife: Durchläuft alle x-Koordinaten im definierten Suchbereich
		// Die Schleife läuft von xStartpunkt bis xEndpunkt-1
		// Wir müssen nicht durch das ganze Bild laufen, nur durch den Bereich, wo der Ring sein könnte
		//
		// Aufbau der Schleife:
		// for (Initialisierung; Bedingung; Inkrementierung) { Anweisungen }
		//  - Initialisierung: int x = xStartpunkt; -> Startwert ist xStartpunkt (z.B. 180)
		//  - Bedingung: x < xEndpunkt; -> Schleife läuft, solange x kleiner als xEndpunkt ist (z.B. 320)
		//  - Inkrementierung: x++ -> Erhöht x nach jedem Durchlauf um 1
		//
		// Beispiel: Wenn xStartpunkt = 180 und xEndpunkt = 320, dann läuft die Schleife von 180 bis 319
		for (int x = xStartpunkt; x < xEndpunkt; x++) {
			
			// Innere Schleife: Durchläuft alle y-Koordinaten im definierten Suchbereich
			// Die Schleife läuft von yStartpunkt bis yEndpunkt-1
			// Für jede Kombination (x, y) prüfen wir, ob der Punkt im Ring liegt
			//
			// Aufbau der Schleife:
			// for (Initialisierung; Bedingung; Inkrementierung) { Anweisungen }
			//  - Initialisierung: int y = yStartpunkt; -> Startwert ist yStartpunkt (z.B. 50)
			//  - Bedingung: y < yEndpunkt; -> Schleife läuft, solange y kleiner als yEndpunkt ist (z.B. 190)
			//  - Inkrementierung: y++ -> Erhöht y nach jedem Durchlauf um 1
			//
			// Beispiel: Wenn yStartpunkt = 50 und yEndpunkt = 190, dann läuft die Schleife von 50 bis 189
			for (int y = yStartpunkt; y < yEndpunkt; y++) {
				
				// Speicherung der aktuellen x-Position
				// Hinweis: Diese Variable ist hier eigentlich überflüssig, da wir direkt x verwenden könnten
				//          Sie dient aber der Klarheit und Dokumentation des Codes
				int positionX = x;
				
				// Speicherung der aktuellen y-Position
				// Hinweis: Diese Variable ist hier eigentlich überflüssig, da wir direkt y verwenden könnten
				//          Sie dient aber der Klarheit und Dokumentation des Codes
				int positionY = y;
				
				// Berechnung der horizontalen Distanz vom Mittelpunkt
				// Dies ist die x-Komponente des Abstandsvektors
				//  - Wenn positionX > mittelpunktX: Der Punkt liegt rechts vom Mittelpunkt (positive Distanz)
				//  - Wenn positionX < mittelpunktX: Der Punkt liegt links vom Mittelpunkt (negative Distanz)
				//  - Wenn positionX = mittelpunktX: Der Punkt liegt direkt über/unter dem Mittelpunkt (Distanz = 0)
				//
				// Beispiel: Wenn positionX = 260 und mittelpunktX = 250, dann ist distanzX = 10
				//           (Der Punkt liegt 10 Pixel rechts vom Mittelpunkt)
				int distanzX = positionX - mittelpunktX;
				
				// Berechnung der vertikalen Distanz vom Mittelpunkt
				// Dies ist die y-Komponente des Abstandsvektors
				//  - Wenn positionY > mittelpunktY: Der Punkt liegt unterhalb vom Mittelpunkt (positive Distanz)
				//  - Wenn positionY < mittelpunktY: Der Punkt liegt oberhalb vom Mittelpunkt (negative Distanz)
				//  - Wenn positionY = mittelpunktY: Der Punkt liegt direkt links/rechts vom Mittelpunkt (Distanz = 0)
				//
				// Beispiel: Wenn positionY = 130 und mittelpunktY = 120, dann ist distanzY = 10
				//           (Der Punkt liegt 10 Pixel unterhalb vom Mittelpunkt)
				int distanzY = positionY - mittelpunktY;
				
				// Berechnung der quadrierten horizontalen Distanz
				// Wir quadrieren die Distanz, um später den Satz des Pythagoras anzuwenden
				// Das Quadrieren macht negative Werte positiv (was wir wollen, da Abstände immer positiv sind)
				//
				// Beispiel: Wenn distanzX = 10, dann ist distanzX2 = 10 * 10 = 100
				//           Wenn distanzX = -10, dann ist distanzX2 = (-10) * (-10) = 100
				//           Beide Fälle ergeben den gleichen Wert, was korrekt ist (Abstand ist immer positiv)
				int distanzX2 = distanzX * distanzX;
				
				// Berechnung der quadrierten vertikalen Distanz
				// Gleiche Begründung wie bei distanzX2
				// Beispiel: Wenn distanzY = 10, dann ist distanzY2 = 10 * 10 = 100
				int distanzY2 = distanzY * distanzY;
				
				
				// Erste Bedingung: Überprüfung, ob der Punkt innerhalb des äußeren Kreises liegt
				//
				// Mathematischer Hintergrund:
				//  - Nach dem Satz des Pythagoras ist der quadrierte Abstand: Abstand² = distanzX² + distanzY²
				//  - Ein Punkt liegt im äußeren Kreis, wenn: Abstand² ≤ aussenRadius²
				//  - Wir prüfen also: distanzX² + distanzY² ≤ aussenRadius²
				//
				// Beispiel mit den Werten von oben:
				//  - distanzX2 = 100, distanzY2 = 100
				//  - distanzX2 + distanzY2 = 200
				//  - aussenRadius2 = 4900
				//  - 200 ≤ 4900 ist wahr → Der Punkt liegt innerhalb des äußeren Kreises
				//
				// Warum diese Bedingung zuerst?
				//  - Wenn der Punkt außerhalb des äußeren Kreises liegt, gehört er sowieso nicht zum Ring
				//  - Dann müssen wir die zweite Bedingung (innerer Kreis) gar nicht erst prüfen
				//  - Dies spart Rechenzeit (Performance-Optimierung)
				if (distanzX2 + distanzY2 <= aussenRadius2) {
					
					// Zweite Bedingung: Überprüfung, ob der Punkt außerhalb des inneren Kreises liegt
					//
					// Mathematischer Hintergrund:
					//  - Ein Punkt liegt außerhalb des inneren Kreises, wenn: Abstand² ≥ innenRadius²
					//  - Wir prüfen also: distanzX² + distanzY² ≥ innenRadius²
					//
					// Beispiel mit den Werten von oben:
					//  - distanzX2 + distanzY2 = 200
					//  - innenRadius2 = 2500
					//  - 200 ≥ 2500 ist falsch → Der Punkt liegt im inneren Kreis (im "Loch")
					//  - Dieser Punkt wird also NICHT schwarz gefärbt
					//
					// Kombination beider Bedingungen:
					//  Ein Punkt liegt im Ring, wenn BEIDE Bedingungen erfüllt sind:
					//   1. distanzX² + distanzY² ≤ aussenRadius² (innerhalb des äußeren Kreises)
					//   2. distanzX² + distanzY² ≥ innenRadius² (außerhalb des inneren Kreises)
					//
					// Das bedeutet: innenRadius² ≤ distanzX² + distanzY² ≤ aussenRadius²
					if (distanzX2 + distanzY2 >= innenRadius2) {
						
						// Färben des Pixels schwarz, da er im Ring liegt
						// p[x][y] greift auf den Pixel an Position (x, y) zu
						// 0 ist der Farbwert für schwarz (alle Farbkanäle auf 0)
						// Die Zuweisung überschreibt die ursprüngliche Farbe des Pixels
						p[x][y] = 0;
						
					}
				}
				
			}
			
		}
		
		show(p);
	}
}
