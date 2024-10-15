package net.luis.sheet1;

import net.luis.util.Task;

@Task("1")
public class Counter {
	
	private int x = 0;
	@Task("4") private boolean saved = false;
	@Task("4") private int savedX = 0;
	
	public void increment() {
		this.x++;
	}
	
	@Task("2")
	public void decrement() {
		this.x--;
	}
	
	public int get() {
		return this.x;
	}
	
	public void reset() {
		this.x = 0;
	}
	
	@Task("4")
	public void save() {
		this.savedX = this.x;
		this.saved = true;
	}
	
	@Task("4")
	public void restore() {
		if (this.saved) {
			this.x = this.savedX;
			this.saved = false;
		} else {
			throw new IllegalStateException("No value saved");
		}
	}
	
}
