import java.util.*;

enum Decision {
	BUY, SELL, HOLD
}

interface Observable {
	
	void addObserver(Observer observer);
	
	void removeObserver(Observer observer);
	
	void notifyObservers();
}

@FunctionalInterface
interface Observer {
	
	void update(Observable observable);
}

@FunctionalInterface
interface InvestmentStrategy {
	
	Decision makeDecision(double currentPrice, double previousPrice);
}

class Aktie implements Observable {
	
	private final String name;
	private final List<Observer> observers;
	private double currentPrice;
	private double previousPrice;
	
	Aktie(String name, double initialPrice) {
		this.name = name;
		this.currentPrice = initialPrice;
		this.previousPrice = initialPrice;
		this.observers = new ArrayList<>();
	}
	
	@Override
	public void addObserver(Observer observer) {
		this.observers.add(observer);
		System.out.println("Anleger wurde für Aktie " + this.name + " registriert.");
	}
	
	@Override
	public void removeObserver(Observer observer) {
		this.observers.remove(observer);
		System.out.println("Anleger wurde für Aktie " + this.name + " abgemeldet.");
	}
	
	@Override
	public void notifyObservers() {
		System.out.println("Benachrichtige " + this.observers.size() + " Anleger über Preisänderung von " + this.name);
		for (Observer observer : this.observers) {
			observer.update(this);
		}
	}
	
	public double getPrice() {return this.currentPrice;}
	
	public void setPrice(double newPrice) {
		this.previousPrice = this.currentPrice;
		this.currentPrice = newPrice;
		System.out.println("Aktie " + this.name + ": Preis von " + this.previousPrice + " auf " + this.currentPrice + " geändert");
		this.notifyObservers();
	}
	
	public double getPreviousPrice() {return this.previousPrice;}
	
	public String getName() {return this.name;}
}

class LowRiskStrategy implements InvestmentStrategy {
	
	@Override
	public Decision makeDecision(double currentPrice, double previousPrice) {
		double changePercent = ((currentPrice - previousPrice) / previousPrice) * 100;
		
		if (changePercent < -2.0) {
			return Decision.BUY;
		} else if (changePercent > 5.0) {
			return Decision.SELL;
		}
		return Decision.HOLD;
	}
}

class MediumRiskStrategy implements InvestmentStrategy {
	
	@Override
	public Decision makeDecision(double currentPrice, double previousPrice) {
		double changePercent = ((currentPrice - previousPrice) / previousPrice) * 100;
		
		if (changePercent < -5.0) {
			return Decision.BUY;
		} else if (changePercent > 10.0) {
			return Decision.SELL;
		} else if (changePercent > 2.0) {
			return Decision.BUY;
		}
		return Decision.HOLD;
	}
}

class HighRiskStrategy implements InvestmentStrategy {
	
	@Override
	public Decision makeDecision(double currentPrice, double previousPrice) {
		double changePercent = ((currentPrice - previousPrice) / previousPrice) * 100;
		
		if (Math.abs(changePercent) > 3.0) {
			if (changePercent > 0) {
				return Decision.BUY;
			} else {
				return Decision.SELL;
			}
		}
		return Decision.HOLD;
	}
}

class Anleger implements Observer {
	
	private final String name;
	private final Map<String, Integer> holdings;
	private InvestmentStrategy strategy;
	private double portfolio;
	
	Anleger(String name, InvestmentStrategy strategy) {
		this.name = name;
		this.strategy = strategy;
		this.portfolio = 10000.0;
		this.holdings = new HashMap<>();
	}
	
	@Override
	public void update(Observable observable) {
		if (observable instanceof Aktie aktie) {
			
			Decision decision = this.strategy.makeDecision(
				aktie.getPrice(),
				aktie.getPreviousPrice()
			);
			
			System.out.println(this.name + " (" + this.strategy.getClass().getSimpleName() + ") " + "entscheidet: " + decision + " für " + aktie.getName());
			
			this.executeDecision(decision, aktie);
		}
	}
	
	public void executeDecision(Decision decision, Aktie aktie) {
		String aktieName = aktie.getName();
		double preis = aktie.getPrice();
		int currentHoldings = this.holdings.getOrDefault(aktieName, 0);
		
		switch (decision) {
			case BUY:
				if (this.portfolio >= preis) {
					this.portfolio -= preis;
					this.holdings.put(aktieName, currentHoldings + 1);
					System.out.println("  -> " + this.name + " kauft 1 Aktie von " + aktieName + " für " + preis + "€. Portfolio: " + String.format("%.2f", this.portfolio) + "€");
				} else {
					System.out.println("  -> " + this.name + " hat nicht genügend Kapital für Kauf.");
				}
				break;
			
			case SELL:
				if (currentHoldings > 0) {
					this.portfolio += preis;
					this.holdings.put(aktieName, currentHoldings - 1);
					System.out.println("  -> " + this.name + " verkauft 1 Aktie von " + aktieName + " für " + preis + "€. Portfolio: " + String.format("%.2f", this.portfolio) + "€");
				} else {
					System.out.println("  -> " + this.name + " besitzt keine Aktien von " + aktieName + " zum Verkauf.");
				}
				break;
			
			case HOLD:
				System.out.println("  -> " + this.name + " wartet ab.");
				break;
		}
	}
	
	public void setStrategy(InvestmentStrategy strategy) {
		this.strategy = strategy;
		System.out.println(this.name + " wechselt zu " + strategy.getClass().getSimpleName());
	}
	
	public void printPortfolio() {
		System.out.println("\n" + this.name + " Portfolio:");
		System.out.println("  Bargeld: " + String.format("%.2f", this.portfolio) + "€");
		System.out.println("  Aktienbesitz:");
		for (Map.Entry<String, Integer> entry : this.holdings.entrySet()) {
			if (entry.getValue() > 0) {
				System.out.println("    " + entry.getKey() + ": " + entry.getValue() + " Stück");
			}
		}
	}
	
	public String getName() {return this.name;}
	
	public double getPortfolio() {return this.portfolio;}
}

class Boerse {
	
	private final List<Aktie> aktien;
	private final List<Anleger> anleger;
	private final Random random;
	
	Boerse() {
		this.aktien = new ArrayList<>();
		this.anleger = new ArrayList<>();
		this.random = new Random();
	}
	
	public void addAktie(Aktie aktie) {
		this.aktien.add(aktie);
		System.out.println("Aktie " + aktie.getName() + " zur Börse hinzugefügt.");
	}
	
	public void addAnleger(Anleger anleger) {
		this.anleger.add(anleger);
		System.out.println("Anleger " + anleger.getName() + " zur Börse hinzugefügt.");
	}
	
	public void subscribeAnlegerToAktie(Anleger anleger, Aktie aktie) {
		aktie.addObserver(anleger);
	}
	
	public void updateAktienPreise() {
		for (Aktie aktie : this.aktien) {
			double currentPrice = aktie.getPrice();
			double changePercent = (this.random.nextDouble() - 0.5) * 20;
			double newPrice = currentPrice * (1 + changePercent / 100);
			
			newPrice = Math.max(newPrice, 1.0);
			
			aktie.setPrice(Math.round(newPrice * 100.0) / 100.0);
		}
	}
	
	public void simulateTrading(int rounds) {
		System.out.println("\n=== Börsen-Simulation startet (" + rounds + " Runden) ===\n");
		
		for (int i = 1; i <= rounds; i++) {
			System.out.println("--- Runde " + i + " ---");
			this.updateAktienPreise();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			
			System.out.println();
		}
		
		System.out.println("=== Simulation beendet ===");
		this.printFinalResults();
	}
	
	private void printFinalResults() {
		System.out.println("\n=== Endresultate ===");
		for (Anleger anleger : this.anleger) {
			anleger.printPortfolio();
		}
	}
}

public class BoersenSimulation {
	
	public static void main(String[] args) {
		Boerse boerse = new Boerse();
		
		Aktie apple = new Aktie("AAPL", 150.0);
		Aktie google = new Aktie("GOOGL", 2500.0);
		Aktie tesla = new Aktie("TSLA", 800.0);
		
		boerse.addAktie(apple);
		boerse.addAktie(google);
		boerse.addAktie(tesla);
		
		Anleger konservativ = new Anleger("Max Mustermann", new LowRiskStrategy());
		Anleger ausgewogen = new Anleger("Anna Schmidt", new MediumRiskStrategy());
		Anleger risikofreudig = new Anleger("Tom Wagner", new HighRiskStrategy());
		
		boerse.addAnleger(konservativ);
		boerse.addAnleger(ausgewogen);
		boerse.addAnleger(risikofreudig);
		
		System.out.println("\n--- Registrierung der Anleger ---");
		boerse.subscribeAnlegerToAktie(konservativ, apple);
		boerse.subscribeAnlegerToAktie(konservativ, google);
		
		boerse.subscribeAnlegerToAktie(ausgewogen, apple);
		boerse.subscribeAnlegerToAktie(ausgewogen, tesla);
		
		boerse.subscribeAnlegerToAktie(risikofreudig, apple);
		boerse.subscribeAnlegerToAktie(risikofreudig, google);
		boerse.subscribeAnlegerToAktie(risikofreudig, tesla);
		
		boerse.simulateTrading(5);
		
		System.out.println("\n--- Strategiewechsel Demo ---");
		konservativ.setStrategy(new HighRiskStrategy());
		
		System.out.println("\nWeitere Simulation nach Strategiewechsel:");
		boerse.simulateTrading(2);
	}
}
