package net.luis.sheet6.money.usd;

import net.luis.sheet6.money.Coin;
import net.luis.util.Task;
import net.luis.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Task("1")
public class DollarCoin extends Dollar implements Coin {
	
	public static final DollarCoin INVALID = new DollarCoin(-1, -1, -1, -1);
	private static final List<DollarCoin> COINS = Utils.make(new ArrayList<>(), list -> {
		list.add(new DollarCoin(0.01, 2.50, 19.05, 1.55));
		list.add(new DollarCoin(0.05, 5.00, 21.21, 1.95));
		list.add(new DollarCoin(0.10, 2.27, 19.91, 1.35));
		list.add(new DollarCoin(0.25, 5.67, 24.26, 1.75));
		list.add(new DollarCoin(0.50, 11.34, 30.61, 2.15));
		list.add(new DollarCoin(1.00, 8.10, 26.50, 2.00));
	});
	
	private final double weight;
	private final double diameter;
	private final double thickness;
	
	private DollarCoin(double amount, double weight, double diameter, double thickness) {
		super(amount);
		this.weight = weight;
		this.diameter = diameter;
		this.thickness = thickness;
	}
	
	public static @NotNull DollarCoin of(double amount) {
		for (DollarCoin coin : COINS) {
			if (coin.getAmount() == amount) {
				return coin;
			}
		}
		return new DollarCoin(amount, -1, -1, -1);
	}
	
	@Override
	public double getWeight() {
		return this.weight;
	}
	
	@Override
	public double getDiameter() {
		return this.diameter;
	}
	
	@Override
	public double getThickness() {
		return this.thickness;
	}
	
	@Override
	public boolean isAmountValid() {
		return COINS.contains(this);
	}
	
}