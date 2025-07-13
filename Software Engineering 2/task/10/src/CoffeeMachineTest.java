import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Luis-St
 *
 */

public class CoffeeMachineTest {
	
	private CoffeeMachine machine;
	private ByteArrayOutputStream outputStream;
	private PrintStream originalOut;
	
	@BeforeEach
	void setUp() {
		this.machine = new CoffeeMachine();
		this.outputStream = new ByteArrayOutputStream();
		this.originalOut = System.out;
		System.setOut(new PrintStream(this.outputStream));
	}
	
	@AfterEach
	void tearDown() {
		System.setOut(this.originalOut);
	}
	
	@Test
	void testConstructorInitialization() {
		CoffeeMachine newMachine = new CoffeeMachine();
		
		assertTrue(newMachine.isHasWater());
		assertTrue(newMachine.isHasCoffeeBeans());
		assertTrue(newMachine.isHasCups());
		assertEquals(0, newMachine.getCoinBalance());
		assertTrue(newMachine.hasEnoughIngredients());
	}
	
	@Test
	void testSetState() {
		CoinAcceptedState newState = new CoinAcceptedState();
		this.machine.setState(newState);
		
		String output = this.outputStream.toString();
		assertTrue(output.contains("Zustand gewechselt zu: CoinAcceptedState"));
	}
	
	@Test
	void testHasEnoughIngredients() {
		// Initial alle Zutaten vorhanden
		assertTrue(this.machine.hasEnoughIngredients());
		
		// Wasser fehlt
		this.machine.setHasWater(false);
		assertFalse(this.machine.hasEnoughIngredients());
		this.machine.setHasWater(true);
		
		// Kaffeebohnen fehlen
		this.machine.setHasCoffeeBeans(false);
		assertFalse(this.machine.hasEnoughIngredients());
		this.machine.setHasCoffeeBeans(true);
		
		// Becher fehlen
		this.machine.setHasCups(false);
		assertFalse(this.machine.hasEnoughIngredients());
	}
	
	@Test
	void testCoinOperations() {
		assertEquals(0, this.machine.getCoinBalance());
		
		this.machine.addCoin();
		assertEquals(1, this.machine.getCoinBalance());
		
		this.machine.addCoin();
		assertEquals(2, this.machine.getCoinBalance());
		
		this.machine.returnCoin();
		assertEquals(0, this.machine.getCoinBalance());
	}
	
	// IdleState
	
	@Test
	void testIdleStateInsertCoin() {
		this.machine.insertCoin();
		
		String output = this.outputStream.toString();
		assertTrue(output.contains("Münze akzeptiert"));
		assertTrue(output.contains("CoinAcceptedState"));
		assertEquals(1, this.machine.getCoinBalance());
	}
	
	@Test
	void testIdleStateOtherActionsRejected() {
		this.machine.checkIngredients();
		this.machine.prepareCoffee();
		this.machine.takeCup();
		
		String output = this.outputStream.toString();
		assertTrue(output.contains("Bitte zuerst Münze einwerfen"));
		assertTrue(output.contains("Kein Kaffee bereit"));
	}
	
	@Test
	void testIdleStateRefillIngredients() {
		// Zutaten leeren
		this.machine.setHasWater(false);
		this.machine.setHasCoffeeBeans(false);
		this.machine.setHasCups(false);
		
		this.machine.refillIngredients();
		
		assertTrue(this.machine.isHasWater());
		assertTrue(this.machine.isHasCoffeeBeans());
		assertTrue(this.machine.isHasCups());
		
		String output = this.outputStream.toString();
		assertTrue(output.contains("Zutaten aufgefüllt"));
	}
	
	// CoinAcceptedState
	
	@Test
	void testCoinAcceptedStateInsertCoin() {
		this.machine.insertCoin();
		this.outputStream.reset();
		
		this.machine.insertCoin();
		
		assertEquals(2, this.machine.getCoinBalance());
		String output = this.outputStream.toString();
		assertTrue(output.contains("Weitere Münze akzeptiert"));
		assertTrue(output.contains("Guthaben: 2"));
	}
	
	@Test
	void testCoinAcceptedStatePrepareCoffee() {
		this.machine.insertCoin();
		this.outputStream.reset();
		
		this.machine.prepareCoffee();
		
		String output = this.outputStream.toString();
		assertTrue(output.contains("CheckingIngredientsState") ||
			output.contains("Prüfe Zutaten"));
	}
	
	// CheckingIngredientsState
	
	@Test
	void testCheckingIngredientsStateWithIngredients() {
		this.machine.insertCoin();
		this.machine.prepareCoffee();
		
		String output = this.outputStream.toString();
		assertTrue(output.contains("Zutaten verfügbar") || output.contains("PreparingCoffeeState"));
	}
	
	@Test
	void testCheckingIngredientsStateWithoutIngredients() {
		this.machine.setHasWater(false);
		this.machine.setHasCoffeeBeans(false);
		
		this.machine.insertCoin();
		this.machine.prepareCoffee();
		
		String output = this.outputStream.toString();
		assertTrue(output.contains("Fehler: Zutaten fehlen") || output.contains("ErrorState"));
		assertTrue(output.contains("Kein Wasser") || output.contains("Keine Kaffeebohnen"));
	}
	
	// PreparingCoffeeState
	
	@Test
	void testPreparingCoffeeStatePreparation() {
		this.machine.insertCoin();
		this.machine.prepareCoffee();
		
		String output = this.outputStream.toString();
		assertTrue(output.contains("Bereite Kaffee zu") || output.contains("Zubereitungsschritt"));
	}
	
	@Test
	void testPreparingCoffeeStateBlocksOtherActions() {
		this.machine.insertCoin();
		this.machine.prepareCoffee();
		this.outputStream.reset();
		
		this.machine.setState(new PreparingCoffeeState());
		
		this.machine.insertCoin();
		this.machine.takeCup();
		this.machine.refillIngredients();
		
		String output = this.outputStream.toString();
		assertTrue(output.contains("wird bereits zubereitet") || output.contains("Bitte warten") || output.contains("Kann während der Zubereitung nicht"));
	}
	
	// CoffeeReadyState
	
	@Test
	void testCoffeeReadyStateTakeCup() {
		this.machine.setState(new CoffeeReadyState());
		this.machine.addCoin();
		
		this.machine.takeCup();
		
		assertEquals(0, this.machine.getCoinBalance());
		String output = this.outputStream.toString();
		assertTrue(output.contains("Danke! Becher entnommen") || output.contains("IdleState"));
	}
	
	@Test
	void testCoffeeReadyStateBlocksOtherActions() {
		this.machine.setState(new CoffeeReadyState());
		
		this.machine.insertCoin();
		this.machine.prepareCoffee();
		this.machine.refillIngredients();
		
		String output = this.outputStream.toString();
		assertTrue(output.contains("Bitte nehmen Sie zuerst Ihren Kaffee"));
	}
	
	// ErrorState
	
	@Test
	void testErrorStateReturnsCoin() {
		this.machine.setState(new ErrorState());
		this.machine.addCoin();
		
		this.machine.insertCoin();
		
		assertEquals(0, this.machine.getCoinBalance());
		String output = this.outputStream.toString();
		assertTrue(output.contains("Münze wird zurückgegeben"));
	}
	
	@Test
	void testErrorStateRefillToIdle() {
		this.machine.setState(new ErrorState());
		
		this.machine.refillIngredients();
		
		assertTrue(this.machine.isHasWater());
		assertTrue(this.machine.isHasCoffeeBeans());
		assertTrue(this.machine.isHasCups());
		
		String output = this.outputStream.toString();
		assertTrue(output.contains("Maschine ist wieder betriebsbereit") || output.contains("IdleState"));
	}
	
	@Test
	void testErrorStateRejectsOtherActions() {
		this.machine.setState(new ErrorState());
		
		this.machine.prepareCoffee();
		this.machine.takeCup();
		this.machine.checkIngredients();
		
		String output = this.outputStream.toString();
		assertTrue(output.contains("Kann keinen Kaffee zubereiten") || output.contains("Kein Kaffee verfügbar") || output.contains("Zutaten fehlen"));
	}
}
