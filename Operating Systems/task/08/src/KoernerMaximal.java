import java.util.concurrent.CountDownLatch;

class KoernerMaximal extends SucherImpl {
	
	private int maxKoernerZahl;
	private final CountDownLatch latch; // Attribut zur Synchronisation
	
	KoernerMaximal(int[] aktuellePosition, Richtung richtung,
				   Territorium territorium, CountDownLatch latch) {
		super(aktuellePosition, richtung, territorium);
		
		this.maxKoernerZahl = 0;
		this.latch = latch;
	}
	
	private int gibMaxKoernerZahl() {
		return this.maxKoernerZahl;
	}
	
	@Override
	public void run() {
		boolean istKachel = true;
		
		do {
			this.bearbeiteKachel();
			istKachel = this.bewege();
		} while (istKachel);
		
		// Auftrag erledigt - CountDownLatch dekrementieren
		this.latch.countDown();
	}
	
	private void bearbeiteKachel() {
		// Aktuelle Körnerzahl mit Maximum vergleichen und ggf. aktualisieren
		int aktuelleKoernerZahl = this.gibTerritorium().gibKoerner(this.gibPosition());
		if (aktuelleKoernerZahl > this.maxKoernerZahl) {
			this.maxKoernerZahl = aktuelleKoernerZahl;
		}
	}
	
	// Konfiguration Territorium
	private static final int[][] koernerZahlen = {
		{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 2, 0, 0, 0, 6, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 3, 0, 6, 2, 0, 0, 6, 7, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0 },
	};
	
	public static void main(String[] args) throws InterruptedException {
		Territorium territorium = new Territorium(koernerZahlen);
		
		CountDownLatch zaehler = new CountDownLatch(2);
		int[] aktuellePosition = { 3, 4 };
		
		// Zwei Sucher erzeugen - einen nach links, einen nach rechts
		KoernerMaximal sucherLinks = new KoernerMaximal(aktuellePosition, Richtung.LINKS, territorium, zaehler);
		KoernerMaximal sucherRechts = new KoernerMaximal(aktuellePosition, Richtung.RECHTS, territorium, zaehler);
		
		// Sucher starten
		sucherLinks.start();
		sucherRechts.start();
		
		// Mit CountDownLatch auf Beendigung beider Threads warten
		zaehler.await();
		
		int maxKoernerZahl = Math.max(sucherLinks.gibMaxKoernerZahl(), sucherRechts.gibMaxKoernerZahl());
		
		System.out.println("Die maximale Anzahl an Koernern" + " auf Kacheln in\n"
			+ "der Reihe, in der ich stehe, betraegt: " + maxKoernerZahl);
	}
}
