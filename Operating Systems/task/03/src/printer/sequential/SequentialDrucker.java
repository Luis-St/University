package printer.sequential;

import printer.runnable.RunnableDrucker;

/**
 *
 * @author Luis-St
 *
 */

public class SequentialDrucker extends RunnableDrucker {
	
	private volatile boolean haenselDone;
	
	@Override
	public synchronized void druckeDatei(String dateiname) {
		super.druckeDatei(dateiname);
	}
	
	boolean isHaenselDone() {
		return this.haenselDone;
	}
	
	void setHaenselDone() {
		this.haenselDone = true;
	}
}
