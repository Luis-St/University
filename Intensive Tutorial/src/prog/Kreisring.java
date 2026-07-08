package prog;

public class Kreisring {
	
	public double r1;
	public double r2;
	public Punkt mittelpunkt; // Mittelpunkt wird nicht benötigt (hätte eventuell auch weggelassen werden können)
	
	/*// Konstruktor 1
	public Kreisring(
		double radius1, // = 7
		double radius2, // = 10.5
		Punkt mp // = (4, 3)
	) {
		r1 = radius1;
		r2 = radius2;
		mittelpunkt = mp;
	}*/
	
	// Konstruktor 2
	public Kreisring(
		double radius1, // = 7
		double radius2, // = 10.5
		int xMitte,
		int yMitte
	) {
		Punkt mp = new Punkt();
		mp.xKoordinate = xMitte;
		mp.yKoordinate = yMitte;
		
		r1 = radius1;
		r2 = radius2;
		mittelpunkt = mp;
	}
	
	// Dient zum testen, nicht relevant für die Klausur
	public static void main(String[] args) {
		/* // Konstruktor 1, danach abgeändert (siehe unten)
		Punkt mittelpunkt = new Punkt();
		mittelpunkt.xKoordinate = 4;
		mittelpunkt.yKoordinate = 3;
		
		Kreisring kr = new Kreisring(7, 10.5, mittelpunkt);*/
		
		// Konstruktor 2
		Kreisring kr = new Kreisring(7, 10.5, 4, 3);
		
		System.out.println(kr.mittelpunkt);
		System.out.println(kr.berechneFlaeche());
		
		// Zusatz
		kr.verschiebe(-3, -2);
		System.out.println(kr.mittelpunkt);
	}
	
	// Zusatz (Methoden, nicht teil der Aufgabe)
	
	// A = pi * r^2
	// Math.PI ~ 3.14
	// Agesamt = Ar2 - Ar1
	// Mehthode ohne Parameter mit Rückgabe
	public double berechneFlaeche() {
		double aR2 = Math.PI * r2 * r2;
		double aR1 = Math.PI * r1 * r1;
		double aGesamt = aR2 - aR1;
		
		return aGesamt;
	}
	
	public void verschiebe(int xMitte, int yMitte) {
		Punkt mp = new Punkt();
		mp.xKoordinate = xMitte;
		mp.yKoordinate = yMitte;
		
		mittelpunkt = mp;
	}
}
