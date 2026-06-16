package gdl.sose26.ka;

public class Aufgabe13 {
	
	public static class Listenelement {
		
		public int wert;
		public Listenelement nachfolger;
		
		public Listenelement(int wert) {
			this.wert = wert;
			nachfolger = null;
		}
		
		public void hinzufuegenRechts(int w) {
			if (nachfolger == null) {
				nachfolger = new Listenelement(w);
			} else {
				nachfolger.hinzufuegenRechts(w);
			}
		}
		
		public void print() {
			if (nachfolger == null) {
				System.out.println(wert);
			} else {
				System.out.print(wert + ", ");
				nachfolger.print();
			}
		}
		
		public void einschieben(int r) {
			Listenelement k = nachfolger;
			nachfolger = new Listenelement(r);
			nachfolger.nachfolger = k;
		}
		
		public void abschneidenNach(int n) {
			if (n == 0) {
				nachfolger = null;
			} else if (nachfolger != null) {
				nachfolger.abschneidenNach(n - 1);
			}
		}
		
	}
	
	public static void main(String[] args) {
		Listenelement x1 = new Listenelement(1);
		x1.hinzufuegenRechts(2);
		x1.hinzufuegenRechts(3);
		x1.hinzufuegenRechts(4);
		x1.hinzufuegenRechts(3);
		x1.print();
		x1.abschneidenNach(2);
		x1.print();
		x1.einschieben(7);
		x1.print();
		x1.einschieben(8);
		x1.print();
		x1.nachfolger.nachfolger = x1;
		x1.print();
	}
	
}
