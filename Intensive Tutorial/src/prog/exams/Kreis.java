package prog.exams;

/**
 * Probeklausur 3, Aufgabe 3: Klasse Kreis
 */
public class Kreis {
	
	public Punkt mittelpunkt;
	public double radius;
	
	public Kreis(Punkt mp, double r) {
		this.mittelpunkt = mp;
		
		if (r <= 0) {
			this.radius = 1;
		} else {
			this.radius = r;
		}
	}
	
	public double berechneFlaeche() {
		return Math.PI * this.radius * this.radius;
	}
	
	public boolean enthaeltPunkt(Punkt p) {
		double abstand = this.mittelpunkt.berechneAbstandZuPunkt(p);
		
		return abstand <= this.radius;
	}
	
	// Dient zum Testen, nicht Teil der Aufgabe
	public static void main(String[] args) {
		
		Punkt mp = new Punkt();
		mp.xKoordinate = 2;
		mp.yKoordinate = 3;
		
		Kreis k = new Kreis(mp, 5);
		System.out.println("Flaeche: " + k.berechneFlaeche());
		
		Punkt p = new Punkt();
		p.xKoordinate = 4;
		p.yKoordinate = 4;
		
		if (k.enthaeltPunkt(p)) {
			System.out.println("p liegt im Kreis");
		}
	}
}
