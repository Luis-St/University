package prog.exams;

/**
 * Probeklausur 5, Aufgabe 3: Klasse Dreieck
 */
public class Dreieck {
	
	public Punkt p1, p2, p3;
	
	public Dreieck(Punkt a, Punkt b, Punkt c) {
		this.p1 = a;
		this.p2 = b;
		this.p3 = c;
	}
	
	public double berechneFlaeche() {
		double dx12 = this.p2.xKoordinate - this.p1.xKoordinate;
		double dy12 = this.p2.yKoordinate - this.p1.yKoordinate;
		double dx13 = this.p3.xKoordinate - this.p1.xKoordinate;
		double dy13 = this.p3.yKoordinate - this.p1.yKoordinate;
		
		return 0.5 * Math.abs(dx12 * dy13 - dx13 * dy12);
	}
	
	public double berechneUmfang() {
		double a = this.p1.berechneAbstandZuPunkt(this.p2);
		double b = this.p2.berechneAbstandZuPunkt(this.p3);
		double c = this.p3.berechneAbstandZuPunkt(this.p1);
		
		return a + b + c;
	}
	
	public boolean istRechtwinklig() {
		double epsilon = 1.0e-6;
		
		double a = this.p1.berechneAbstandZuPunkt(this.p2);
		double b = this.p2.berechneAbstandZuPunkt(this.p3);
		double c = this.p3.berechneAbstandZuPunkt(this.p1);
		
		if (Math.abs(a * a + b * b - c * c) < epsilon) {
			return true;
		}
		
		if (Math.abs(a * a + c * c - b * b) < epsilon) {
			return true;
		}
		
		return Math.abs(b * b + c * c - a * a) < epsilon;
	}
	
	// Dient zum Testen, nicht Teil der Aufgabe
	public static void main(String[] args) {
		
		Punkt a = new Punkt();
		a.xKoordinate = 0;
		a.yKoordinate = 0;
		
		Punkt b = new Punkt();
		b.xKoordinate = 4;
		b.yKoordinate = 0;
		
		Punkt c = new Punkt();
		c.xKoordinate = 0;
		c.yKoordinate = 3;
		
		Dreieck d = new Dreieck(a, b, c);
		System.out.println("Flaeche: " + d.berechneFlaeche()); // 6.0
		System.out.println("Umfang:  " + d.berechneUmfang());  // 12.0
		
		if (d.istRechtwinklig()) {
			System.out.println("Das Dreieck ist rechtwinklig");
		}
	}
}
