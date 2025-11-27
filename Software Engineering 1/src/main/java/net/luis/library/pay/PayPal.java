package net.luis.library.pay;

import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-St
 *
 */

public class PayPal implements PaymentMethod {
	
	private final String username;
	private final String password;
	
	public PayPal(@NotNull String username, @NotNull String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	public boolean pay(double amount) {
		System.out.println("Paying " + amount + " using PayPal");
		return true;
	}
}
