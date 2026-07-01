package gdl.sose26.ka;

import static prog.Picture.*;

public class WiSe25Aufgabe4 {
	
	public static void main(String[] args) {
		
		int[][] i = loadFromFileSystem("./MyPicture.jpg");
		
		int[][] image = new int[300][300];
		
		for (int x = 0; x < image.length; x++) {
			
			System.arraycopy(i[x], 0, image[x], 0, image[0].length);
			
		}
		
		f(image);
		show(image);
	}
	
	public static void f(int[][] p) {
		// b)
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				
				if (x % 2 == 0 && y % 2 == 0) {
					
					zeichneViereck(p, x * 50, y * 50, 0xFF00FF /*Farbe Lila*/);
					
				}
				
				if (x % 2 == 1 && y % 2 == 1) {
					
					zeichneViereck(p, x * 50, y * 50, 0xFF00FF /*Farbe Lila*/);
					
				}
				
			}
		}
		
		// a)
		for (int i = 0; i < 6; i++) {
			
			int x = i * 50;
			int y = i * 50;
			
			zeichneViereck(p, x, y, 0x00FF00 /* Farbe Grün  */);
		}
	}
	
	public static void zeichneViereck(int[][] p, int xPos, int yPos, int color) {
		
		for (int x = xPos; x < xPos + 50; x++) {
			for (int y = yPos; y < yPos + 50; y++) {
				
				p[x][y] = color;
				
			}
		}
		
		// Alternative, auch möglich für das zeichnen des Vierecks
		/*for (int x = 0; x < 50; x++) {
			for (int y = 0; y < 50; y++) {
				
				p[xPos + x][yPos + y] = color;
				
			}
		}*/
		
	}
	
}
