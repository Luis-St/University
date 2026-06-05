package task09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {
	
	private static final double DELTA = 0.0001;
	private BankAccount account;
	
	@BeforeEach
	void setUp() {
		this.account = new BankAccount();
	}
	
	@Test
	void neuesKontoHatGuthabenNull() {
		assertEquals(0.0, this.account.getBalance(), DELTA);
	}
	
	@Test
	void depositErhoehtGuthaben() {
		this.account.deposit(100);
		assertEquals(100.0, this.account.getBalance(), DELTA);
	}
	
	@Test
	void depositMitNichtPositivemBetragWirftException() {
		assertThrows(IllegalArgumentException.class, () -> this.account.deposit(0));
		assertThrows(IllegalArgumentException.class, () -> this.account.deposit(-50));
	}
	
	@Test
	void withdrawVerringertGuthaben() {
		this.account.deposit(100);
		this.account.withdraw(40);
		assertEquals(60.0, this.account.getBalance(), DELTA);
	}
	
	@Test
	void withdrawMitNichtPositivemBetragWirftException() {
		assertThrows(IllegalArgumentException.class, () -> this.account.withdraw(0));
		assertThrows(IllegalArgumentException.class, () -> this.account.withdraw(-10));
	}
	
	@Test
	void withdrawUeberGuthabenWirftException() {
		Exception ex = assertThrows(IllegalArgumentException.class, () -> this.account.withdraw(50));
		assertEquals("Insufficient funds", ex.getMessage());
	}
	
	@Test
	void withdrawGesamtesGuthabenErlaubt() {
		this.account.deposit(50);
		this.account.withdraw(50);
		assertEquals(0.0, this.account.getBalance(), DELTA);
	}
}
