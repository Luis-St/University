package task09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
	
	private static final double DELTA = 0.0001;
	private ShoppingCart cart;
	
	@BeforeEach
	void setUp() {
		this.cart = new ShoppingCart();
	}
	
	@Test
	void neuerWarenkorbIstLeer() {
		assertEquals(0, this.cart.getItemCount());
		assertEquals(0.0, this.cart.getTotalPrice(), DELTA);
	}
	
	@Test
	void addItemFuegtArtikelHinzu() {
		this.cart.addItem("Apfel", 0.50);
		assertEquals(1, this.cart.getItemCount());
		assertEquals(0.50, this.cart.getTotalPrice(), DELTA);
	}
	
	@Test
	void getTotalPriceSummiertAlleArtikel() {
		this.cart.addItem("Apfel", 0.50);
		this.cart.addItem("Brot", 2.20);
		this.cart.addItem("Milch", 1.30);
		assertEquals(4.00, this.cart.getTotalPrice(), DELTA);
	}
	
	@Test
	void removeItemEntferntArtikel() {
		this.cart.addItem("Apfel", 0.50);
		this.cart.addItem("Brot", 2.20);
		boolean removed = this.cart.removeItem("Apfel");
		assertTrue(removed);
		assertEquals(1, this.cart.getItemCount());
		assertEquals(2.20, this.cart.getTotalPrice(), DELTA);
	}
	
	@Test
	void removeItemMitUnbekanntemNamenAendertNichts() {
		this.cart.addItem("Apfel", 0.50);
		boolean removed = this.cart.removeItem("Banane");
		assertFalse(removed);
		assertEquals(1, this.cart.getItemCount());
	}
}
