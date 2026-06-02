import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	
	private final List<Item> items = new ArrayList<>();
	
	// Artikel mit Namen und Preis hinzufuegen
	public void addItem(String name, double price) {
		this.items.add(new Item(name, price));
	}
	
	// Ersten Artikel mit dem angegebenen Namen entfernen
	public boolean removeItem(String name) {
		return this.items.removeIf(item -> item.name().equals(name));
	}
	
	// Gesamtpreis aller Artikel berechnen
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
