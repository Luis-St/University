package printer.synced;

import printer.runnable.RunnableBenutzer;
import printer.runnable.RunnableDrucker;

/**
 *
 * @author Luis-St
 *
 */

public class SynchronizedBenutzer extends RunnableBenutzer {
	
	protected final boolean isHaensel;
	
	public SynchronizedBenutzer(SynchronizedDrucker drucker, String dateiname, int anzahl, boolean isHaensel) {
		super(drucker, dateiname, anzahl);
		this.isHaensel = isHaensel;
	}
	
	@Override
	public void run() {
		if (this.isHaensel) {
			for (int i = 0; i< this.anzahl; i++) {
				this.drucker.druckeDatei(this.dateiname);
			}
			((SynchronizedDrucker) this.drucker).setHaenselDone();
			Thread.yield();
		} else {
			while (!((SynchronizedDrucker) this.drucker).isHaenselDone()) {
				Thread.yield();
			}
			
			for (int i = 0; i< this.anzahl; i++) {
				this.drucker.druckeDatei(this.dateiname);
			}
		}
	}
}
