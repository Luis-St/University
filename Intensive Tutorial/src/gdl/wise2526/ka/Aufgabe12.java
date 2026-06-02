package gdl.wise2526.ka;

import java.util.Arrays;

// Lösung der Klausuraufgabe 12 - Programmierung
// Aufgabe: Schreibe eine statische Methode f, die einen Parameter x vom Typ int-Array hat und keinen Rückgabewert.
//
// Die Methode f soll alle Werte des Arrays, die sich zwischen dem ersten Stelle, an der sich der Wert 1 befindet,
// und der zweiten Stelle, an der sich der Wert 1 befindet, auf 0 setzen.
//
// Sonderfälle:
//  - Enthält der Array nur eine Stelle mit dem Wert 1, so sollen alle folgenden Stellen bis zum Ende des Arrays auf 0 gesetzt werden.
//  - Enthält der Array keine Stelle mit dem Wert 1, passiert nichts (Array bleibt unverändert).
//  - Die Werte an den Positionen der Einsen selbst werden NICHT auf 0 gesetzt, nur die Werte dazwischen.
//
// Beispiele:
//  Vorher:  [2, 1, 3, 4, 1, 7, 7]     →  Nachher: [2, 1, 0, 0, 1, 7, 7]
//  Vorher:  [2, 3, 1, 9, 9, 7, 7, 1, 2, 1, 6, 1]  →  Nachher: [2, 3, 1, 0, 0, 0, 0, 1, 2, 1, 6, 1]
//  Vorher:  [1, 1, 2, 2]              →  Nachher: [1, 1, 2, 2]  (keine Werte zwischen den beiden Einsen)
//  Vorher:  [5, 3, 5, 1, 4, 8]        →  Nachher: [5, 3, 5, 1, 0, 0]  (nur eine 1 → Rest auf 0)

public class Aufgabe12 {
	
	// Hauptmethode zum Testen der f-Methode
	// Erstellt ein Test-Array, gibt es vor und nach der Verarbeitung aus
	public static void main(String[] args) {
		
		// Erstellen eines Test-Arrays mit verschiedenen Werten
		// Dieses Array enthält eine 1 an Index 3
		// Nach der Verarbeitung sollen die Werte ab Index 4 auf 0 gesetzt werden (da es keine zweite 1 gibt)
		int[] x = { 5, 3, 5, 1, 4, 8 };
		
		// Ausgabe des Arrays VOR der Verarbeitung
		// Arrays.toString wandelt das Array in eine lesbare String-Darstellung um
		// Beispiel: [5, 3, 5, 1, 4, 8]
		System.out.println(Arrays.toString(x));
		
		// Aufruf der Methode f mit dem Array x
		// Die Methode verändert das Array direkt (pass by reference)
		// Das bedeutet: Die Änderungen wirken sich auf das ursprüngliche Array aus
		f(x);
		
		// Ausgabe des Arrays NACH der Verarbeitung
		// Erwartetes Ergebnis: [5, 3, 5, 1, 0, 0]
		// Die Werte nach der ersten 1 (4 und 8) wurden auf 0 gesetzt
		System.out.println(Arrays.toString(x));
	}
	
	// Klassenmethode zum Setzen von Werten zwischen zwei Einsen auf 0
	// Klassenmethode:
	//  - public: Die Methode ist von außerhalb der Klasse aufrufbar
	//  - static: Die Methode kann direkt aufgerufen werden ohne ein Objekt zu erstellen
	//  - void: Die Methode gibt keinen Wert zurück
	//         Sie verändert stattdessen das übergebene Array direkt
	//  - f: Name der Methode (kann frei gewählt werden)
	//  - (int[] x): Die Methode erwartet einen Parameter:
	//               - x: ein Array von int-Werten, das verändert werden soll
	public static void f(int[] x) {
		
		// ====================================================================
		// TEIL 1: Suche nach der ersten 1 im Array
		// ====================================================================
		
		// Variable zur Speicherung des Index der ersten 1
		// Initialisierung mit x.length (der Länge des Arrays)
		// Warum x.length?
		//  - x.length ist ein Wert außerhalb des gültigen Index-Bereichs (0 bis x.length-1)
		//  - Wenn wir keine 1 finden, bleibt der Wert x.length
		//  - Dies ermöglicht es uns später zu prüfen, ob eine 1 gefunden wurde
		//  - Wenn indexDerErstenEins == x.length, dann wurde keine 1 gefunden
		int indexDerErstenEins = x.length;
		
		// Erste Schleife: Suche nach der ersten 1 im Array
		// Die Schleife durchläuft das Array von Anfang bis Ende
		// Aufbau der Schleife:
		// for (Initialisierung; Bedingung; Inkrementierung) { Anweisungen }
		//  - Initialisierung: int i = 0; -> Startwert des Index ist 0 (erstes Element)
		//  - Bedingung: i < x.length; -> Schleife läuft, solange i kleiner als die Länge des Arrays ist
		//  - Inkrementierung: i++ -> Erhöht i nach jedem Durchlauf um 1 (nächstes Element)
		for (int i = 0; i < x.length; i++) {
			
			// Überprüfung, ob das aktuelle Element den Wert 1 hat
			// x[i] greift auf das Element an Index i zu
			// == ist der Gleichheitsoperator (nicht =, das wäre eine Zuweisung!)
			if (x[i] == 1) {
				
				// Speichern des Index, an dem die erste 1 gefunden wurde
				// Beispiel: Wenn i = 3 und x[3] = 1, dann wird indexDerErstenEins = 3
				indexDerErstenEins = i;
				
				// Abbrechen der Schleife durch Setzen von i auf x.length
				// Warum funktioniert das?
				//  - Nach dieser Zuweisung wird i++ ausgeführt (Teil der for-Schleife)
				//  - Dann ist i = x.length + 1
				//  - Die Bedingung i < x.length ist dann falsch
				//  - Die Schleife wird beendet
				//
				// Alternative Methode (eleganter, aber hier nicht verwendet):
				//  - break; → beendet die Schleife sofort
				//
				// Warum die Schleife abbrechen?
				//  - Wir suchen nur die ERSTE 1
				//  - Sobald wir sie gefunden haben, müssen wir nicht weitersuchen
				//  - Dies spart Rechenzeit (Performance)
				i = x.length;
				
			}
			
		}
		
		// ====================================================================
		// TEIL 2: Suche nach der zweiten 1 im Array
		// ====================================================================
		
		// Variable zur Speicherung des Index der zweiten 1
		// Initialisierung mit x.length (gleiche Begründung wie bei indexDerErstenEins)
		// Wenn wir keine zweite 1 finden, bleibt der Wert x.length
		int indexDerZweitenEins = x.length;
		
		// Zweite Schleife: Suche nach der zweiten 1 im Array
		// WICHTIG: Diese Schleife startet NACH der ersten 1!
		// Aufbau der Schleife:
		// for (Initialisierung; Bedingung; Inkrementierung) { Anweisungen }
		//  - Initialisierung: int i = indexDerErstenEins + 1;
		//                     Startwert ist die Position NACH der ersten 1
		//                     Beispiel: Wenn indexDerErstenEins = 3, dann starten wir bei i = 4
		//                     Warum +1? Weil wir die erste 1 selbst nicht nochmal betrachten wollen
		//  - Bedingung: i < x.length; -> Schleife läuft, solange i kleiner als die Länge des Arrays ist
		//  - Inkrementierung: i++ -> Erhöht i nach jedem Durchlauf um 1 (nächstes Element)
		//
		// Sonderfall: Wenn keine erste 1 gefunden wurde (indexDerErstenEins == x.length):
		//  - Dann ist der Startwert i = x.length + 1
		//  - Die Bedingung i < x.length ist sofort falsch
		//  - Die Schleife wird gar nicht ausgeführt (was korrekt ist!)
		for (int i = indexDerErstenEins + 1; i < x.length; i++) {
			
			// Überprüfung, ob das aktuelle Element den Wert 1 hat
			// Dies ist die gleiche Prüfung wie in der ersten Schleife
			if (x[i] == 1) {
				
				// Speichern des Index, an dem die zweite 1 gefunden wurde
				// Beispiel: Wenn i = 7 und x[7] = 1, dann wird indexDerZweitenEins = 7
				indexDerZweitenEins = i;
				
				// Abbrechen der Schleife durch Setzen von i auf x.length
				// Gleiche Technik wie in der ersten Schleife
				// Warum abbrechen? Wir suchen nur die ZWEITE 1, nicht weitere Einsen
				i = x.length;
				
			}
			
		}
		
		// ====================================================================
		// TEIL 3: Setzen der Werte zwischen den beiden Einsen auf 0
		// ====================================================================
		
		// Dritte Schleife: Setze alle Werte zwischen der ersten und zweiten 1 auf 0
		// WICHTIG: Diese Schleife läuft zwischen den beiden gefundenen Positionen!
		// Aufbau der Schleife:
		// for (Initialisierung; Bedingung; Inkrementierung) { Anweisungen }
		//  - Initialisierung: int i = indexDerErstenEins + 1;
		//                     Startwert ist die Position NACH der ersten 1
		//                     Beispiel: Wenn indexDerErstenEins = 1, dann starten wir bei i = 2
		//                     Warum +1? Die erste 1 selbst soll NICHT auf 0 gesetzt werden!
		//  - Bedingung: i < indexDerZweitenEins;
		//               Schleife läuft nur bis VOR die zweite 1
		//               Beispiel: Wenn indexDerZweitenEins = 4, dann läuft die Schleife bis i = 3
		//               Warum <? Die zweite 1 selbst soll NICHT auf 0 gesetzt werden!
		//  - Inkrementierung: i++ -> Erhöht i nach jedem Durchlauf um 1 (nächstes Element)
		//
		// Beispiel mit [2, 1, 3, 4, 1, 7, 7]:
		//  - indexDerErstenEins = 1
		//  - indexDerZweitenEins = 4
		//  - Die Schleife läuft von i = 2 bis i = 3
		//  - x[2] = 0 (war vorher 3)
		//  - x[3] = 0 (war vorher 4)
		//  - Ergebnis: [2, 1, 0, 0, 1, 7, 7]
		//
		// Sonderfall 1: Keine erste 1 gefunden (indexDerErstenEins == x.length):
		//  - Startwert: i = x.length + 1
		//  - Bedingung: i < x.length ist sofort falsch
		//  - Schleife wird nicht ausgeführt → korrekt, da nichts zu tun ist
		//
		// Sonderfall 2: Keine zweite 1 gefunden (indexDerZweitenEins == x.length):
		//  - Die Schleife läuft von indexDerErstenEins + 1 bis x.length - 1
		//  - Dies setzt alle Werte nach der ersten 1 bis zum Ende auf 0
		//  - Beispiel: [5, 3, 5, 1, 4, 8] → [5, 3, 5, 1, 0, 0]
		//
		// Sonderfall 3: Beide Einsen direkt nebeneinander (z.B. indexDerErstenEins = 1, indexDerZweitenEins = 2):
		//  - Startwert: i = 2
		//  - Bedingung: i < 2 ist sofort falsch
		//  - Schleife wird nicht ausgeführt → korrekt, da keine Werte dazwischen liegen
		for (int i = indexDerErstenEins + 1; i < indexDerZweitenEins; i++) {
			
			// Setzen des aktuellen Elements auf 0
			// x[i] greift auf das Element an Index i zu
			// = ist der Zuweisungsoperator, weist den Wert 0 zu
			// Dies überschreibt den ursprünglichen Wert an dieser Position
			x[i] = 0;
			
		}
		
		// Ende der Methode
		// Da die Methode void ist (keinen Rückgabewert hat), wird hier kein return benötigt
		// Die Änderungen am Array x sind bereits durchgeführt und wirken sich auf das ursprüngliche Array aus
		// (Arrays werden in Java "by reference" übergeben, nicht "by value")
	}
}
