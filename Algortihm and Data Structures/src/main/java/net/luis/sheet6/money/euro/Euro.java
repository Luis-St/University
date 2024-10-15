package net.luis.sheet6.money.euro;

import net.luis.sheet6.money.PaymentMethod;
import net.luis.util.Task;

@Task("1")
public abstract class Euro extends PaymentMethod {
	
	Euro(double amount) {
		super("Euro", amount);
	}
	
}

