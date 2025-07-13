package printer.runnable;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Luis-St
 *
 */

public class RunnableDrucker {
	
	public void druckeDatei(String dateiname) {
		try (BufferedReader in = new BufferedReader(new FileReader(dateiname))) {
			String line = in.readLine();
			while (line != null) {
				System.out.println(line);
				line = in.readLine();
			}
		} catch (Exception e) {
			System.out.println("Eine Ausnahme ist aufgetreten.");
		}
	}
}
