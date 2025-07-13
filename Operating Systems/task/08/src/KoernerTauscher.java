import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class KoernerTauscher extends SucherImpl {
	
	private final Exchanger<Integer> austauscher;
	
	KoernerTauscher(int[] neuePosition, Richtung richtung, Territorium territorium, Exchanger<Integer> austauscher) {
		super(neuePosition, richtung, territorium);
		this.austauscher = austauscher;
	}
	
	// Konfiguration Territorium
	private static final int[][] koernerZahlen = { { 2, 0, 0, 0, 1, 5, 8 }, { 3, 0, 4, 0, 5, 2, 9 } };
	
	public static void main(String[] args) throws InterruptedException {
		Exchanger<Integer> austauscher = new Exchanger<>();
		Territorium territorium = new Territorium(koernerZahlen);
		
		// Zwei KoernerTauscher Objekte erzeugen
		KoernerTauscher tauscher1 = new KoernerTauscher(new int[]{0, 0}, Richtung.RECHTS, territorium, austauscher);
		KoernerTauscher tauscher2 = new KoernerTauscher(new int[]{1, 0}, Richtung.RECHTS, territorium, austauscher);
		
		// FixedThreadPool mit zwei Threads
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		// Tauscher dem Thread Pool übergeben
		pool.execute(tauscher1);
		pool.execute(tauscher2);
		
		pool.shutdown();
		
		// Warten bis Pool alle Aufgaben fertiggestellt hat
		pool.awaitTermination(10, TimeUnit.SECONDS);
		
		// Territorium ausgeben
		territorium.gibKoernerAus();
	}
	
	@Override
	public void run() {
		boolean istKachel = true;
		
		do {
			this.bearbeiteKachel();
			istKachel = this.bewege();
		} while (istKachel);
	}
	
	private void bearbeiteKachel() {
		try {
			// Aktuelle Körnerzahl holen
			int meineKoernerZahl = this.gibTerritorium().gibKoerner(this.gibPosition());
			
			// Mit anderem Thread austauschen
			int andereKoernerZahl = this.austauscher.exchange(meineKoernerZahl);
			
			// Neue Körnerzahl setzen
			this.gibTerritorium().setzeKoernerZahl(this.gibPosition(), andereKoernerZahl);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
