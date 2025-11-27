package net.luis.library.pay;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.time.LocalDateTime;

/**
 *
 * @author Luis-St
 *
 */

public class SEPADirectDebit implements PaymentMethod {
	
	private final String iban;
	private final String bic;
	private final File mandate;
	private final LocalDateTime validUntil;
	
	public SEPADirectDebit(@NotNull String iban, @NotNull String bic, @NotNull File mandate, @NotNull LocalDateTime validUntil) {
		this.iban = iban;
		this.bic = bic;
		this.mandate = mandate;
		this.validUntil = validUntil;
	}
	
	public @NotNull LocalDateTime getValidUntil() {
		return this.validUntil;
	}
	
	public boolean isValid() {
		return this.validUntil.isAfter(LocalDateTime.now());
	}
	
	@Override
	public boolean pay(double amount) {
		System.out.println("Paying " + amount + " via SEPA Direct Debit");
		return true;
	}
}
