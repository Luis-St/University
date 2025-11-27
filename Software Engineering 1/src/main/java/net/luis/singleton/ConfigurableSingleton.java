package net.luis.singleton;

import java.util.*;

/**
 *
 * @author Luis-St
 *
 */

public class ConfigurableSingleton {
	
	private static final Map<String, ConfigurableSingleton> INSTANCES = new WeakHashMap<>();
	private static int maxInstances = 1;
	
	private final List<String> data = new ArrayList<>();
	
	private ConfigurableSingleton() {}
	
	public static int getMaxInstances() {
		return maxInstances;
	}
	
	public static void setMaxInstances(int maxInstances) {
		ConfigurableSingleton.maxInstances = Math.max(ConfigurableSingleton.maxInstances, maxInstances); // Don't allow setting a value lower than the current one
	}
	
	public static ConfigurableSingleton getInstance(String key) {
		if (!INSTANCES.containsKey(key)) {
			if (INSTANCES.size() < maxInstances) {
				INSTANCES.put(key, new ConfigurableSingleton());
			} else {
				throw new IllegalStateException("Max number of instances reached");
			}
		}
		return INSTANCES.get(key);
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
