package b4;

import java.util.concurrent.atomic.AtomicInteger;

class Test {
	
	static class TicketAnbieter {
		
		private final AtomicInteger VerfuegbareTickets;
		
		TicketAnbieter(int n) {
			this.VerfuegbareTickets = new AtomicInteger(n);
		}
		
		int TicketVerkaufWennVerfuegbar() {
			int aktuellerWert;
			int neuerWert;
			
			do {
				aktuellerWert = this.VerfuegbareTickets.get();
				if (aktuellerWert <= 0) {
					return -1;
				}
				neuerWert = aktuellerWert - 1;
			} while (!this.VerfuegbareTickets.compareAndSet(aktuellerWert, neuerWert));
			
			return aktuellerWert;
		}
	}
	
	static class Reisebuero extends Thread {
		
		private final String name;
		private final TicketAnbieter anbieter;
		
		Reisebuero(String name, TicketAnbieter anbieter) {
			this.name = name;
			this.anbieter = anbieter;
		}
		
		void warteAufKunde() {
			try {
				Thread.sleep(Math.round(1000 * Math.random()));
			} catch (InterruptedException e) {}
		}
		
		@Override
		public void run() {
			this.warteAufKunde();
			int nr;
			while ((nr = this.anbieter.TicketVerkaufWennVerfuegbar()) > 0) {
				System.out.println(this.name + " verkauft Ticket " + nr);
				this.warteAufKunde();
			}
			System.out.println(this.name + " hat keine Tickets mehr zum Verkauf");
		}
	}
	
	public static void main(String[] argv) {
		TicketAnbieter ta = new TicketAnbieter(4);
		new Reisebuero("Reiseland", ta).start();
		new Reisebuero("Happy Travel", ta).start();
	}
}
