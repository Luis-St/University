package net.luis.sheet6.money.srf;

import net.luis.sheet6.money.Coin;
import net.luis.util.Task;
import net.luis.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Task("1")
public class SwissFrancCoin extends SwissFranc implements Coin {
	
	public static final SwissFrancCoin INVALID = new SwissFrancCoin(-1, -1, -1, -1);
	private static final List<SwissFrancCoin> COINS = Utils.make(new ArrayList<>(), list -> {
		list.add(new SwissFrancCoin(0.05, 1.8, 17.15, 1.25));
		list.add(new SwissFrancCoin(0.10, 3.0, 19.15, 1.45));
		list.add(new SwissFrancCoin(0.20, 4.0, 21.05, 1.65));
		list.add(new SwissFrancCoin(0.50, 2.2, 18.2, 1.25));
		list.add(new SwissFrancCoin(1.00, 4.4, 23.2, 1.55));
		list.add(new SwissFrancCoin(2.00, 8.8, 27.40, 2.15));
		list.add(new SwissFrancCoin(5.00, 13.2, 31.45, 2.35));
	});
	
	private final double weight;
	private final double diameter;
	private final double thickness;
	
	private SwissFrancCoin(double amount, double weight, double diameter, double thickness) {
		super(amount);
		this.weight = weight;
		this.diameter = diameter;
		this.thickness = thickness;
	}
	
	public static @NotNull SwissFrancCoin of(double amount) {
		for (SwissFrancCoin coin : COINS) {
			if (coin.getAmount() == amount) {
				return coin;
			}
		}
		return new SwissFrancCoin(amount, -1, -1, -1);
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
