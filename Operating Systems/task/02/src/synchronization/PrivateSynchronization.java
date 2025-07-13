package synchronization;

/**
 *
 * @author Luis-St
 *
 */

public class PrivateSynchronization {
	
	private int value;
	private final Object o = new Object();
	
	public PrivateSynchronization(int value) {
		this.value = value;
	}
	
	public void swap(PrivateSynchronization object) {
		synchronized (this.o) {
			int h = this.value;
			this.value = object.value;
			object.value = h;
		}
	}
}
