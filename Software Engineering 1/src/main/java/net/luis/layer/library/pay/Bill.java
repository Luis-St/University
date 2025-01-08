package net.luis.layer.library.pay;

import net.luis.layer.library.Address;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-St
 *
 */

public record Bill(
	@NotNull Address address,
	double amount
) implements PaymentMethod {
	
	@Override
	public boolean pay(double amount) {
		System.out.println("Paying " + amount + " using Bill");
		return true;
	}
}
