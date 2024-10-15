package net.luis.sheet6.money.srf;

import net.luis.sheet6.money.Bill;
import net.luis.util.Task;
import net.luis.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Task("1")
public class SwissFrancBill extends SwissFranc implements Bill {
	
	private static final List<SwissFrancBill> BILLS = Utils.make(new ArrayList<>(), list -> {
		list.add(new SwissFrancBill(10, 74.0, 126.0));
		list.add(new SwissFrancBill(20, 74.0, 137.0));
		list.add(new SwissFrancBill(50, 74.0, 148.0));
		list.add(new SwissFrancBill(100, 74.0, 159.0));
		list.add(new SwissFrancBill(200, 74.0, 170.0));
		list.add(new SwissFrancBill(1000, 74.0, 181.0));
	});
	
	private final double length;
	private final double width;
	
	private SwissFrancBill(double amount, double length, double width) {
		super(amount);
		this.length = length;
		this.width = width;
	}
	
	public static @NotNull SwissFrancBill of(double amount) {
		for (SwissFrancBill bill : BILLS) {
			if (bill.getAmount() == amount) {
				return bill;
			}
		}
		return new SwissFrancBill(amount, -1, -1);
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

