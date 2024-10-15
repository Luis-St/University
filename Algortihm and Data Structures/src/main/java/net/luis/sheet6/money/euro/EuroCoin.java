package net.luis.sheet6.money.euro;

import net.luis.sheet6.money.Coin;
import net.luis.util.Task;
import net.luis.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Task("1")
public class EuroCoin extends Euro implements Coin {
	
	private static final List<EuroCoin> COINS = Utils.make(new ArrayList<>(), list -> {
		list.add(new EuroCoin(0.01, 2.30, 16.25, 1.67));
		list.add(new EuroCoin(0.02, 3.06, 18.75, 1.67));
		list.add(new EuroCoin(0.05, 3.92, 21.25, 1.67));
		list.add(new EuroCoin(0.10, 4.10, 19.75, 1.93));
		list.add(new EuroCoin(0.20, 5.74, 22.25, 2.14));
		list.add(new EuroCoin(0.50, 7.80, 24.25, 2.38));
		list.add(new EuroCoin(1.00, 7.50, 23.25, 2.33));
		list.add(new EuroCoin(2.00, 8.50, 25.75, 2.20));
	});
	
	private final double weight;
	private final double diameter;
	private final double thickness;
	
	private EuroCoin(double amount, double weight, double diameter, double thickness) {
		super(amount);
		this.weight = weight;
		this.diameter = diameter;
		this.thickness = thickness;
	}
	
	public static @NotNull EuroCoin of(double amount) {
		for (EuroCoin coin : COINS) {
			if (coin.getAmount() == amount) {
				return coin;
			}
		}
		return new EuroCoin(amount, -1, -1, -1);
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

