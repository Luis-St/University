package task09;

public class TemperatureConverter {
	
	// Konvertiert Celsius in Fahrenheit
	public double celsiusToFahrenheit(double celsius) {
		return (celsius * 9 / 5) + 32;
	}
	
	// Konvertiert Fahrenheit in Celsius
	public double fahrenheitToCelsius(double fahrenheit) {
		return (fahrenheit - 32) * 5 / 9;
	}
}
