package gdl.wise2526.pa;

// Lösung der Praktikumsaufgabe 3 a)
// Schreibe eine Klassenmethode, die ein Array von double-Werten entgegennimmt und die Summe aller Werte berechnet und zurückgibt.
public class Aufgabe3 {
	
	// Tests der summeBerechnen-Methode
	// Es werden verschiedene Arrays von double-Werten erstellt und die Methode aufgerufen.
	// Die Ergebnisse werden zur überprüfung ausgegeben.
	public static void main(String[] args) {
		
		// In array a sind nur positive ganzzahlige Werte enthalten
		// Die Summe sollte 10.0 ergeben
		// Hinweis: 'new double[]' ist nicht zwingend notwendig, kann auch weggelassen werden.
		//          Es wäre also auch möglich, das Array so zu erstellen:
		//          double[] a = { 1, 2, 3, 4 };
		double[] a = new double[] { 1, 2, 3, 4 };
		double summeA = summeBerechnen(a);
		System.out.println("Summe von a: " + summeA);
		
		// In array b sind positive Dezimalwerte enthalten
		// Die Summe sollte 61.0 ergeben
		// Hinweis: 'new double[]' ist nicht zwingend notwendig, kann auch weggelassen werden.
		//          Es wäre also auch möglich, das Array so zu erstellen:
		//          double[] b = { 10.5, 20.5, 30.0 };
		double[] b = new double[] { 10.5, 20.5, 30.0 };
		double summeB = summeBerechnen(b);
		System.out.println("Summe von b: " + summeB);
		
		// In array c sind negative ganzzahlige Werte enthalten
		// Die Summe sollte -10.0 ergeben
		// Hinweis: 'new double[]' ist nicht zwingend notwendig, kann auch weggelassen werden.
		//          Es wäre also auch möglich, das Array so zu erstellen:
		//          double[] c = { -1, -2, -3, -4 };
		double[] c = new double[] { -1, -2, -3, -4 };
		double summeC = summeBerechnen(c);
		System.out.println("Summe von c: " + summeC);
		
		// In array d sind gemischte positive und negative Dezimalwerte enthalten
		// Die Summe sollte 5.0 ergeben
		// Hinweis: 'new double[]' ist nicht zwingend notwendig, kann auch weggelassen werden.
		//          Es wäre also auch möglich, das Array so zu erstellen:
		//          double[] d = { 5, -3, 4.5, -1.5 };
		double[] d = new double[] { 5, -3, 4.5, -1.5 };
		double summeD = summeBerechnen(d);
		System.out.println("Summe von d: " + summeD);
	}
	
	// Klassenmethode zur Berechnung der Summe eines Arrays von double-Werten
	// Klassenmethode:
	//  - public: Die Methode ist von außerhalb der Klasse aufrufbar (z.B. aus einer anderen Klasse) (noch nicht relevant hier)
	//  - static: Die Methode kann direkt aufgrufen werden, entweder summeBerechnen(...) oder Aufgabe_3.summeBerechnen(...)
	//            static ist für eine Klassenmethode immer notwendig.
	//  - double: Der Rückgabewert der Methode ist vom Typ double (eine Kommazahl)
	//  - summeBerechnen: Name der Methode, der frei gewählt werden kann, mithilfe dieses Namens wird die Methode aufgerufen.
	//  - (double[] werte): Die Methode erwartet einen Parameter, ein Array von double-Werten, der beim Aufruf übergeben werden muss.
	//					    Der Parametername ist "werte" und kann innerhalb der Methode verwendet werden, kann auch frei gewählt werden.
	public static double summeBerechnen(double[] werte) {
		// Variable zur Speicherung der Summe, initialisiert mit 0.0
		// Wird benötigt da wir keine direkte möglichkeit haben, die Summe direkt von dem Array zu berechnen (es gibt keine eingebaute Funktion dafür)
		// Da wir nicht wissen wie viele Werte im Array sind, müssen wir die Summe "manuell" berechnen, indem wir alle Werte einzeln durchgehen und zur Summe hinzufügen.
		// Aufbau:
		//  - double: Datentyp der Variable, in diesem Fall eine Kommazahl
		//  - summe: Name der Variable, kann frei gewählt werden, wird verwendet um auf die Variable zuzugreifen
		//  - = 0.0: Initialisierung der Variable mit dem Wert 0.0, damit die Summe bei 0 beginnt
		double summe = 0.0;
		
		// Schleife zum Durchgehen aller Werte im Array.
		// Die Schleife läuft von 0 bis zur Länge des Arrays (werte.length)
		// Der Index i wird verwendet, um auf die einzelnen Werte im Array zuzugreifen.
		// In jedem Schleifendurchlauf wird der aktuelle Wert zur summe hinzugefügt.
		// Aufbau der Schleife:
		// for (Initialisierung; Bedingung; Inkrementierung) { Anweisungen }
		//  - Initialisierung: int i = 0; -> Startwert des Index, setzt den Index i zubeginn auf 0
		//  - Bedingung: i < werte.length; -> Schleife läuft, solange i kleiner ist als die Länge des Arrays ist wird die Anweisungen ausgeführt
		//  - Inkrementierung: i++ -> Erhöht den Index i nach jedem Schleifendurchlauf um 1
		for (int i = 0; i < werte.length; i++) {
			
			// Ausgabe des aktuellen Index und des Werts an diesem Index (zur Veranschaulichung) (wird auch Logging genannt)
			// Ausgabeformat: (Index) -> (Wert): i -> werte[i]
			// Beispiel: (Index) -> (Wert): 0 -> 1.0
			System.out.println("(Index) -> (Wert): " + i + " -> " + werte[i]);
			
			
			// Ausgabe der Summe vor dem Hinzufügen des aktuellen Werts
			System.out.println("Summe vorher: " + summe);
			
			// Hinzufügen des aktuellen Werts zur Summe
			// Aufbau:
			//  - summe: Variable in der die Summe gespeichert wird
			//           Hier die gleiche Variable wie oben definiert, wir überschreiben den alten Wert mit dem neuen Wert
			//  - = : Zuweisungsoperator, weist den Wert auf der rechten Seite der Variable auf der linken Seite zu
			//  - summe + werte[i]: Berechnung des neuen Werts für die summe
			//                      Nimmt den aktuellen Wert der summe und addiert den Wert an Index i des Arrays werte hinzu.
			//
 			// Könnten wir in lang auch wie folgt schreiben:
			// double aktuellerWert = werte[i];
			// double neueSumme = summe + aktuellerWert;
			// summe = neueSumme;
			//
 			// Aber die Kurzform ist üblicher und wird häufiger verwendet, kann aber auch noch weiter verkürzt werden zu:
			// summe += werte[i];
			summe = summe + werte[i];
			
			// Ausgabe der Summe nach dem Hinzufügen des aktuellen Werts
			System.out.println("Summe nachher: " + summe);
			
		}
		
		// Rückgabe der berechneten Summe
		// Die Methode gibt den Wert der Variable summe zurück, wenn sie aufgerufen wird
		// Die Rückgabe ist erforderlich, da der Rückgabewert der Methode als double deklariert ist. (void wäre kein Rückgabewert)
		// Die Rückgabe erfolgt mit dem Schlüsselwort 'return', gefolgt vom Rückgabewert.
		return summe;
	}
}
