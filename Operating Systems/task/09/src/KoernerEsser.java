import java.util.concurrent.*;

class KoernerEsser extends SucherImpl {
	
	private CyclicBarrier barriere;
	private int zahlEsser;
	
	KoernerEsser(int[] neuePosition, Richtung richtung, Territorium territorium,
				 int zahlEsser, CyclicBarrier barriere) {
		super(neuePosition, richtung, territorium);
		this.barriere = barriere;
		this.zahlEsser = zahlEsser;
	}
	
	private static final int[][] zahlKoerner = {
		{ 1, 0, 2, 0, 3, 0, 4, 0, 5, 0 },
		{ 6, 0, 7, 0, 8, 0, 9, 0, 10, 0 },
		{ 1, 0, 2, 0, 3, 0, 4, 0, 5, 0 },
		{ 6, 0, 7, 0, 8, 0, 9, 0, 10, 20 }
	};
	
	public static void main(String[] args) throws InterruptedException {
		Territorium territorium = new Territorium(zahlKoerner);
		int zahlEsser = 3;
		
		// Thread Pool mit mindestens so vielen Threads wie Esser
		// (muss >= zahlEsser sein, damit alle Esser gleichzeitig an der Barriere warten können)
		ExecutorService dienstleister = Executors.newFixedThreadPool(zahlEsser);
		
		// CyclicBarrier für alle Esser (jeder bekommt dieselbe)
		// Wert muss zahlEsser sein, damit alle Esser synchronisiert werden
		CyclicBarrier barriere = new CyclicBarrier(zahlEsser);
		
		for (int i = 0; i < zahlEsser; i++) {
			KoernerEsser esser = new KoernerEsser(
				new int[] { 0, 0 }, Richtung.RECHTS, territorium, zahlEsser, barriere);
			
			// Esser dem ThreadPool zur Ausführung übergeben
			dienstleister.execute(esser);
		}
		
		dienstleister.shutdown();
		
		// Warten bis der letzte Auftrag des Thread Pool abgearbeitet ist
		dienstleister.awaitTermination(10, TimeUnit.SECONDS);
		
		territorium.gibKoernerAus();
	}
	
	@Override
	public void run() {
		int zeile = 0;
		boolean istKachel = true;
		do {
			
			do {
				this.bearbeiteKachel();
				istKachel = this.bewege();
			} while (istKachel);
			
			zeile++;
			istKachel = this.setzePosition(new int[] { zeile, 0 });
		} while (istKachel);
	}
	
	private void bearbeiteKachel() {
		try {
			// CyclicBarrier verwenden damit jeder Esser die ursprüngliche Zahl der Körner sieht
			this.barriere.await();
			
			// Körnerzahl der aktuellen Kachel holen
			int aktuelleKoernerZahl = this.gibTerritorium().gibKoerner(this.gibPosition());
			
			// Wenn mehr Körner vorhanden sind als Esser, um eins verringern
			if (aktuelleKoernerZahl > this.zahlEsser) {
				this.gibTerritorium().dekrementiereKoerner(this.gibPosition());
			}
		} catch (InterruptedException | BrokenBarrierException e) {
			Thread.currentThread().interrupt();
		}
	}
}
