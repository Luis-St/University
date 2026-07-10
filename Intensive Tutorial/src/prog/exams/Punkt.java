package prog.exams;

/**
 * Punkt-Klasse für die Aufgaben 3 der Probeklausuren 1, 2 und 4.
 * (Eigene Fassung mit double-Koordinaten, damit alle Aufgaben zusammenpassen.)
 */
public class Punkt {
	
	public double xKoordinate;
	public double yKoordinate;
	
	public Punkt() {
		this.xKoordinate = 0.0;
		this.yKoordinate = 0.0;
	}
	
	public Punkt(double r, double alpha) {
		double alphaImBogenmass = alpha * Math.PI / 180.0;
		
		this.xKoordinate = r * Math.cos(alphaImBogenmass);
		this.yKoordinate = r * Math.sin(alphaImBogenmass);
	}
	
	
	// Dient zum Testen, nicht Teil der Aufgabe
	public static void main(String[] args) {
		
		// Probeklausur 1, Aufgabe 3
		Punkt p1 = new Punkt();
		p1.xKoordinate = 1.0;
		p1.yKoordinate = 1.5;
		
		Punkt mp = new Punkt();
		mp.xKoordinate = 5.6;
		mp.yKoordinate = -4.3;
		
		int i = p1.liegtInnerhalb(3.0, mp);
		
		if (i == 1) {
			System.out.println("p1 liegt innerhalb");
		} else if (i == 0) {
			System.out.println("p1 liegt ausserhalb");
		} else {
			System.out.println("ungueltiger Radius");
		}
		
		// Probeklausur 2, Aufgabe 3
		Punkt pol1 = new Punkt(4, 30);
		Punkt pol2 = new Punkt(7.5, 0);
		Punkt pol3 = new Punkt(3, 270);
		
		System.out.println(pol1 + "  Abstand: " + pol1.berechneAbstandVomNullpunkt());
		System.out.println(pol2);
		System.out.println(pol3);
		
		// Probeklausur 4, Aufgabe 3
		Punkt p = new Punkt();
		p.xKoordinate = 3;
		p.yKoordinate = 4;
		
		Punkt gedreht = p.rotiere(90);
		Punkt versetzt = p.verschiebeUm(5, 0);
		
		System.out.println(gedreht.xKoordinate + " / " + gedreht.yKoordinate);
		System.out.println(versetzt.xKoordinate + " / " + versetzt.yKoordinate);
	}
	
	public double berechneAbstandZuPunkt(Punkt p) {
		double dx = p.xKoordinate - this.xKoordinate;
		double dy = p.yKoordinate - this.yKoordinate;
		
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public double berechneAbstandVomNullpunkt() {
		Punkt nullpunkt = new Punkt();
		
		return this.berechneAbstandZuPunkt(nullpunkt);
	}
	
	// ---- Probeklausur 4, Aufgabe 3: drehen und verschieben ----
	// Beide Methoden geben einen NEUEN Punkt zurück,
	// die aufrufende Instanz bleibt unverändert.
	
	// ---- Probeklausur 1, Aufgabe 3: Punkt im Kreis ----
	//  1 = innerhalb oder auf dem Umfang
	//  0 = außerhalb
	// -1 = ungültiger Kreis (Radius <= 0)
	public int liegtInnerhalb(double radius, Punkt mittelpunkt) {
		
		if (radius <= 0) {
			return -1;
		}
		
		// Wiederverwendung der bekannten Methode (gibt den Zusatzpunkt)
		double abstand = this.berechneAbstandZuPunkt(mittelpunkt);
		
		if (abstand <= radius) {
			return 1;
		} else {
			return 0;
		}
	}
	
	// xn = x * cos(beta) - y * sin(beta)
	// yn = x * sin(beta) + y * cos(beta)
	public Punkt rotiere(double winkel) {
		double beta = winkel * Math.PI / 180.0;
		
		Punkt neu = new Punkt();
		neu.xKoordinate = this.xKoordinate * Math.cos(beta) - this.yKoordinate * Math.sin(beta);
		neu.yKoordinate = this.xKoordinate * Math.sin(beta) + this.yKoordinate * Math.cos(beta);
		
		return neu;
	}
	
	// xn = x + strecke * cos(richtung)
	// yn = y + strecke * sin(richtung)
	public Punkt verschiebeUm(double strecke, double richtung) {
		double r = richtung * Math.PI / 180.0;
		
		Punkt neu = new Punkt();
		neu.xKoordinate = this.xKoordinate + strecke * Math.cos(r);
		neu.yKoordinate = this.yKoordinate + strecke * Math.sin(r);
		
		return neu;
	}
	
	@Override
	public String toString() {
		return "(" + this.xKoordinate + ", " + this.yKoordinate + ")";
	}
}
