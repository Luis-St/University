package sose26.nachhilfe;

import static prog.Picture.*;

public class KlausurAufgabe4 {
	
	public static void main(String[] args) {
		
		int[][] i = loadFromFileSystem("./MyPicture.jpg");
		
		int[][] image = new int[300][300];
		
		for (int x = 0; x < image.length; x++) {
			
			System.arraycopy(i[x], 0, image[x], 0, image[0].length);
			
		}
		
		fB2(image);
		show(image);
	}
	
	public static void fB1(int[][] p) {
		
		for (int x = 0; x < 6; x++) {
			
			for (int y = 0; y < 6; y++) {
				
				if (x % 2 == 0 && y % 2 == 0) {
					
					zeichneRechteck(
						p, x * 50, y * 50, 50, 50, 0x0000FF
					);
					
				} else if (x % 2 == 1 && y % 2 == 1) {
					
					zeichneRechteck(
						p, x * 50, y * 50, 50, 50, 0x0000FF
					);
					
				}
				
			}
			
		}
	
	
	
	}
	
	public static void fB2(int[][] p) {
		
		// Erste Zeile
		zR(p, 0, 0);
		zR(p, 100, 0);
		zR(p, 200, 0);
		
		// Zweite Zeile
		zR(p, 50, 50);
		zR(p, 150, 50);
		zR(p, 250, 50);
		
		// Dritte Zeile
		zR(p, 0, 100);
		zR(p, 100, 100);
		zR(p, 200, 100);
		
		// Vierte Zeile
		zR(p, 50, 150);
		zR(p, 150, 150);
		zR(p, 250, 150);
		
		// Fünfte Zeile
		zR(p, 0, 200);
		zR(p, 100, 200);
		zR(p, 200, 200);
		
		// Sechste Zeile
		zR(p, 50, 250);
		zR(p, 150, 250);
		zR(p, 250, 250);
	}
	
	public static void fA1(int[][] p) {
		
		zeichneRechteck(
			p, 0, 0, 50, 50, 0x0000FF
		);
		zeichneRechteck(
			p, 50, 50, 50, 50, 0x0000FF
		);
		zeichneRechteck(
			p, 100, 100, 50, 50, 0x0000FF
		);
		zeichneRechteck(
			p, 150, 150, 50, 50, 0x0000FF
		);
		zeichneRechteck(
			p, 200, 200, 50, 50, 0x0000FF
		);
		zeichneRechteck(
			p, 250, 250, 50, 50, 0x0000FF
		);
		
	}
	
	public static void fA2(int[][] p) {
		
		int[] positions = { 0, 50, 100, 150, 200, 250 };
		
		for (int i = 0; i < positions.length; i++) {
			
			zeichneRechteck(
				p, positions[i], positions[i], 50, 50, 0x0000FF
			);
		
		}
		
	}
	
	public static void fA3(int[][] p) {
		
		for (int i = 0; i < 6; i++) {
			
			zeichneRechteck(
				p, i * 50, i * 50, 50, 50, 0x0000FF
			);
			
		}
		
	}
	
	public static void zR(int[][] p, int xStart, int yStart) {
		zeichneRechteck(p, xStart, yStart, 50, 50, 0x0000FF);
	}
	
	public static void zeichneRechteck(
		int[][] p, int xStart, int yStart, int width, int height, int color
	) {
		
		for (int x = 0; x < width; x++) {
			
			for (int y = 0; y < height; y++) {
				
				p[x + xStart][y + yStart] = color;
				
			}
			
		}
		
	}
	
}
