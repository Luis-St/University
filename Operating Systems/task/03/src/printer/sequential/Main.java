package printer.sequential;

import printer.runnable.RunnableBenutzer;

/**
 *
 * @author Luis-St
 *
 */

@SuppressWarnings("DuplicatedCode")
public class Main {
	
	public static void main(String[] argv) {
		if (argv.length >= 2) {
			SequentialDrucker d = new SequentialDrucker();
			RunnableBenutzer haenselRunnable = new RunnableBenutzer(d, argv[0], 5);
			RunnableBenutzer gretelRunnable = new RunnableBenutzer(d, argv[1], 5);
			
			Thread haensel = new Thread(haenselRunnable);
			Thread gretel = new Thread(gretelRunnable);
			
			haensel.start();
			gretel.start();
		} else {
			System.out.println("Bitte zwei Dateinamen als Argumente uebergeben!");
		}
	}
}
