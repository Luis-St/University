package net.luis.sheet2;

import net.luis.util.Task;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-St
 *
 */

@Task("1")
public record Fraction(int numerator, int denominator) implements Comparable<Fraction> {
	
	@Task("3")
	private static int faculty(int n) {
		if (n == 0) {
			return 1;
		}
		return n * faculty(n - 1);
	}
	
	@Task("3")
	public static @NotNull Fraction e(int n) {
		Fraction fraction = new Fraction(1, 1);
		for (int i = 1; i <= n; i++) {
			fraction = fraction.add(new Fraction(1, faculty(i)));
		}
		return fraction;
	}
	
	public @NotNull Fraction add(@NotNull Fraction fraction) {
		int numerator = this.numerator * fraction.denominator + fraction.numerator * this.denominator;
		int denominator = this.denominator * fraction.denominator;
		return new Fraction(numerator, denominator);
	}
	
	public @NotNull Fraction subtract(@NotNull Fraction fraction) {
		int numerator = this.numerator * fraction.denominator - fraction.numerator * this.denominator;
		int denominator = this.denominator * fraction.denominator;
		return new Fraction(numerator, denominator);
	}
	
	public @NotNull Fraction multiply(@NotNull Fraction fraction) {
		int numerator = this.numerator * fraction.numerator;
		int denominator = this.denominator * fraction.denominator;
		return new Fraction(numerator, denominator);
	}
	
	public @NotNull Fraction divide(@NotNull Fraction fraction) {
		int numerator = this.numerator * fraction.denominator;
		int denominator = this.denominator * fraction.numerator;
		return new Fraction(numerator, denominator);
	}
	
	private int ggt(int numerator, int denominator) {
		if (denominator == 0) {
			return numerator;
		}
		return ggt(denominator, numerator % denominator);
	}
	
	public @NotNull Fraction simplify() {
		int ggt = this.ggt(this.numerator, this.denominator);
		return new Fraction(this.numerator / ggt, this.denominator / ggt);
	}
	
	@Override
	@Task("Sheet 5 - Task 3")
	public int compareTo(@NotNull Fraction fraction) {
		return Integer.compare(this.numerator * fraction.denominator, fraction.numerator * this.denominator);
	}
	
	@Override
	public String toString() {
		return this.numerator + "/" + this.denominator;
	}
}
