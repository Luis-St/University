package gdl.wise2526.nachhilfe;

public class Aufgabe2 {
	
	public static void main(String[] args) {
		Produkt p1 = new Produkt("Schraube", 0.02);
		Produkt p2 = new Produkt("Gehäuse", 1);
		Produkt p3 = new Produkt("Motor", 20);
		p1.bestandteilVon = p3;
		p2.bestandteilVon = p3;
		p1.bestandteilVon.masse = 3;
		System.out.println(p3.masse);
		System.out.println(p2.masse);
		System.out.println(p2.bestandteilVon.masse);
		System.out.println(p1.bestandteilVon.bestandteilVon);
		p3.bestandteilVon = new Produkt("Auto", 500);
		System.out.println(p1.bestandteilVon.bestandteilVon.bezeichnung);
		p3 = null;
		p2 = null;
		p1.bestandteilVon.bestandteilVon = null;
		p1.bestandteilVon = p1;
		System.out.println(p1.bestandteilVon.bestandteilVon.masse);
  		System.out.println(p2.masse);
	}
	
}

class Produkt {
	
	public String bezeichnung;
	public double masse;
	public Produkt bestandteilVon;
	
	public Produkt(String b, double m) {
		bezeichnung = b;
		masse = m;
		bestandteilVon = null;
	}
}
