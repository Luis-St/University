package net.luis.sheet6.money.euro;

import net.luis.sheet6.money.Bill;
import net.luis.util.Task;
import net.luis.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Task("1")
public class EuroBill extends Euro implements Bill {
	
	private static final List<EuroBill> BILLS = Utils.make(new ArrayList<>(), list -> {
		list.add(new EuroBill(5, 120.0, 62.0));
		list.add(new EuroBill(10, 127.0, 67.0));
		list.add(new EuroBill(20, 133.0, 72.0));
		list.add(new EuroBill(50, 140.0, 77.0));
		list.add(new EuroBill(100, 147.0, 82.0));
		list.add(new EuroBill(200, 153.0, 82.0));
		list.add(new EuroBill(500, 160.0, 82.0));
	});
	
	private final double length;
	private final double width;
	
	private EuroBill(double amount, double length, double width) {
		super(amount);
		this.length = length;
		this.width = width;
	}
	
	public static @NotNull EuroBill of(double amount) {
		for (EuroBill bill : BILLS) {
			if (bill.getAmount() == amount) {
				return bill;
			}
		}
		return new EuroBill(amount, 0.0, 0.0);
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
