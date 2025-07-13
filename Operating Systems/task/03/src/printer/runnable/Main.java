package printer.runnable;

/**
 *
 * @author Luis-St
 *
 */

@SuppressWarnings("DuplicatedCode")
public class Main {
	
	public static void main(String[] argv) {
		if (argv.length >= 2) {
			RunnableDrucker d = new RunnableDrucker();
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
