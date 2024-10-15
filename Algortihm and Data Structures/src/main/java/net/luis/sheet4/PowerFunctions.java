package net.luis.sheet4;

import net.luis.sheet1.Counter;
import net.luis.util.Task;

/**
 *
 * @author Luis-St
 *
 */

@Task("1")
public class PowerFunctions {
	
	@Task("2") private static final Counter powerCounter = new Counter();
	@Task("2") private static final Counter fastPowerCounter = new Counter();
	
	public static double power(double value, int n) {
		if (n == 0) {
			return 1;
		}
		double result = value;
		for (int i = 1; i < n; i++) {
			result *= value;
			powerCounter.increment();
		}
		return result;
	}
	
	public static double fastPower(double value, int n) {
		double result = 1.0;
		while (n > 0) {
			if (n % 2 == 1) {
				result *= value;
				fastPowerCounter.increment();
			}
			value *= value;
			fastPowerCounter.increment();
			n /= 2;
		}
		return result;
	}
	
	public static void resetCounters() {
		powerCounter.reset();
		fastPowerCounter.reset();
	}
	
	public static int getPowerCount() {
		return powerCounter.get();
	}
	
	public static int getFastPowerCount() {
		return fastPowerCounter.get();
	}
	
}
