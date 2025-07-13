import java.io.Serial;
import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
	
	static class Speicher extends LinkedList<String> {
		
		@Serial private static final long serialVersionUID = -8897355982032379105L;
		private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
		private int lesendeThreads;
		private int schreibendeThreads;
		
		void writeLine(String zeile) {
			// Für Teil II: ReadWriteLock verwenden
			// rwLock.writeLock().lock();
			try {
				this.schreiberRein();
				this.add(zeile);
				Thread.yield(); // Für Teil I
			} finally {
				this.schreiberRaus();
				// rwLock.writeLock().unlock();
			}
		}
		
		String readLine(int zeilenNummer) {
			// Für Teil II: ReadWriteLock verwenden
			// rwLock.readLock().lock();
			try {
				this.leserRein();
				if (zeilenNummer >= 0 && zeilenNummer < this.size()) {
					String result = this.get(zeilenNummer);
					Thread.yield(); // Für Teil I
					return result;
				}
				return null;
			} finally {
				this.leserRaus();
				// rwLock.readLock().unlock();
			}
		}
		
		synchronized void schreiberRein() {
			this.schreibendeThreads++;
			System.out.println("Schreiber rein - Lesende: " + this.lesendeThreads + ", Schreibende: " + this.schreibendeThreads);
		}
		
		synchronized void schreiberRaus() {
			this.schreibendeThreads--;
			System.out.println("Schreiber raus - Lesende: " + this.lesendeThreads + ", Schreibende: " + this.schreibendeThreads);
		}
		
		synchronized void leserRein() {
			this.lesendeThreads++;
			System.out.println("Leser rein - Lesende: " + this.lesendeThreads + ", Schreibende: " + this.schreibendeThreads);
		}
		
		synchronized void leserRaus() {
			this.lesendeThreads--;
			System.out.println("Leser raus - Lesende: " + this.lesendeThreads + ", Schreibende: " + this.schreibendeThreads);
		}
	}
	
	static class Leser extends Thread {
		
		private final Speicher speicher;
		
		Leser(Speicher speicher) {
			this.speicher = speicher;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < this.speicher.size(); i++) {
				String zeile = this.speicher.readLine(i);
				if (zeile != null) {
					System.out.println(Thread.currentThread().getName() +
						" liest: " + zeile);
					Thread.yield(); // Für Teil I
				}
			}
		}
	}
	
	static class Schreiber extends Thread {
		
		private final Speicher speicher;
		private final String[] text;
		private final int zahlWiederholungen;
		
		Schreiber(Speicher speicher, String[] text, int zahlWiederholungen) {
			this.speicher = speicher;
			this.text = text;
			this.zahlWiederholungen = zahlWiederholungen;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < this.zahlWiederholungen; i++) {
				for (String zeile : this.text) {
					this.speicher.writeLine(zeile);
					System.out.println(Thread.currentThread().getName() +
						" schreibt: " + zeile);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Speicher speicher = new Speicher();
		
		String[] text1 = { "text 1: Zeile 1", "text 1: Zeile 2" };
		String[] text2 = { "text 2: Zeile 1", "text 2: Zeile 2" };
		
		// Schreiber erstellen
		Schreiber schreiber1 = new Schreiber(speicher, text1, 2);
		Schreiber schreiber2 = new Schreiber(speicher, text2, 2);
		
		// Threads starten
		schreiber1.start();
		schreiber2.start();
		
		// Kurz warten, damit Schreiber starten können
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Leser erstellen und starten
		Leser leser1 = new Leser(speicher);
		Leser leser2 = new Leser(speicher);
		
		leser1.start();
		leser2.start();
		
		// Auf alle Threads warten
		try {
			schreiber1.join();
			schreiber2.join();
			leser1.join();
			leser2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
