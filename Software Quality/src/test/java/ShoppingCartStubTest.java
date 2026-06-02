import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartStubTest {
	
	private static final double DELTA = 0.0001;
	private ShoppingCart2 cart;
	
	@BeforeEach
	void setUp() {
		this.cart = new ShoppingCart2(new ArtikelPreisStub());
	}
	
	@Test
	void addItemBeziehtPreisVomStub() {
		this.cart.addItem("Apfel");
		assertEquals(1.00, this.cart.getTotalPrice(), DELTA);
	}
	
	@Test
	void getTotalPriceMitMehrerenArtikeln() {
		this.cart.addItem("Apfel");
		this.cart.addItem("Brot");
		assertEquals(4.00, this.cart.getTotalPrice(), DELTA);
	}
	
	@Test
	void removeItemEntferntArtikel() {
		this.cart.addItem("Apfel");
		this.cart.addItem("Brot");
		this.cart.removeItem("Apfel");
		assertEquals(3.00, this.cart.getTotalPrice(), DELTA);
		assertEquals(1, this.cart.getItemCount());
	}
	
	@Test
	void unbekannterArtikelWirftException() {
		assertThrows(IllegalArgumentException.class, () -> this.cart.addItem("Banane"));
	}
	
	// Stub-Klasse: ersetzt die reale Datenbankanbindung
	static class ArtikelPreisStub implements IArtikelPreis {
		
		@Override
		public double getPreis(String artikelName) {
			switch (artikelName) {
				case "Apfel":
					return 1.00;
				case "Brot":
					return 3.00;
				default:
					throw new IllegalArgumentException("Unbekannt: " + artikelName);
			}
		}
	}
}
