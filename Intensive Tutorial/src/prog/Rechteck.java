package prog;

import java.util.Scanner;

/**
 Erstelle eine Klasse Rechteck:
 a) Mit zwei Attributen für breite und hoehe
 b) Erstelle einen Konstruktor an den ich breite und hoehe übergeben kann (beispiel verwendung: new Rechteck(10, 5))
 c) Erstelle eine Methode die die Flaeche des Rechtecks berechnet (berechneFlaeche, keine Parameter, Rückgabe double)
 d) Erstelle eine Methode die den Umfang des Rechtecks berechnet (berechneUmfang, keine Parameter, Rückgabe double)
 e) Erstelle eine Methode die ein anderes Rechteck als Parameter erhält und zurückgibt,
 ob das aktuelle Rechteck eine größere Flaeche besitzt als das übergebene (istGroesserAls, Parameter Rechteck, Rückgabe boolean).
 Tipp: Du kannst hierfür die bereits erstellte Methode berechneFlaeche wiederverwenden (beispiel verwendung: rechteckA.istGroesserAls(rechteckB))
 */

public class Rechteck {
	
	// a)
	public double breite;
	public double hoehe;
	
	// b)
	public /*Kein Rückgabewert*/ Rechteck(double b, double h) {
		hoehe = h;
		breite = b;
	}
	
	// c)
	public double berechneFlaeche() {
		double flaeche = hoehe * breite;
		return flaeche;
	}
	
	// d)
	public double berechneUmfang() {
		double umfang = (2 * breite) + (2 * hoehe);
		return umfang;
	}
	
	// e)
	public boolean istGroesserAls(Rechteck anderes) {
		double eigeneFlaeche = berechneFlaeche();
		double andereFlaeche = anderes.berechneFlaeche();
		
		if (eigeneFlaeche > andereFlaeche) {
			return true;
		} else {
			return false;
		}
		
		// Möglichkeit 2:
		/*
		if (eigeneFlaeche > andereFlaeche) {
			return true;
		}
		return false;
		*/
		
		// Möglichkeit 3:
		// return eigeneFlaeche > andereFlaeche;
	}
	
	// Dient zum testen, nicht relevant für die Klausur
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Gib die Rechteck breite ein: ");
		double b = scanner.nextDouble();
		System.out.println("Gib die Rechteck höhe ein: ");
		double h = scanner.nextDouble();
		
		Rechteck rechteck1 = new Rechteck(b, h);
		
		double flaecheR1 = rechteck1.berechneFlaeche();
		System.out.println(flaecheR1);
		
		double umfangR1 = rechteck1.berechneUmfang();
		System.out.println(umfangR1);
		
		boolean groesser = rechteck1.istGroesserAls(new Rechteck(20.0, 20.0));
		System.out.println(groesser);
		
		scanner.close();
	}
	
}
