package task09;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart2 {
	
	private final List<Item> items = new ArrayList<>();
	private final IArtikelPreis artikelPreis;
	
	public ShoppingCart2(IArtikelPreis artikelPreis) {
		this.artikelPreis = artikelPreis;
	}
	
	// Preis wird ueber ArtikelPreis bezogen
	public void addItem(String name) {
		double price = this.artikelPreis.getPreis(name);
		this.items.add(new Item(name, price));
	}
	
	public boolean removeItem(String name) {
		return this.items.removeIf(item -> item.name().equals(name));
	}
	
	public double getTotalPrice() {
		double total = 0.0;
		for (Item item : this.items) {
			total += item.price();
		}
		return total;
	}
	
	public int getItemCount() {
		return this.items.size();
	}
}
