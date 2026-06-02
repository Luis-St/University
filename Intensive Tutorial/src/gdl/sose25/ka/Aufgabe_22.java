package gdl.sose25.ka;

/**
 *
 * @author Luis-St
 *
 */

public class Aufgabe_22 {
	
	public static class Listenelement {
		
		public int wert;
		public Listenelement nachfolger;
		
		public Listenelement(int wert) {
			this.wert = wert;
			nachfolger = null;
		}
		
		public int count(int v) {
			
			int count = 0;
			Listenelement element = this;
			
			while (element != null) {
				
				if (element.wert == v) {
					
					count = count + 1;
					
				}
				
				element = element.nachfolger;
				
			}
			
			return count;
		}
		
	}
	
	public static void main(String[] args) {
		Listenelement liste1 = new Listenelement(1);
		liste1.nachfolger = new Listenelement(2);
		liste1.nachfolger.nachfolger = new Listenelement(3);
		liste1.nachfolger.nachfolger.nachfolger = new Listenelement(4);
		liste1.nachfolger.nachfolger.nachfolger.nachfolger = new Listenelement(3);
		liste1.nachfolger.nachfolger.nachfolger.nachfolger.nachfolger = new Listenelement(2);
		liste1.nachfolger.nachfolger.nachfolger.nachfolger.nachfolger.nachfolger = new Listenelement(1);
		
		System.out.println(liste1.count(1));
		System.out.println(liste1.count(2));
		System.out.println(liste1.count(3));
		System.out.println(liste1.count(4));
		System.out.println(liste1.count(5));
	}
	
}
