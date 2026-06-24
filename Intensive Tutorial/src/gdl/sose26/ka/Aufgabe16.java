package gdl.sose26.ka;

import static prog.Picture.*;
import static prog.Picture.show;

public class Aufgabe16 {
	
	public static void main(String[] args) {
		int[][] p = loadFromFileSystem("./MyPicture.jpg");
		
		f(p);
		show(p);
	}
	
	public static void f(int[][] p) {
		
		int mittelPunktX = 250;
		int mittelPunktY = 120;
		
		int aussenRadius = 70;
		int aR2 = aussenRadius * aussenRadius;
		
		int innererRadius = 50;
		int iR2 = innererRadius * innererRadius;
		
		for (int x = 0; x < p.length; x++) {
			
			for (int y = 0; y < p[0].length; y++) {
				
				// (x, y)
				
				int dx = x - mittelPunktX;
				int dy = y - mittelPunktY;
				
				int dx2 = dx * dx;
				int dy2 = dy * dy;
				
				int distanz2 = dx2 + dy2;
				
				// if (distanz2 <= aR2 && distanz2 > iR2) {
				if (distanz2 <= aR2) {
					
					if (distanz2 > iR2) {
						
						p[x][y] = 0;
						
					}
				}
			}
			
		}
	}
	
}
