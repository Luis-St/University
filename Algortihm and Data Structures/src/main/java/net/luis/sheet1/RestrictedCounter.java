package net.luis.sheet1;

import net.luis.util.Task;

/**
 *
 * @author Luis-st
 *
 */

@Task("5")
public class RestrictedCounter extends Counter {
	
	private final int maxCapacity;
	
	public RestrictedCounter(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	
	@Override
	public void increment() {
		if (this.get() < this.maxCapacity) {
			super.increment();
		} else {
			throw new IllegalStateException("Counter is at max capacity");
		}
	}
	
	public int freeCapacity() {
		return this.maxCapacity - this.get();
	}
	
}
