package net.luis.sheet6.money;

import net.luis.util.Task;
import org.jetbrains.annotations.NotNull;

@Task("1")
public abstract class PaymentMethod implements Comparable<PaymentMethod> {
	
	private final String currency;
	private final double amount;
	
	protected PaymentMethod(String currency, double amount) {
		this.currency = currency;
		this.amount = amount;
	}
	
	public String getCurrency() {
		return this.currency;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public abstract boolean isAmountValid();
	
	@Override
	public int compareTo(@NotNull PaymentMethod paymentMethod) {
		return Double.compare(this.amount, paymentMethod.amount);
	}
	
	@Override
	public String toString() {
		return amount + " " + currency;
	}
}