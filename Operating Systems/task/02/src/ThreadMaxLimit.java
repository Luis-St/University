/**
 *
 * @author Luis-St
 *
 */

class ThreadMaxLimit extends Thread {
	
	/**
	 * Tested on two systems:
	 * Both of them run over half an hour without any problems.
	 * I guess there is no limit for the number of threads.
	 * It could be limited by the system resources.
	 */
	
	ThreadMaxLimit() {
		this.start();
	}
	
	@Override
	public void run() {
		ThreadMaxLimit.main(null);
	}
	
	public static void main(String[] args) {
		new ThreadMaxLimit();
	}
}
