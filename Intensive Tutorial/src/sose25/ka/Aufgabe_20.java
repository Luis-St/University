package sose25.ka;

/**
 *
 * @author Luis-St
 *
 */

public class Aufgabe_20 {
	
	public static class Listenelement {
		
		public int wert;
		public Listenelement nachfolger;
		
		public Listenelement(int wert) {
			this.wert = wert;
			nachfolger = null;
		}
		
	}
	
	public static void main(String[] args) {
		Listenelement liste1 = new Listenelement(1);
		liste1.nachfolger = new Listenelement(2);
		liste1.nachfolger.nachfolger = new Listenelement(3);
		
		Listenelement liste2 = new Listenelement(4);
		liste2.nachfolger = new Listenelement(5);
		liste2.nachfolger.nachfolger = new Listenelement(6);
		
		concat(liste1, liste2);
		
		System.out.println();
	}
	
	public static void concat(Listenelement a, Listenelement b) {
	
		Listenelement lastElementOfA = a;
		
		while (lastElementOfA.nachfolger != null) {
			
			lastElementOfA = lastElementOfA.nachfolger;
			
		}
		
		lastElementOfA.nachfolger = b;
	}
	
}
