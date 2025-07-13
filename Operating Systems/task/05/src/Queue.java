import java.util.Random;

class Queue {
	private final int[] buffer;
	private int in;
	private int out;
	private int count;
	private final String name;
	
	Queue(int groesse, String name) {
		this.buffer = new int[groesse];
		this.name = name;
	}
	
	synchronized int get() throws InterruptedException {
		while (this.count == 0) {
			this.wait();
		}
		int zahl = this.buffer[this.out];
		this.out = (this.out + 1) % this.buffer.length;
		this.count--;
		this.notifyAll();
		return zahl;
	}
	
	synchronized void put(int zahl) throws InterruptedException {
		while (this.count == this.buffer.length) {
			this.wait();
		}
		this.buffer[this.in] = zahl;
		this.in = (this.in + 1) % this.buffer.length;
		this.count++;
		this.notifyAll();
	}
	
	synchronized boolean isEmpty() {
		return this.count == 0;
	}
	
	synchronized void print() {
		System.out.print(this.name + " contains: ");
		for (int i = 0; i < this.count; i++) {
			int index = (this.out + i) % this.buffer.length;
			System.out.print(this.buffer[index] + " ");
		}
		System.out.println();
	}
}

class Einspeiser extends Thread {
	private final Queue zielQueue;
	private final int kennWert;
	private final Random random = new Random();
	
	Einspeiser(Queue zielQueue, int kennWert) {
		this.zielQueue = zielQueue;
		this.kennWert = kennWert;
	}
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < 5; i++) {
				this.zielQueue.put(this.kennWert);
				System.out.println("Einspeiser " + this.kennWert + " produced: " + this.kennWert);
				Thread.sleep(this.random.nextInt(2001)); // 0-2000ms
			}
		} catch (InterruptedException e) {
			System.out.println("Einspeiser " + this.kennWert + " interrupted");
		}
	}
}

class Merge extends Thread {
	private final Queue input;
	private final Queue output;
	
	Merge(Queue input, Queue output) {
		this.input = input;
		this.output = output;
	}
	
	@Override
	public void run() {
		try {
			while (!this.isInterrupted()) {
				int zahl = this.input.get();
				this.output.put(zahl);
				System.out.println("Merge transferred: " + zahl);
			}
		} catch (InterruptedException e) {
			// After interrupt, empty the input queue
			try {
				while (!this.input.isEmpty()) {
					int zahl = this.input.get();
					this.output.put(zahl);
					System.out.println("Merge transferred (cleanup): " + zahl);
				}
			} catch (InterruptedException e2) {
				System.out.println("Merge interrupted during cleanup");
			}
		}
		System.out.println("Merge thread ending");
	}
	
	public static void main(String[] args) throws InterruptedException {
		// Create queues
		Queue inputQueue1 = new Queue(10, "InputQueue1");
		Queue inputQueue2 = new Queue(10, "InputQueue2");
		Queue outputQueue = new Queue(20, "OutputQueue");
		
		// Create threads
		Einspeiser einspeiser1 = new Einspeiser(inputQueue1, 1);
		Einspeiser einspeiser2 = new Einspeiser(inputQueue2, 0);
		Merge merge1 = new Merge(inputQueue1, outputQueue);
		Merge merge2 = new Merge(inputQueue2, outputQueue);
		
		// Start all threads
		einspeiser1.start();
		einspeiser2.start();
		merge1.start();
		merge2.start();
		
		// Wait for Einspeiser to complete
		einspeiser1.join();
		einspeiser2.join();
		
		// Give some time for merging
		sleep(1000);
		
		// Interrupt merge threads
		merge1.interrupt();
		merge2.interrupt();
		
		// Wait for merge threads to complete
		merge1.join();
		merge2.join();
		
		// Print final output
		System.out.println("\nFinal output:");
		outputQueue.print();
		
		System.out.println("Program finished");
	}
}
