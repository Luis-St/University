package net.luis.sheet6.money.usd;

import net.luis.sheet6.money.Bill;
import net.luis.util.Task;
import net.luis.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Task("1")
public class DollarBill extends Dollar implements Bill {
	
	private static final List<DollarBill> BILLS = Utils.make(new ArrayList<>(), list -> {
		list.add(new DollarBill(1,   155.81, 66.42));
		list.add(new DollarBill(2,   155.81, 66.42));
		list.add(new DollarBill(5,   155.81, 66.42));
		list.add(new DollarBill(10,  155.81, 66.42));
		list.add(new DollarBill(20,  155.81, 66.42));
		list.add(new DollarBill(50,  155.81, 66.42));
		list.add(new DollarBill(100, 155.81, 66.42));
	});
	
	private final double length;
	private final double width;
	
	private DollarBill(double amount, double length, double width) {
		super(amount);
		this.length = length;
		this.width = width;
	}
	
	public static @NotNull DollarBill of(double amount) {
		for (DollarBill bill : BILLS) {
			if (bill.getAmount() == amount) {
				return bill;
			}
		}
		return new DollarBill(amount, -1, -1);
	}
	
	@Override
	public double getLength() {
		return this.length;
	}
	
	@Override
	public double getWidth() {
		return this.width;
	}
	
	@Override
	public boolean isAmountValid() {
		return BILLS.contains(this);
	}
	
}
