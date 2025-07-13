package synchronization;

/**
 *
 * @author Luis-St
 *
 */

public class StaticSynchronization {
	
	private static final Object o = new Object();
	
	private int value;
	
	public StaticSynchronization(int value) {
		this.value = value;
	}
	
	public void swap(StaticSynchronization a) {
		synchronized (o) {
			int h = this.value;
			this.value = a.value;
			a.value = h;
		}
	}
}
