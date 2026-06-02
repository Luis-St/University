package gdl.sose26.nachhilfe;

import static prog.Picture.*;

public class KlausurAufgabe18 {
	
	public static void main(String[] args) {
		
		int[][] p = loadFromFileSystem("./MyPicture.jpg");
		f(p);
		show(p);
	}
	
	public static void f(int[][] p) {
		
		int hoehe = p.length;
		int breite = p[0].length;
		
		int mittelpunktX = 150;
		int mittelpunktY = 130;
		int radius = 50; // c
		
		int verschiebungX = 110;
		int verschiebungY = 70;
		
		int startX = 100;
		int startY = 80;
		
		int zielX = 200;
		int zielY = 180;
		
		for (int x = startX; x < /*zielX*/ startX + (2 * radius); x++) {
			
			for (int y = startY; y < /*zielY*/ startY + (2 * radius); y++) {
				
				// a^2 + b^2 = c^2
				
				int dx = x - mittelpunktX; // a
				int dy = y - mittelpunktY; // b
				
				int dx2 = dx * dx; // a^2
				int dy2 = dy * dy; // b^2
				
				int distanz = dx2 + dy2; // a^2 + b^2 (distanz)
				
				// distanz <= c^2
				if (distanz <= radius * radius) {
					
					p[x + verschiebungX][y + verschiebungY] = p[x][y];
				
				} else {
					
					// Ausserhalb des Kreises aber noch im Rechteck
					
				}
				
			}
			
		}
		
	}
}
