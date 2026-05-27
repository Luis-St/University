package sose26;

public class CinemaReservation {
	
	public static void main(String[] args) {
		
		int reihen = 12;
		int plaetze = 8;
		int zufall = 10;
		java.util.Random rng = new java.util.Random(zufall);
		
		boolean[][] kino = new boolean[reihen][plaetze];
		
		for (int reihe = 0; reihe < kino.length; reihe++) {
			for (int platz = 0; platz < kino[reihe].length; platz++) {
				kino[reihe][platz] = rng.nextBoolean();
			}
		}
		
		anzeigen(kino);
		
		System.out.println("\nFreie Plaetze: " + freiePlaetze(kino));
		System.out.println("Belegungsgrad: " + belegungsgrad(kino) + "%");
		System.out.println("Reihe 0 komplett frei? " + reiheFrei(kino, 0));
		
		System.out.println("\nReservierung Reihe 2, Platz 3: " + reservieren(kino, 2, 3));
		System.out.println("Nochmal Reihe 2, Platz 3: " + reservieren(kino, 2, 3));
		System.out.println("Ungueltig Reihe 99, Platz 0: " + reservieren(kino, 99, 0));
		
		System.out.println("\nGruppenreservierung (3 Personen) in Reihe: " + gruppenReservierung(kino, 3));
		
		System.out.println();
		anzeigen(kino);
	}
	
	// a)
	static void anzeigen(boolean[][] kino) {
		for (int reihe = 0; reihe < kino.length; reihe++) {
			System.out.print("Reihe " + reihe + ": ");
			for (int platz = 0; platz < kino[reihe].length; platz++) {
				System.out.print(kino[reihe][platz] ? "X" : "O");
			}
			System.out.println();
		}
	}
	
	// b)
	static boolean reservieren(boolean[][] kino, int r, int p) {
		if (r < 0 || r >= kino.length || p < 0 || p >= kino[r].length) {
			System.out.println("Fehler: Ungueltige Indizes (r=" + r + ", p=" + p + ").");
			return false;
		}
		if (kino[r][p]) {
			System.out.println("Fehler: Platz " + p + " in Reihe " + r + " ist bereits belegt.");
			return false;
		}
		kino[r][p] = true;
		return true;
	}
	
	// c)
	static int freiePlaetze(boolean[][] kino) {
		int frei = 0;
		for (int reihe = 0; reihe < kino.length; reihe++) {
			for (int platz = 0; platz < kino[reihe].length; platz++) {
				if (!kino[reihe][platz]) {
					frei++;
				}
			}
		}
		return frei;
	}
	
	// d)
	static boolean reiheFrei(boolean[][] kino, int reihe) {
		for (int platz = 0; platz < kino[reihe].length; platz++) {
			if (kino[reihe][platz]) {
				return false;
			}
		}
		return true;
	}
	
	// e)
	static int gruppenReservierung(boolean[][] kino, int anzahl) {
		for (int reihe = 0; reihe < kino.length; reihe++) {
			int freiInFolge = 0;
			for (int platz = 0; platz < kino[reihe].length; platz++) {
				if (!kino[reihe][platz]) {
					freiInFolge++;
					if (freiInFolge == anzahl) {
						int start = platz - anzahl + 1;
						for (int i = start; i <= platz; i++) {
							kino[reihe][i] = true;
						}
						return reihe;
					}
				} else {
					freiInFolge = 0;
				}
			}
		}
		return -1;
	}
	
	// f)
	static double belegungsgrad(boolean[][] kino) {
		int gesamt = 0;
		int belegt = 0;
		for (int reihe = 0; reihe < kino.length; reihe++) {
			gesamt += kino[reihe].length;
			for (int platz = 0; platz < kino[reihe].length; platz++) {
				if (kino[reihe][platz]) {
					belegt++;
				}
			}
		}
		return (double) belegt / gesamt * 100;
	}
	
}
