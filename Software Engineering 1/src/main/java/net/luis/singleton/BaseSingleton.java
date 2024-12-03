package net.luis.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis-St
 *
 */

public class BaseSingleton {
	
	private static final BaseSingleton INSTANCE = new BaseSingleton();
	
	private final List<String> data = new ArrayList<>();
	
	private BaseSingleton() {}
	
	public static BaseSingleton getInstance() {
		return INSTANCE;
	}
	
	public void addData(String value) {
		this.data.add(value);
	}
	
	public void removeData(String value) {
		this.data.remove(value);
	}
	
	public void printData() {
		this.data.forEach(System.out::println);
	}
}
