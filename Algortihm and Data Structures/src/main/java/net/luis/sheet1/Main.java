package net.luis.sheet1;

import net.luis.sheet1.Counter;
import net.luis.sheet1.RestrictedCounter;

import static net.luis.util.ConsoleColors.*;

/**
 *
 * @author Luis-st
 *
 */

public class Main {
	
	public static void main(String[] args) {
		System.out.println(BRIGHT_GREEN + "Sheet 1" + RESET);
		System.out.println();
		testTask3();
		System.out.println();
		testTask4();
		System.out.println();
		testTask5();
	}
	
	private static void testTask3() {
		System.out.println(BRIGHT_GREEN + "--- Task 3 ---" + RESET);
		Counter counter = new Counter();
		counter.increment();
		counter.increment();
		System.out.println("Counter is at " + counter.get());
		counter.increment();
		counter.increment();
		System.out.println("Counter is at " + counter.get());
		counter.decrement();
		System.out.println("Counter is at " + counter.get());
		counter.decrement();
		counter.decrement();
		System.out.println("Counter is at " + counter.get());
		counter.increment();
		counter.reset();
		System.out.println("Counter is at " + counter.get());
	}
	
	private static void testTask4() {
		System.out.println(BRIGHT_GREEN + "--- Task 4 ---" + RESET);
		Counter counter = new Counter();
		counter.increment();
		System.out.println("Counter is at " + counter.get());
		counter.save();
		counter.increment();
		System.out.println("Counter is at " + counter.get());
		counter.restore();
		System.out.println("Counter is at " + counter.get());
		try {
			counter.restore();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}
	
	private static void testTask5() {
		System.out.println(BRIGHT_GREEN + "--- Task 5 ---" + RESET);
		RestrictedCounter counter = new RestrictedCounter(3);
		counter.increment();
		counter.increment();
		System.out.println("Counter is at " + counter.get());
		System.out.println("Counter has " + counter.freeCapacity() + " free capacity");
		counter.increment();
		System.out.println("Counter is at " + counter.get());
		System.out.println("Counter has " + counter.freeCapacity() + " free capacity");
		try {
			counter.increment();
		} catch (IllegalStateException e) {
			System.out.println("Exception thrown");
		}
	}
	
}
