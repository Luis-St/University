import java.io.*;

public class PipeTest {
	private static final int PIPE_SIZE = 1024; // Default pipe buffer size
	private static final int INHALT_GROESSE = 1024; // Can be changed for different tests
	
	static class ThreadA extends Thread {
		private final PipedOutputStream out;
		private final int size;
		private final boolean closeAfterWrite;
		
		ThreadA(PipedOutputStream out, int size, boolean closeAfterWrite) {
			this.out = out;
			this.size = size;
			this.closeAfterWrite = closeAfterWrite;
		}
		
		@Override
		public void run() {
			try {
				byte[] data = new byte[this.size];
				for (int i = 0; i < this.size; i++) {
					data[i] = (byte)(i % 256);
				}
				
				System.out.println("Thread A: Writing " + this.size + " bytes");
				this.out.write(data, 0, this.size);
				System.out.println("Thread A: Write completed");
				
				if (this.closeAfterWrite) {
					this.out.close();
					System.out.println("Thread A: Stream closed");
				}
			} catch (IOException e) {
				System.out.println("Thread A Exception: " + e.getMessage());
			}
		}
	}
	
	static class ThreadB extends Thread {
		private final PipedInputStream in;
		private final boolean singleRead;
		
		ThreadB(PipedInputStream in, boolean singleRead) {
			this.in = in;
			this.singleRead = singleRead;
		}
		
		@Override
		public void run() {
			try {
				if (this.singleRead) {
					// VI) Only one read
					int data = this.in.read();
					System.out.println("Thread B: Read single byte: " + data);
				} else {
					// Normal operation: read in loop
					int count = 0;
					int data;
					while ((data = this.in.read()) != -1) {
						System.out.print(data + " ");
						count++;
						if (count % 20 == 0) System.out.println();
						Thread.sleep(1);
					}
					System.out.println("\nThread B: Read " + count + " bytes total");
				}
			} catch (IOException e) {
				System.out.println("Thread B IOException: " + e.getMessage());
			} catch (InterruptedException e) {
				System.out.println("Thread B interrupted");
			}
		}
	}
	
	public static void testScenario(String scenario, int size, boolean startThreadB, boolean closeStream, boolean singleRead) {
		System.out.println("\n=== Test " + scenario + " ===");
		System.out.println("INHALT_GROESSE = " + size);
		System.out.println("Thread B started: " + startThreadB);
		System.out.println("Close stream: " + closeStream);
		System.out.println("Single read: " + singleRead);
		
		try {
			PipedOutputStream out = new PipedOutputStream();
			PipedInputStream in = new PipedInputStream(out);
			
			ThreadA threadA = new ThreadA(out, size, closeStream);
			ThreadB threadB = new ThreadB(in, singleRead);
			
			threadA.start();
			if (startThreadB) {
				threadB.start();
			}
			
			// Wait for threads to complete (with timeout)
			threadA.join(5000);
			if (startThreadB) {
				threadB.join(5000);
			}
			
			if (threadA.isAlive()) {
				System.out.println("Thread A still running - DEADLOCK!");
			}
			if (startThreadB && threadB.isAlive()) {
				System.out.println("Thread B still running");
			}
			
			System.out.println("Test completed successfully");
			
		} catch (Exception e) {
			System.out.println("Exception in main: " + e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		// I) Normal operation
		testScenario("I", 1024, true, false, false);
		
		// II) With close()
		testScenario("II", 1024, true, true, false);
		
		// III) Size <= 1024, no Thread B
		testScenario("III", 1024, false, false, false);
		
		// IV) Size > 1024, no Thread B
		testScenario("IV", 2048, false, false, false);
		
		// V) Size > 1024, Thread B working
		testScenario("V", 2048, true, false, false);
		
		// VI) Size >= 1026, Thread B single read
		testScenario("VI", 1026, true, false, true);
	}
}
