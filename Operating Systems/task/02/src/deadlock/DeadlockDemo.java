package deadlock;

/**
 *
 * @author Luis-St
 *
 */

public class DeadlockDemo {
	
	public static void main(String[] args) {
		MemoryCell cell1 = new MemoryCell();
		MemoryCell cell2 = new MemoryCell();
		
		SwapThread thread1 = new SwapThread(cell1, cell2);
		SwapThread thread2 = new SwapThread(cell2, cell1);
		
		thread1.setName("Thread-1");
		thread2.setName("Thread-2");
		
		System.out.println("Starte Threads...");
		thread1.start();
		thread2.start();
		
		// Wait for a while and then check if a deadlock has occurred
		try {
			Thread.sleep(5000);  // 5 Sekunden warten
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		// Check, if the threads are still alive (in deadlock)
		if (thread1.isAlive() && thread2.isAlive()) {
			System.out.println("DEADLOCK DETECTED!");
			System.out.println("Thread-1 and Thread-2 are both alive.");
			System.out.println("Thread-1 is holding the lock on cell1 and waiting for cell2.");
			System.out.println("Thread-2 is holding the lock on cell2 and waiting for cell1.");
		} else {
			System.out.println("No deadlock detected.");
		}
	}
}
