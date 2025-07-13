import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

class Territorium {
	
	// jede Position, die keine Kachel ist, ist eine Mauer
	private boolean[][] istKachel;
	
	private AtomicInteger[][] zahlenKoerner;
	
	// Konstruktor fuer Territorium ohne Koerner aber mit Mauern 
	Territorium(boolean[][] istKachel) {
		this.istKachel = istKachel;
	}
	
	// Konstruktor fuer Territorium ohne Mauern aber mit Koernern
	Territorium(int[][] zahlKoerner) {
		this.istKachel = new boolean[zahlKoerner.length][zahlKoerner[0].length];
		this.zahlenKoerner = new AtomicInteger[zahlKoerner.length][zahlKoerner[0].length];
		for (int i = 0; i < zahlKoerner.length; i++) {
			for (int j = 0; j < zahlKoerner[0].length; j++) {
				this.zahlenKoerner[i][j] = new AtomicInteger(zahlKoerner[i][j]);
				this.istKachel[i][j] = true;
			}
		}
	}
	
	boolean istAusserhalb(int[] position) {
		if (position[0] < 0 || position[0] >= this.istKachel.length) {
			return true;
		} else return position[1] < 0 || position[1] >= this.istKachel[0].length;
	}
	
	Richtung gibGegenrichtung(Richtung r) {
		if (r == null) {
			return null;
		}
		if (r == Richtung.OBEN) {
			return Richtung.UNTEN;
		} else if (r == Richtung.RECHTS) {
			return Richtung.LINKS;
		} else if (r == Richtung.UNTEN) {
			return Richtung.OBEN;
		} else if (r == Richtung.LINKS) {
			return Richtung.RECHTS;
		} else {
			return null;
		}
	}
	
	int gibKoerner(int[] position) {
		if (this.zahlenKoerner == null) {
			return 0;
		} else {
			return this.zahlenKoerner[position[0]][position[1]].get();
		}
		
	}
	
	static boolean[][] wandleIntegerInBoolean(int[][] kachelnInt) {
		boolean[][] istKachel = new boolean[kachelnInt.length][kachelnInt[0].length];
		int zeilenNr = 0;
		for (int[] zeile : kachelnInt) {
			int spaltenNr = 0;
			for (int istKachelInt : zeile) {
				istKachel[zeilenNr][spaltenNr] = (istKachelInt == 1);
				spaltenNr++;
			}
			zeilenNr++;
		}
		return istKachel;
	}
	
	boolean istKachel(int[] position) {
		if (this.istAusserhalb(position)) {
			return false;
		} else {
			return this.istKachel[position[0]][position[1]];
		}
	}
	
	Queue<Richtung> gibVerzweigungen(Sucher sucher) {
		Queue<Richtung> verzweigungen = new LinkedList<>();
		if (this.verzweigungExistiert(Richtung.OBEN, sucher)) {
			verzweigungen.add(Richtung.OBEN);
		}
		if (this.verzweigungExistiert(Richtung.RECHTS, sucher)) {
			verzweigungen.add(Richtung.RECHTS);
		}
		if (this.verzweigungExistiert(Richtung.UNTEN, sucher)) {
			verzweigungen.add(Richtung.UNTEN);
		}
		if (this.verzweigungExistiert(Richtung.LINKS, sucher)) {
			verzweigungen.add(Richtung.LINKS);
		}
		return verzweigungen;
	}
	
	// wenn r == null, wird jede Verzweigungsrichtung als true gewertet,
	// also auch die, aus der der Sucher eventuell gekommen ist.
	// Ist wichtig, wenn der Sucher gerade seine Suche beginnt, und
	// von nirgendwo hergekommen ist.
	private boolean verzweigungExistiert(Richtung r, Sucher sucher) {
		int[] moeglichePosition = this.gibNeuePosition(r, sucher.gibPosition());
		return this.istKachel(moeglichePosition) && r != this.gibGegenrichtung(sucher.gibGehRichtung());
	}
	
	public int[] gibNeuePosition(Richtung r, int[] altePosition) {
		int[] neuePosition = { altePosition[0], altePosition[1] };
		switch (r) {
			case OBEN:
				neuePosition[0] = altePosition[0] - 1;
				break;
			case RECHTS:
				neuePosition[1] = altePosition[1] + 1;
				break;
			case UNTEN:
				neuePosition[0] = altePosition[0] + 1;
				break;
			case LINKS:
				neuePosition[1] = altePosition[1] - 1;
				break;
		}
		return neuePosition;
	}
	
	void dekrementiereKoerner(int[] position) {
		this.zahlenKoerner[position[0]][position[1]].decrementAndGet();
	}
	
	void setzeKoernerZahl(int[] position, int zahlKoerner) {
		this.zahlenKoerner[position[0]][position[1]].set(zahlKoerner);
	}
	
	public void gibKoernerAus() {
		for (AtomicInteger[] zeile : this.zahlenKoerner) {
			for (AtomicInteger zahlKoerner : zeile) {
				System.out.print(zahlKoerner.get() + "\t");
			}
			System.out.println();
		}
		
	}
	
	int gibZeilenZahl() {
		return this.istKachel.length;
	}
	
	int gibSpaltenZahl() {
		return this.istKachel[0].length;
	}
	
}
