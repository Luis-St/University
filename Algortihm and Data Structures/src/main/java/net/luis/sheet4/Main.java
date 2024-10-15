package net.luis.sheet4;

import net.luis.sheet4.PowerFunctions;

import java.util.Random;

import static net.luis.util.ConsoleColors.*;

/**
 *
 * @author Luis-St
 *
 */

public class Main {
	
	private static final Random RNG = new Random();
	
	public static void main(String[] args) {
		System.out.println(BRIGHT_GREEN + "Sheet 4" + RESET);
		int totalExponent = 0;
		for (int i = 0; i < 1000; i++) {
			int value = RNG.nextBoolean() ? 1 : 0;
			int n = RNG.nextInt(1000);
			totalExponent += n;
			PowerFunctions.power(value, n);
			PowerFunctions.fastPower(value, n);
		}
		System.out.println("Average exponent: " + totalExponent / 1000.0);
		System.out.println("Average power operations: " + PowerFunctions.getPowerCount() / 1000.0);
		System.out.println("Average fast Power operations: " + PowerFunctions.getFastPowerCount() / 1000.0);
	}
	
}
