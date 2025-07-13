package deadlock;

/**
 *
 * @author Luis-St
 *
 */

class SwapThread extends Thread {
	
	private final MemoryCell source;
	private final MemoryCell target;
	
	SwapThread(MemoryCell source, MemoryCell target) {
		this.source = source;
		this.target = target;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " tries to swap values...");
		this.source.swapValues(this.target);
		System.out.println(Thread.currentThread().getName() + " finished swapping values.");
	}
}
