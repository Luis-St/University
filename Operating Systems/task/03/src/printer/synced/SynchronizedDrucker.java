package printer.synced;

import printer.runnable.RunnableDrucker;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Luis-St
 *
 */

public class SynchronizedDrucker extends RunnableDrucker {
	
	private volatile boolean haenselDone = false;
	
	@Override
	public synchronized void druckeDatei(String dateiname) {
		super.druckeDatei(dateiname);
	}
	
	public boolean isHaenselDone() {
		return this.haenselDone;
	}
	
	public void setHaenselDone() {
		this.haenselDone = true;
	}
}
