package net.luis.sheet6.money.usd;

import net.luis.sheet6.money.PaymentMethod;
import net.luis.util.Task;

@Task("1")
public abstract class Dollar extends PaymentMethod {
	
	Dollar(double amount) {
		super("Dollar", amount);
	}
	
}
