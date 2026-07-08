package prog;

public class Strecke {
	
	public Punkt anfang;
	public Punkt ende;
	
	public Strecke(Punkt p1, Punkt p2) {
		
		if (p1.xKoordinate <= p2.xKoordinate) {
			
			anfang = p1;
			ende = p2;
			
		} else {
			
			anfang = p2;
			ende = p1;
			
		}
	}
	
	public static void main(String[] args) {
		Punkt a = new Punkt();
		a.xKoordinate = 4;
		a.yKoordinate = 1;
		
		Punkt b = new Punkt();
		b.xKoordinate = 1;
		b.yKoordinate = 3;
		
		Strecke s = new Strecke(a, b); // b liegt links -> Anfang = b
		System.out.println(s.anfang.xKoordinate); // 1
		System.out.println("Laenge: " + s.berechneLaenge()); // 3.6055...
		System.out.println("Steigung: " + s.berechneSteigung()); // -0.6667
	}
	
	// a^2 + b^2 = c^2
	public double berechneLaenge() {
		int dx = ende.xKoordinate - anfang.xKoordinate;
		int dy = ende.yKoordinate - anfang.yKoordinate;
		
		double dz = Math.sqrt(dx * dx + Math.pow(dy, 2));
		return dz;
	}
	
	public double berechneSteigung() {
		double sx = ende.xKoordinate - anfang.xKoordinate;
		double sy = ende.yKoordinate - anfang.yKoordinate;
		
		double s = sy / sx;
		return s;
	}
}
