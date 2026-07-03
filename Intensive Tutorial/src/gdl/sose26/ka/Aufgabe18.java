package gdl.sose26.ka;

import static prog.Picture.*;

public class Aufgabe18 {
	
	public static void main(String[] args) {
		int[][] p = loadFromFileSystem("./MyPicture.jpg");
		
		f(p);
		show(p);
	}
	
	public static void f(int[][] p) {
		
		int r = 50;
		int r2 = r * r;
		
		int mittelpunktX = 150;
		int mittelpunktY = 130;
		
		int verschiebungX = 110;
		int verschiebungY = 70;
		
		// a^2 + b^2 = c^2
		for (int x = 100; x < 200; x++) {
			
			for (int y = 80; y < 180; y++) {
				
				int dx = mittelpunktX - x; // a
				int dy = mittelpunktY - y; // b
				
				if (dx * dx + dy * dy <= r2) {
				
					p[x + verschiebungX][y + verschiebungY] = p[x][y];
				
				}
			}
		}
	}
	
}
