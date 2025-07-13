package a;

public class Main {
	
	static class Signale {
		boolean ablageposFrei = true;
		boolean produktablageErlaubt;
		boolean produktAbgelegt;
	}
	
	static class Kartonzufuhrprozess extends Thread {
		private final Signale signale;
		
		public Kartonzufuhrprozess(Signale signale) {
			this.signale = signale;
		}
		
		private void fahreKartonVor() {
			System.out.println("Karton wird vorgefahren...");
			try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
		}
		
		@Override
		public void run() {
			while (true) {
				synchronized (this.signale) {
					// Warte bis Ablageposition frei ist
					while (!this.signale.ablageposFrei) {
						try {
							this.signale.wait();
						} catch (InterruptedException ignored) {}
					}
					
					// Fahre Karton vor
					this.fahreKartonVor();
					
					// Setze Signale
					this.signale.ablageposFrei = false;
					this.signale.produktablageErlaubt = true;
					
					// Benachrichtige andere Prozesse
					this.signale.notifyAll();
				}
			}
		}
	}
	
	static class Produktablageprozess extends Thread {
		private final Signale signale;
		
		Produktablageprozess(Signale signale) {
			this.signale = signale;
		}
		
		private void legeProduktAb() {
			System.out.println("Produkt wird abgelegt...");
			try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
		}
		
		@Override
		public void run() {
			while (true) {
				synchronized (this.signale) {
					// Warte bis Produktablage erlaubt ist
					while (!this.signale.produktablageErlaubt) {
						try {
							this.signale.wait();
						} catch (InterruptedException ignored) {}
					}
					
					// Lege Produkt ab
					this.legeProduktAb();
					
					// Setze Signale
					this.signale.produktablageErlaubt = false;
					this.signale.produktAbgelegt = true;
					
					// Benachrichtige andere Prozesse
					this.signale.notifyAll();
				}
			}
		}
	}
	
	static class Abtransportprozess extends Thread {
		private final Signale signale;
		
		Abtransportprozess(Signale signale) {
			this.signale = signale;
		}
		
		private void fahreKartonWeg() {
			System.out.println("Karton wird weggefahren...");
			try { Thread.sleep(800); } catch (InterruptedException ignored) {}
			System.out.println("--- Zyklus abgeschlossen ---\n");
		}
		
		@Override
		public void run() {
			while (true) {
				synchronized (this.signale) {
					// Warte bis Produkt abgelegt wurde
					while (!this.signale.produktAbgelegt) {
						try {
							this.signale.wait();
						} catch (InterruptedException e) {}
					}
					
					// Fahre Karton weg
					this.fahreKartonWeg();
					
					// Setze Signale
					this.signale.produktAbgelegt = false;
					this.signale.ablageposFrei = true;
					
					// Benachrichtige andere Prozesse
					this.signale.notifyAll();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Signale signale = new Signale();
		
		new Kartonzufuhrprozess(signale).start();
		new Produktablageprozess(signale).start();
		new Abtransportprozess(signale).start();
	}
}
