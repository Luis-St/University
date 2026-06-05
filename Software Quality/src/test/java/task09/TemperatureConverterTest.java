package task09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TemperatureConverterTest {
	
	private static final double DELTA = 0.0001;
	private final TemperatureConverter converter = new TemperatureConverter();
	
	@Test
	void celsiusToFahrenheit_Gefrierpunkt() {
		assertEquals(32.0, this.converter.celsiusToFahrenheit(0), DELTA);
	}
	
	@Test
	void celsiusToFahrenheit_Siedepunkt() {
		assertEquals(212.0, this.converter.celsiusToFahrenheit(100), DELTA);
	}
	
	@Test
	void celsiusToFahrenheit_negativ() {
		assertEquals(-40.0, this.converter.celsiusToFahrenheit(-40), DELTA);
	}
	
	@Test
	void fahrenheitToCelsius_Gefrierpunkt() {
		assertEquals(0.0, this.converter.fahrenheitToCelsius(32), DELTA);
	}
	
	@Test
	void fahrenheitToCelsius_Siedepunkt() {
		assertEquals(100.0, this.converter.fahrenheitToCelsius(212), DELTA);
	}
	
	@Test
	void fahrenheitToCelsius_negativ() {
		assertEquals(-40.0, this.converter.fahrenheitToCelsius(-40), DELTA);
	}
	
	@Test
	void hinUndRueckrechnungIstKonsistent() {
		double celsius = 37.5;
		double fahrenheit = this.converter.celsiusToFahrenheit(celsius);
		assertEquals(celsius, this.converter.fahrenheitToCelsius(fahrenheit), DELTA);
	}
}
