package printer.runnable;

/**
 *
 * @author Luis-St
 *
 */

public class RunnableBenutzer implements Runnable {
	
	protected final RunnableDrucker drucker;
	protected final String dateiname;
	protected final int anzahl;
	
	public RunnableBenutzer(RunnableDrucker drucker, String dateiname, int anzahl) {
		this.drucker = drucker;
		this.dateiname = dateiname;
		this.anzahl = anzahl;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < this.anzahl; i++) {
			this.drucker.druckeDatei(this.dateiname);
		}
	}
}
