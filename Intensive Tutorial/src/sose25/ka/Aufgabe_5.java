package sose25.ka;

import java.util.Arrays;

public class Aufgabe_5 {

	public static void main(String[] args) {

		boolean[] array = new boolean[] { 
			false, false, false, false, true, true, false, false, false, true, false,true 
		};
		
		f(array);
		
		System.out.println(Arrays.toString(array));

	}
	
	public static void g(boolean x[], int n) {
		
		for (int i = 0; i < n; i++) {
			
			f(x);
			
		}
	
	}
	
	public static void f(boolean x[]) {

		for (int i = 0; i < x.length; i++) { // Durchlaufen des Arrays

			if (x[i] == true) { // Prüfung ob Wert an aktueller Stelle true

				// Setzen des vorherigen Wertes auf true
				// Nur dann wenn wir nicht an stelle 0 des Arrays sind
				// da Position -1 im Array zu einem Fehler führen würde
				if (i > 0) { // i != 0

					x[i - 1] = true;

				}

				// Setzen des nächsten Wertes auf true
				// Nur dann wenn wir nicht an der letzten Stelle des Arrays sind
				// da wir sonst einen Fehler erhalten da wir auf eine Stelle zugreifen die außerhalb des Arrays liegt
				if (i < x.length - 1) {

					// Das Setzen erfolgt nur dann wenn der Wert an der nächsten Stelle false ist
					// Lassen wir diese Bedienung weg wird das ganze array auf true gesetzt
					// Durch das i++ überspringen wir den nächsten Wert da wir diesen gesetzt haben
					// Ohne das hochzählen würden wir diese Logik für jede kommende stelle wiederholen
					// und so das array mit true werten füllen
					if (x[i + 1] == false) {

						x[i + 1] = true;

						i++;

					}
				}
			}
		}
	}

}
