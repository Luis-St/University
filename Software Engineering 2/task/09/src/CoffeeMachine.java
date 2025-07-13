public class CoffeeMachine {
	
	private CoffeeMachineState currentState;
	private boolean hasWater;
	private boolean hasCoffeeBeans;
	private boolean hasCups;
	private int coinBalance;
	
	CoffeeMachine() {
		this.currentState = new IdleState();
		this.hasWater = true;
		this.hasCoffeeBeans = true;
		this.hasCups = true;
		this.coinBalance = 0;
	}
	
	public void setState(CoffeeMachineState state) {
		this.currentState = state;
		System.out.println("Zustand gewechselt zu: " + state.getClass().getSimpleName());
	}
	
	public void insertCoin() {
		this.currentState.insertCoin(this);
	}
	
	public void checkIngredients() {
		this.currentState.checkIngredients(this);
	}
	
	public void prepareCoffee() {
		this.currentState.prepareCoffee(this);
	}
	
	public void takeCup() {
		this.currentState.takeCup(this);
	}
	
	public void refillIngredients() {
		this.currentState.refillIngredients(this);
	}
	
	public boolean hasEnoughIngredients() {
		return this.hasWater && this.hasCoffeeBeans && this.hasCups;
	}
	
	public boolean isHasWater() {return this.hasWater;}
	
	public void setHasWater(boolean hasWater) {this.hasWater = hasWater;}
	
	public boolean isHasCoffeeBeans() {return this.hasCoffeeBeans;}
	
	public void setHasCoffeeBeans(boolean hasCoffeeBeans) {this.hasCoffeeBeans = hasCoffeeBeans;}
	
	public boolean isHasCups() {return this.hasCups;}
	
	public void setHasCups(boolean hasCups) {this.hasCups = hasCups;}
	
	public int getCoinBalance() {return this.coinBalance;}
	
	public void addCoin() {this.coinBalance++;}
	
	public void returnCoin() {this.coinBalance = 0;}
	
	public static void main(String[] args) {
		CoffeeMachine machine = new CoffeeMachine();
		
		System.out.println("=== Kaffeemaschine Test ===");
		System.out.println("1. Münze einwerfen:");
		machine.insertCoin();
		
		System.out.println("\n2. Kaffee zubereiten:");
		machine.prepareCoffee();
		
		System.out.println("\n3. Kaffee nehmen:");
		machine.takeCup();
		
		System.out.println("\n4. Test mit fehlenden Zutaten:");
		machine.insertCoin();
		machine.prepareCoffee();
		
		System.out.println("\n5. Zutaten auffüllen:");
		machine.refillIngredients();
		
		System.out.println("\n6. Erneuter Versuch:");
		machine.insertCoin();
		machine.prepareCoffee();
		machine.takeCup();
	}
}

interface CoffeeMachineState {
	
	void insertCoin(CoffeeMachine machine);
	
	void checkIngredients(CoffeeMachine machine);
	
	void prepareCoffee(CoffeeMachine machine);
	
	void takeCup(CoffeeMachine machine);
	
	void refillIngredients(CoffeeMachine machine);
}

class IdleState implements CoffeeMachineState {
	
	@Override
	public void insertCoin(CoffeeMachine machine) {
		machine.addCoin();
		machine.setState(new CoinAcceptedState());
		System.out.println("Münze akzeptiert. Wählen Sie Ihren Kaffee.");
	}
	
	@Override
	public void checkIngredients(CoffeeMachine machine) {
		System.out.println("Bitte zuerst Münze einwerfen.");
	}
	
	@Override
	public void prepareCoffee(CoffeeMachine machine) {
		System.out.println("Bitte zuerst Münze einwerfen.");
	}
	
	@Override
	public void takeCup(CoffeeMachine machine) {
		System.out.println("Kein Kaffee bereit.");
	}
	
	@Override
	public void refillIngredients(CoffeeMachine machine) {
		machine.setHasWater(true);
		machine.setHasCoffeeBeans(true);
		machine.setHasCups(true);
		System.out.println("Zutaten aufgefüllt.");
	}
}

class CoinAcceptedState implements CoffeeMachineState {
	
	@Override
	public void insertCoin(CoffeeMachine machine) {
		machine.addCoin();
		System.out.println("Weitere Münze akzeptiert. Guthaben: " + machine.getCoinBalance());
	}
	
	@Override
	public void checkIngredients(CoffeeMachine machine) {
		machine.setState(new CheckingIngredientsState());
		machine.checkIngredients(); // Delegiere an neuen Zustand
	}
	
	@Override
	public void prepareCoffee(CoffeeMachine machine) {
		this.checkIngredients(machine);
	}
	
	@Override
	public void takeCup(CoffeeMachine machine) {
		System.out.println("Kein Kaffee bereit. Bitte erst Kaffee zubereiten.");
	}
	
	@Override
	public void refillIngredients(CoffeeMachine machine) {
		machine.setHasWater(true);
		machine.setHasCoffeeBeans(true);
		machine.setHasCups(true);
		System.out.println("Zutaten aufgefüllt.");
	}
}

class CheckingIngredientsState implements CoffeeMachineState {
	
	@Override
	public void insertCoin(CoffeeMachine machine) {
		System.out.println("Überprüfung läuft. Bitte warten...");
	}
	
	@Override
	public void checkIngredients(CoffeeMachine machine) {
		System.out.println("Prüfe Zutaten...");
		if (machine.hasEnoughIngredients()) {
			machine.setState(new PreparingCoffeeState());
			System.out.println("Zutaten verfügbar. Bereite Kaffee zu...");
			machine.prepareCoffee();
		} else {
			machine.setState(new ErrorState());
			System.out.println("Fehler: Zutaten fehlen!");
			if (!machine.isHasWater()) System.out.println("- Kein Wasser");
			if (!machine.isHasCoffeeBeans()) System.out.println("- Keine Kaffeebohnen");
			if (!machine.isHasCups()) System.out.println("- Keine Becher");
		}
	}
	
	@Override
	public void prepareCoffee(CoffeeMachine machine) {
		this.checkIngredients(machine);
	}
	
	@Override
	public void takeCup(CoffeeMachine machine) {
		System.out.println("Kaffee wird noch nicht zubereitet.");
	}
	
	@Override
	public void refillIngredients(CoffeeMachine machine) {
		machine.setHasWater(true);
		machine.setHasCoffeeBeans(true);
		machine.setHasCups(true);
		System.out.println("Zutaten aufgefüllt.");
	}
}

class PreparingCoffeeState implements CoffeeMachineState {
	
	@Override
	public void insertCoin(CoffeeMachine machine) {
		System.out.println("Kaffee wird bereits zubereitet. Bitte warten...");
	}
	
	@Override
	public void checkIngredients(CoffeeMachine machine) {
		System.out.println("Zubereitung läuft...");
	}
	
	@Override
	public void prepareCoffee(CoffeeMachine machine) {
		System.out.println("Bereite Kaffee zu...");
		
		try {
			for (int i = 1; i <= 3; i++) {
				Thread.sleep(1000);
				System.out.println("Zubereitungsschritt " + i + "/3...");
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		machine.setHasWater(false);
		machine.setHasCoffeeBeans(false);
		machine.setHasCups(false);
		
		machine.setState(new CoffeeReadyState());
		System.out.println("Kaffee ist fertig! Bitte nehmen Sie Ihren Kaffee.");
	}
	
	@Override
	public void takeCup(CoffeeMachine machine) {
		System.out.println("Kaffee wird noch zubereitet. Bitte warten...");
	}
	
	@Override
	public void refillIngredients(CoffeeMachine machine) {
		System.out.println("Kann während der Zubereitung nicht auffüllen.");
	}
}

class CoffeeReadyState implements CoffeeMachineState {
	
	@Override
	public void insertCoin(CoffeeMachine machine) {
		System.out.println("Bitte nehmen Sie zuerst Ihren Kaffee.");
	}
	
	@Override
	public void checkIngredients(CoffeeMachine machine) {
		System.out.println("Kaffee ist bereit. Bitte nehmen Sie ihn.");
	}
	
	@Override
	public void prepareCoffee(CoffeeMachine machine) {
		System.out.println("Kaffee ist bereits fertig. Bitte nehmen Sie ihn.");
	}
	
	@Override
	public void takeCup(CoffeeMachine machine) {
		machine.setState(new IdleState());
		machine.returnCoin();
		System.out.println("Danke! Becher entnommen. Genießen Sie Ihren Kaffee!");
		System.out.println("Maschine ist wieder bereit für neue Bestellungen.");
	}
	
	@Override
	public void refillIngredients(CoffeeMachine machine) {
		System.out.println("Bitte nehmen Sie zuerst Ihren Kaffee.");
	}
}

class ErrorState implements CoffeeMachineState {
	
	@Override
	public void insertCoin(CoffeeMachine machine) {
		System.out.println("Maschine ist außer Betrieb. Münze wird zurückgegeben.");
		machine.returnCoin();
	}
	
	@Override
	public void checkIngredients(CoffeeMachine machine) {
		System.out.println("Fehler: Zutaten fehlen. Bitte auffüllen.");
	}
	
	@Override
	public void prepareCoffee(CoffeeMachine machine) {
		System.out.println("Kann keinen Kaffee zubereiten. Zutaten fehlen.");
	}
	
	@Override
	public void takeCup(CoffeeMachine machine) {
		System.out.println("Kein Kaffee verfügbar.");
	}
	
	@Override
	public void refillIngredients(CoffeeMachine machine) {
		machine.setHasWater(true);
		machine.setHasCoffeeBeans(true);
		machine.setHasCups(true);
		machine.setState(new IdleState());
		System.out.println("Zutaten aufgefüllt. Maschine ist wieder betriebsbereit.");
	}
}
