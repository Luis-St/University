package gdl.sose26.nachhilfe;

import static prog.Picture.*;

public class Aufgabe16 {
	
	public static void main(String[] args) {
		
		int[][] p = loadFromFileSystem("./MyPicture.jpg");
		
		//rechteck(p);
		f2(p);
		show(p);
	}
	
	public static void f(int[][] p) {
		
		int breite = p.length;
		int hoehe = p[0].length;
		
		int mittelpunktX = 250;
		int mittelpunktY = 120;
		
		int radiusA = 70;
		int radiusA2 = radiusA * radiusA;
		int radiusI = 50;
		int radiusI2 = radiusI * radiusI;
		
		for (int x = 0; x < breite; x++) {
			for (int y = 0; y < hoehe; y++) {
				
				// a^2 + b^2 = c^2
				
				int dx = x - mittelpunktX; // a
				int dy = y - mittelpunktY; // b
				
				int dx2 = dx * dx; // a^2
				int dy2 = dy * dy; // b^2
				
				int distanz2 = dx2 + dy2; // c^2
				
				boolean istInnen = distanz2 <= radiusI2;
				boolean istAussen = distanz2 <= radiusA2;
				
				if (istAussen && !istInnen) {
					
					p[x][y] = 0;
					
				}
				
			}
		}
	}
	
	public static void f2(int[][] p) {
		
		int breite = p.length;
		int hoehe = p[0].length;
		
		int mittelpunktX = 250;
		int mittelpunktY = 120;
		
		int radiusA = 70;
		int radiusA2 = radiusA * radiusA;
		int radiusI = 50 + 10;
		int radiusI2 = radiusI * radiusI;
		int radiusKleinA = 25;
		int radiusKleinA2 = radiusKleinA * radiusKleinA;
		int radiusKleinI = 15;
		int radiusKleinI2 = radiusKleinI * radiusKleinI;
		
		for (int x = 0; x < breite; x++) {
			for (int y = 0; y < hoehe; y++) {
				
				// a^2 + b^2 = c^2
				
				int dx = x - mittelpunktX; // a
				int dy = y - mittelpunktY; // b
				
				int dx2 = dx * dx; // a^2
				int dy2 = dy * dy; // b^2
				
				int distanz2 = dx2 + dy2; // c^2
				
				boolean istInnen = distanz2 <= radiusI2;
				boolean istAussen = distanz2 <= radiusA2;
				
				boolean istInnenKlein = distanz2 <= radiusKleinI2;
				boolean istAussenKlein = distanz2 <= radiusKleinA2;
				
				if (istAussen && !istInnen) {
					
					p[x][y] = 0;
					
				}
				
				// if (istAussenKlein && !istInnenKlein) {
				if (istAussenKlein) {
					if (!istInnenKlein) {
						
						p[x][y] = 0;
						
					}
				}
				
				// if ((istAussen && !istInnen) || (istAussenKlein && !istInnenKlein)) {
				
				if (istInnenKlein) {
					
					p[x][y] = 0x0000FF;
					
				}
				
			}
		}
	}
	
	public static void rechteck(int[][] p) {
		
		int breite = p.length;
		int hoehe = p[0].length;
		
		int mittelpunktX = 250;
		int mittelpunktY = 120;
		
		int radiusA = 70;
		int radiusI = 50;
		
		int lininienbreite = 20;
		
		int linksAussen = mittelpunktX - radiusA;
		int rechtsAussen = mittelpunktX + radiusA;
		int obenAussen = mittelpunktY - radiusA;
		int untenAussen = mittelpunktY + radiusA;
		
		int linksInnen = mittelpunktX - radiusI;
		int rechtsInnen = mittelpunktX + radiusI;
		int obenInnen = mittelpunktY - radiusI;
		int untenInnen = mittelpunktY + radiusI;
		
		for (int x = 0; x < p.length; x++) {
			for (int y = 0; y < p[0].length; y++) {
				
				boolean aussen = x >= linksAussen && x <= rechtsAussen && y >= obenAussen && y <= untenAussen;
				boolean innen = x >= linksInnen && x <= rechtsInnen && y >= obenInnen && y <= untenInnen;
				
				if (aussen && !innen) {
					p[x][y] = 0;
				}
				
			}
		}
		
	}
}
