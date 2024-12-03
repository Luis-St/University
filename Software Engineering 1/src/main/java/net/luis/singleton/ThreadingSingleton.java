package net.luis.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis-St
 *
 */

public class ThreadingSingleton {
	
	private static volatile ThreadingSingleton instance;
	
	private final List<String> data = new ArrayList<>();
	
	private ThreadingSingleton() {}
	
	public static ThreadingSingleton getInstance() {
		if (instance == null) {
			synchronized (ThreadingSingleton.class) { // double-checked locking
				if (instance == null) {
					instance = new ThreadingSingleton();
				}
			}
		}
		return instance;
	}
	
	public synchronized void addData(String value) {
		this.data.add(value);
	}
	
	public synchronized void removeData(String value) {
		this.data.remove(value);
	}
	
	public synchronized void printData() {
		this.data.forEach(System.out::println);
	}
}
