package synchronization;

/**
 *
 * @author Luis-St
 *
 */

public class DualSynchronization {
	
	private int value;
	
	public DualSynchronization(int value) {
		this.value = value;
	}
	
	public synchronized void swap(DualSynchronization a) {
		synchronized (a) {
			int h = this.value;
			this.value = a.value;
			a.value = h;
		}
	}
}
