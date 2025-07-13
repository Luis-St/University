package deadlock;

/**
 *
 * @author Luis-St
 *
 */

class MemoryCell {
	
	private int value;
	
	public synchronized void setValue(int w) {
		this.value = w;
	}
	
	public synchronized int getValue() {
		return this.value;
	}
	
	public synchronized void swapValues(MemoryCell cell) {
		// Interruption to increase the chance of a deadlock
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		int value = cell.getValue();
		cell.setValue(this.value);
		this.setValue(value);
	}
}
