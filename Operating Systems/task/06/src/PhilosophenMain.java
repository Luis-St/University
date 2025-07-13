import java.util.concurrent.Semaphore;
import java.util.Random;

// Teil I: Philosophen-Problem OHNE Semaphorengruppe (mit Verklemmung)
class PhilosophOhneGruppe extends Thread {
	private final int id;
	private final Semaphore linkeGabel;
	private final Semaphore rechteGabel;
	private final Random random = new Random();
	private static volatile boolean startSignal;
	
	public PhilosophOhneGruppe(int id, Semaphore linkeGabel, Semaphore rechteGabel) {
		this.id = id;
		this.linkeGabel = linkeGabel;
		this.rechteGabel = rechteGabel;
	}
	
	@Override
	public void run() {
		try {
			// Warte auf Startsignal, damit alle gleichzeitig anfangen
			while (!startSignal) {
				Thread.sleep(10);
			}
			
			while (true) {
				this.denken();
				// Alle Philosophen nehmen zuerst linke, dann rechte Gabel
				// Dies kann zu Verklemmung führen!
				System.out.println("Philosoph " + this.id + " will essen");
				
				this.linkeGabel.acquire();
				System.out.println("Philosoph " + this.id + " hat linke Gabel");
				
				// Längere Verzögerung für höhere Verklemmungswahrscheinlichkeit
				Thread.sleep(500);
				
				this.rechteGabel.acquire();
				System.out.println("Philosoph " + this.id + " hat rechte Gabel");
				
				this.essen();
				
				this.rechteGabel.release();
				System.out.println("Philosoph " + this.id + " legt rechte Gabel ab");
				
				this.linkeGabel.release();
				System.out.println("Philosoph " + this.id + " legt linke Gabel ab");
			}
		} catch (InterruptedException e) {
			System.out.println("Philosoph " + this.id + " wurde unterbrochen");
		}
	}
	
	private void denken() throws InterruptedException {
		System.out.println("Philosoph " + this.id + " denkt");
		Thread.sleep(this.random.nextInt(200)); // Kürzere Denkzeit
	}
	
	private void essen() throws InterruptedException {
		System.out.println("Philosoph " + this.id + " isst");
		Thread.sleep(this.random.nextInt(200));
	}
	
	public static void setStartSignal() {
		startSignal = true;
	}
}

// Teil II: Philosophen-Problem MIT Semaphorengruppe (verklemmungsfrei)
class PhilosophMitGruppe extends Thread {
	private final int id;
	private final SemaphoreGroup semGroup;
	private final int linkeGabelIndex;
	private final int rechteGabelIndex;
	private final Random random = new Random();
	
	public PhilosophMitGruppe(int id, SemaphoreGroup semGroup) {
		this.id = id;
		this.semGroup = semGroup;
		this.linkeGabelIndex = id;
		this.rechteGabelIndex = (id + 1) % 5;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				this.denken();
				
				// Atomares Aufnehmen beider Gabeln
				int[] deltas = new int[5];
				deltas[this.linkeGabelIndex] = -1;
				deltas[this.rechteGabelIndex] = -1;
				
				System.out.println("Philosoph " + this.id + " will essen");
				this.semGroup.changeValues(deltas);
				System.out.println("Philosoph " + this.id + " hat beide Gabeln");
				
				this.essen();
				
				// Atomares Ablegen beider Gabeln
				deltas[this.linkeGabelIndex] = 1;
				deltas[this.rechteGabelIndex] = 1;
				this.semGroup.changeValues(deltas);
				System.out.println("Philosoph " + this.id + " legt beide Gabeln ab");
			}
		} catch (InterruptedException e) {
			System.out.println("Philosoph " + this.id + " wurde unterbrochen");
		}
	}
	
	private void denken() throws InterruptedException {
		System.out.println("Philosoph " + this.id + " denkt");
		Thread.sleep(this.random.nextInt(1000));
	}
	
	private void essen() throws InterruptedException {
		System.out.println("Philosoph " + this.id + " isst");
		Thread.sleep(this.random.nextInt(1000));
	}
}

// Main-Klasse für Philosophen-Problem
public class PhilosophenMain {
	public static void main(String[] args) {
		//System.out.println("=== Teil I: Ohne Semaphorengruppe (mit Verklemmung) ===");
		//mitVerklemmung();
		
		// Auskommentieren für Teil II
		System.out.println("\n=== Teil II: Mit Semaphorengruppe (verklemmungsfrei) ===");
		ohneVerklemmung();
	}
	
	private static void mitVerklemmung() {
		// 5 Gabeln als Semaphore
		Semaphore[] gabeln = new Semaphore[5];
		for (int i = 0; i < 5; i++) {
			gabeln[i] = new Semaphore(1);
		}
		
		// 5 Philosophen erstellen und starten
		PhilosophOhneGruppe[] philosophen = new PhilosophOhneGruppe[5];
		for (int i = 0; i < 5; i++) {
			philosophen[i] = new PhilosophOhneGruppe(i, gabeln[i], gabeln[(i + 1) % 5]);
			philosophen[i].start();
		}
		
		// Kurz warten, dann alle gleichzeitig starten
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("=== STARTE ALLE PHILOSOPHEN GLEICHZEITIG ===");
		PhilosophOhneGruppe.setStartSignal();
	}
	
	private static void ohneVerklemmung() {
		// Semaphorengruppe mit 5 Gabeln (initial alle verfügbar)
		SemaphoreGroup semGroup = new SemaphoreGroup(5);
		int[] initialValues = {1, 1, 1, 1, 1};
		semGroup.changeValues(initialValues);
		
		// 5 Philosophen erstellen und starten
		PhilosophMitGruppe[] philosophen = new PhilosophMitGruppe[5];
		for (int i = 0; i < 5; i++) {
			philosophen[i] = new PhilosophMitGruppe(i, semGroup);
			philosophen[i].start();
		}
	}
}

// SemaphoreGroup-Klasse (aus der Aufgabenstellung)
class SemaphoreGroup {
	
	private int[] values;
	
	public SemaphoreGroup(int numberOfMembers) {
		if (numberOfMembers <= 0) {
			return;
		}
		this.values = new int[numberOfMembers];
	}
	
	public int getNumberOfMembers() {
		return this.values.length;
	}
	
	public synchronized void changeValues(int[] deltas) {
		if (deltas.length != this.values.length) {
			return;
		}
		while (!this.canChange(deltas)) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException aufgetreten");
			}
		}
		this.doChange(deltas);
		this.notifyAll();
	}
	
	private Boolean canChange(int[] deltas) {
		for (int i = 0; i < this.values.length; i++) {
			if (this.values[i] + deltas[i] < 0) {
				return false;
			}
		}
		return true;
	}
	
	private void doChange(int[] deltas) {
		for (int i = 0; i < this.values.length; i++) {
			this.values[i] = this.values[i] + deltas[i];
		}
	}
}
