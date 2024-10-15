package net.luis.sheet6.money.srf;

import net.luis.sheet6.money.PaymentMethod;
import net.luis.util.Task;

@Task("1")
public abstract class SwissFranc extends PaymentMethod {
	
	SwissFranc(double amount) {
		super("Swiss franc", amount);
	}
	
}
