package b3;

public class Main {
	
	static class TicketAnbieter {
		private int VerfuegbareTickets;
		
		TicketAnbieter(int n) {
			this.VerfuegbareTickets = n;
		}
		
		synchronized int TicketVerkaufWennVerfuegbar() {
			if (this.VerfuegbareTickets > 0) {
				int nr = this.VerfuegbareTickets;
				this.VerfuegbareTickets = this.VerfuegbareTickets - 1;
				return nr;
			}
			return -1;
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
			} catch (InterruptedException ignored) {}
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
