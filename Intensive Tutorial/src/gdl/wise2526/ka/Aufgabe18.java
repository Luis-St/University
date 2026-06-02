package gdl.wise2526.ka;

import static prog.Picture.*;

public class Aufgabe18 {
	
	public static void main(String[] args) {
		int[][] image = loadFromFileSystem("./MyPicture.jpg");
		
		f(image);
	}
	
	public static void f(int[][] p) {
		
		int breite = p.length;
		int hoehe = p[0].length;
		
		int xStartpunkt = 100;
		int xEndpunkt = 200;
		
		int yStartpunkt = 80;
		int yEndpunkt = 180;
		
		int mittelpunktX = 150;
		int mittelpunktY = 130;
		
		int r = 50;
		int r2 = r * r;
		
		for (int x = xStartpunkt; x < xEndpunkt; x++) {
			
			for (int y = yStartpunkt; y < yEndpunkt; y++) {
				
				int dx = x - mittelpunktX;
				int dy = y - mittelpunktY;
				
				int dx2 = dx * dx;
				int dy2 = dy * dy;
				
				if (dx2 + dy2 <= r2) {
					
					int xZiel = x + 110;
					int yZiel = y + 70;
					
					p[xZiel][yZiel] = p[x][y];
					
				}
				
			}
			
		}
		
		show(p);
	}
	
}
