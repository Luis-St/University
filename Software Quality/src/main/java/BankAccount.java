public class BankAccount {
	
	private double balance;
	
	public BankAccount() {
		this.balance = 0.0;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be positive");
		}
		this.balance += amount;
	}
	
	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdrawal amount must be positive");
		}
		if (amount > this.balance) {
			throw new IllegalArgumentException("Insufficient funds");
		}
		this.balance -= amount;
	}
}
