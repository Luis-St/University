package net.luis.sheet6;

import net.luis.sheet5.ComparableMergesort;
import net.luis.sheet6.money.Bill;
import net.luis.sheet6.money.Coin;
import net.luis.sheet6.money.PaymentMethod;
import net.luis.sheet6.money.euro.Euro;
import net.luis.sheet6.money.euro.EuroBill;
import net.luis.sheet6.money.euro.EuroCoin;
import net.luis.sheet6.money.srf.SwissFranc;
import net.luis.sheet6.money.srf.SwissFrancBill;
import net.luis.sheet6.money.srf.SwissFrancCoin;
import net.luis.sheet6.money.usd.Dollar;
import net.luis.sheet6.money.usd.DollarBill;
import net.luis.sheet6.money.usd.DollarCoin;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static net.luis.util.ConsoleColors.*;

/**
 *
 * @author Luis-St
 *
 */

public class Main {
	
	private static final PaymentMethod[] MONEY = {
			DollarCoin.of(0.10), DollarBill.of(20), SwissFrancBill.of(5), SwissFrancCoin.of(0.50), DollarCoin.of(0.50), EuroBill.of(50), DollarBill.of(100), DollarCoin.of(0.2), DollarCoin.of(0.25), EuroCoin.of(0.01), SwissFrancBill.of(200),
			DollarBill.of(100), EuroCoin.of(2), EuroBill.of(5), DollarCoin.of(0.25), SwissFrancCoin.of(0.10), SwissFrancCoin.of(0.01), DollarCoin.of(0.50), DollarBill.of(100), DollarBill.of(100), DollarCoin.of(0.2), DollarCoin.of(0.01),
			SwissFrancBill.of(20), SwissFrancCoin.of(0.50), DollarCoin.of(0.50), EuroBill.of(50), DollarBill.of(5), EuroCoin.of(0.50), EuroCoin.of(0.01), SwissFrancBill.of(10), SwissFrancBill.of(20), SwissFrancCoin.of(0.50),
			SwissFrancCoin.of(0.02)
	};
	
	public static void main(String[] args) {
		System.out.println(BRIGHT_GREEN + "Sheet 6" + RESET);
		System.out.println();
		testTask1();
		System.out.println();
		testTask2();
		System.out.println();
		testTask3();
		System.out.println();
		testTask4();
		System.out.println();
		testTask5();
	}
	
	private static void testTask1() {
		System.out.println(BRIGHT_GREEN + "--- Task 1 ---" + RESET);
		AtomicInteger invalid = new AtomicInteger();
		Arrays.stream(MONEY).filter(m -> {
			if (m.isAmountValid()) {
				return true;
			}
			invalid.getAndIncrement();
			return false;
		}).forEach(System.out::println);
		System.out.println("Invalid: " + invalid);
	}
	
	private static void testTask2() {
		System.out.println(BRIGHT_GREEN + "--- Task 2 ---" + RESET);
		double euro = 0.0;
		double srf = 0.0;
		double usd = 0.0;
		for (PaymentMethod paymentMethod : MONEY) {
			if (!paymentMethod.isAmountValid()) {
				continue;
			}
			if (paymentMethod instanceof Euro) {
				euro += paymentMethod.getAmount();
			} else if (paymentMethod instanceof SwissFranc) {
				srf += paymentMethod.getAmount();
			} else if (paymentMethod instanceof Dollar) {
				usd += paymentMethod.getAmount();
			}
		}
		System.out.println(euro + "€");
		System.out.println(srf + "CHF");
		System.out.println(usd + "$");
	}
	
	private static void testTask3() {
		System.out.println(BRIGHT_GREEN + "--- Task 3 ---" + RESET);
		double coinWeight = 0.0;
		double billArea = 0.0;
		for (PaymentMethod paymentMethod : MONEY) {
			if (!paymentMethod.isAmountValid()) {
				continue;
			}
			if (paymentMethod instanceof Coin coin) {
				coinWeight += coin.getWeight();
			} else if (paymentMethod instanceof Bill bill) {
				billArea += bill.getLength() * bill.getWidth();
			}
		}
		System.out.println(coinWeight + "g");
		System.out.println(billArea + "cm²");
	}
	
	private static void testTask4() {
		System.out.println(BRIGHT_GREEN + "--- Task 4 ---" + RESET);
		double coinHeight = 0.0;
		for (PaymentMethod paymentMethod : MONEY) {
			if (!paymentMethod.isAmountValid()) {
				continue;
			}
			if (paymentMethod instanceof Coin coin) {
				coinHeight += coin.getThickness();
			}
		}
		System.out.println(coinHeight + "cm");
	}
	
	private static void testTask5() {
		System.out.println(BRIGHT_GREEN + "--- Task 5 ---" + RESET);
		System.out.println("Unsorted: " + Arrays.toString(MONEY));
		ComparableMergesort.sort(MONEY);
		System.out.println("Sorted: " + Arrays.toString(MONEY));
	}
	
}
