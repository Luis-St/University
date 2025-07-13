package printer.synced;

/**
 *
 * @author Luis-St
 *
 */

public class Main {
	
	public static void main (String[] argv) {
		if (argv.length >= 2) {
			SynchronizedDrucker d = new SynchronizedDrucker();
			SynchronizedBenutzer haenselRunnable = new SynchronizedBenutzer(d, argv[0], 5, true);
			SynchronizedBenutzer gretelRunnable = new SynchronizedBenutzer(d, argv[1], 5, false);
			
			Thread haensel = new Thread(haenselRunnable);
			Thread gretel = new Thread(gretelRunnable);
			
			haensel.start();
			gretel.start();
		} else {
			System.out.println("Bitte zwei Dateinamen als Argumente uebergeben!");
		}
	}
}
