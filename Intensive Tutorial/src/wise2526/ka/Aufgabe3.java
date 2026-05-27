package wise2526.ka;

// Lösung der Klausuraufgabe 3
// Schreibe eine Klassenmethode, die in einem Array von int-Werten nach zwei aufeinanderfolgenden Zahlen sucht,
// deren Summe einem gegebenen Zielwert entspricht, und gibt die Position der ersten Zahl zurück.
public class Aufgabe3 {
	
	// Tests der f-Methode
	// Es wird ein Array von int-Werten erstellt und die Methode mit einem Zielwert aufgerufen.
	// Das Ergebnis wird zur Überprüfung ausgegeben.
	public static void main(String[] args) {
		
		// Testarray mit den Werten 1, 5, 1, 8, 3
		// Die Methode soll prüfen, ob zwei aufeinanderfolgende Zahlen die Summe 19 ergeben
		// Hinweis: Die gesuchte Summe 19 existiert nicht im Array (maximale aufeinanderfolgende Summe ist 1+8=9)
		//          Daher wird die Methode -1 zurückgeben
		int[] a = { 1, 5, 1, 8, 3 };
		int b = 19;
		
		// Aufruf der Methode f mit dem Array a und dem Zielwert b
		// Die Methode gibt die Position zurück, an der zwei aufeinanderfolgende Zahlen die Summe b ergeben
		// Falls keine solche Position existiert, wird -1 zurückgegeben
		int position = f(a, b);
		System.out.println(position);
	}
	
	// Klassenmethode zur Suche nach zwei aufeinanderfolgenden Zahlen mit einer bestimmten Summe
	// Klassenmethode:
	//  - public: Die Methode ist von außerhalb der Klasse aufrufbar (z.B. aus einer anderen Klasse) (noch nicht relevant hier)
	//  - static: Die Methode kann direkt aufgerufen werden, entweder f(...) oder Aufgabe_3.f(...)
	//            static ist für eine Klassenmethode immer notwendig.
	//  - int: Der Rückgabewert der Methode ist vom Typ int (eine ganze Zahl)
	//         Gibt die Position (Index) zurück, an der die Bedingung erfüllt ist, oder -1 falls nicht gefunden
	//  - f: Name der Methode, der frei gewählt werden kann, mithilfe dieses Namens wird die Methode aufgerufen.
	//  - (int[] a, int b): Die Methode erwartet zwei Parameter:
	//                      - a: ein Array von int-Werten, in dem gesucht werden soll
	//                      - b: der Zielwert, den die Summe zweier aufeinanderfolgender Zahlen ergeben soll
	public static int f(int[] a, int b) {
		
		// Schleife zum Durchgehen aller Paare von aufeinanderfolgenden Zahlen im Array.
		// Die Schleife läuft von 0 bis zur vorletzten Position (a.length - 1)
		// Wir gehen nur bis a.length - 1, weil wir im Schleifeninneren auf a[i + 1] zugreifen (die nächste Zahl)
		// Würden wir bis a.length laufen, würden wir bei a[i + 1] einen IndexOutOfBoundsException bekommen
		// Aufbau der Schleife:
		// for (Initialisierung; Bedingung; Inkrementierung) { Anweisungen }
		//  - Initialisierung: int i = 0; -> Startwert des Index, setzt den Index i zu Beginn auf 0
		//  - Bedingung: i < a.length - 1; -> Schleife läuft, solange i kleiner ist als die Länge des Arrays minus 1
		//  - Inkrementierung: i++ -> Erhöht den Index i nach jedem Schleifendurchlauf um 1
		for (int i = 0; i < a.length - 1; i++) {
			
			// Ausgabe des aktuellen Index zur Veranschaulichung des Ablaufs (Logging)
			// Hilft beim Debuggen und Verstehen, welche Positionen die Methode durchläuft
			System.out.println(i);
			
			// Speicherung der aktuellen Position in einer separaten Variable
			// Hinweis: Diese Variable wird im Code nicht weiter verwendet, dient aber zur Verdeutlichung
			//          In einer optimierten Version könnte diese Zeile weggelassen werden
			int aktuellePoition = i;
			
			// Speicherung der Zahl an der aktuellen Position
			// a[i] greift auf das Element an Index i im Array a zu
			// Beispiel: Wenn i = 0, dann ist aktuelleZahl = a[0] = 1
			int aktuelleZahl = a[i];
			
			// Speicherung der Zahl an der nächsten Position
			// a[i + 1] greift auf das Element an Index i + 1 im Array a zu
			// Dies ist die nächste Zahl nach der aktuellen Zahl
			// Beispiel: Wenn i = 0, dann ist naechsteZahl = a[1] = 5
			int naechsteZahl = a[i + 1];
			
			// Berechnung der Summe der aktuellen und der nächsten Zahl
			// Diese Summe wird mit dem Zielwert b verglichen
			// Beispiel: Wenn aktuelleZahl = 1 und naechsteZahl = 5, dann ist summe = 6
			int summe = aktuelleZahl + naechsteZahl;
			
			// Überprüfung, ob die berechnete Summe dem gesuchten Zielwert entspricht
			// if-Bedingung:
			//  - summe == b: Vergleicht die berechnete Summe mit dem Zielwert b
			//  - == ist der Gleichheitsoperator (nicht = , das wäre eine Zuweisung!)
			//  - Wenn die Bedingung erfüllt ist (summe ist gleich b), wird der Code im if-Block ausgeführt
			if (summe == b) {
				
				// Rückgabe der aktuellen Position i
				// Dies bedeutet: An Position i und i+1 stehen zwei Zahlen, deren Summe gleich b ist
				// Die Methode wird hier sofort beendet und gibt i zurück
				// Beispiel: Wenn i = 2 und a[2] + a[3] = b, dann wird 2 zurückgegeben
				return i;
				
			}
		}
		
		// Rückgabe von -1, falls keine zwei aufeinanderfolgenden Zahlen gefunden wurden, deren Summe b ergibt
		// Diese Zeile wird nur erreicht, wenn die Schleife vollständig durchgelaufen ist, ohne dass die if-Bedingung erfüllt wurde
		// -1 ist ein typischer Rückgabewert, um anzuzeigen, dass etwas nicht gefunden wurde
		// (ähnlich wie bei der indexOf-Methode in Java)
		return -1;
	}
}
