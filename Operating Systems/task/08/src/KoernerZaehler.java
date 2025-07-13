import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.*;

class KoernerZaehler extends SucherImpl implements Callable<Integer> {
	
	private static final int[][] koernerZahlen = {
		{ 1, 0, 2, 0, 3, 0, 4, 0, 5, 0 },
		{ 6, 0, 7, 0, 8, 0, 9, 0, 10, 0 },
		{ 1, 0, 2, 0, 3, 0, 4, 0, 5, 0 },
		{ 6, 0, 7, 0, 8, 0, 9, 0, 10, 17 }
	};
	
	public static void main(String[] args) throws Exception {
		Territorium territorium = new Territorium(koernerZahlen);
		final int ANZAHL_SUCHER = territorium.gibZeilenZahl();
		final int ANZAHL_GLEICHZEITIGE_THREADS = 2;
		ExecutorService pool = Executors.newFixedThreadPool(ANZAHL_GLEICHZEITIGE_THREADS);
		Set<Future<Integer>> dieFutures = new LinkedHashSet<>();
		
		// Für jede Zeile einen KoernerZaehler erzeugen
		for (int i = 0; i < ANZAHL_SUCHER; i++) {
			Callable<Integer> zaehler = new KoernerZaehler(territorium, new int[] { i, 0 }, Richtung.RECHTS);
			Future<Integer> future = pool.submit(zaehler);
			dieFutures.add(future);
		}
		
		pool.shutdown();
		
		int anzahlKoerner = 0;
		
		// Ergebnisse aus allen Future-Objekten sammeln
		for (Future<Integer> future : dieFutures) {
			anzahlKoerner += future.get();
		}
		
		System.out.println("Die Anzahl der Koerner betraegt: " + anzahlKoerner);
	}
	
	KoernerZaehler(Territorium territorium, int[] startPunkt, Richtung gehRichtung) {
		super(startPunkt, gehRichtung, territorium);
	}
	
	@Override
	public Integer call() throws Exception {
		boolean istKachel = true;
		
		do {
			this.bearbeiteKachel();
			istKachel = this.bewege();
		} while (istKachel);
		
		return this.gesamtZahlKoerner;
	}
	
	private int gesamtZahlKoerner;
	
	private void bearbeiteKachel() {
		// Körnerzahl der aktuellen Kachel zur Gesamtsumme addieren
		int koernerDieserKachel = this.gibTerritorium().gibKoerner(this.gibPosition());
		this.gesamtZahlKoerner += koernerDieserKachel;
	}
}
