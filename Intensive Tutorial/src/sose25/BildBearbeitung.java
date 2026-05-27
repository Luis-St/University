package sose25;

import prog.Picture;

import java.util.Arrays;

public class BildBearbeitung {
	
	public static void main(String[] args) {
		
		int[] array = {
			1, 2, 3, 4, 5, 6, 7, 8, 9
		};
		
		mirrorArray(array);
		
		System.out.println(Arrays.toString(array));
		
		int[][] image = Picture.loadFromFileSystem("./MyPicture.jpg");
		
		/*int x = 250;
		int y = 120;
		int innerRadius = 50;
		int outerRadius = 70;
		int color = 0;
		
		insertRing(image, x, y, innerRadius, outerRadius, color);
		
		insertRectangle(image, 100, 100, 50, 50, 0x000FFF, 10);
		
		mirrorYAxis(image);*/
		
		int partX = 50;
		int partY = 75;
		
		int partWidth = 150;
		int partHeight = 150;
		
		/*for (int x = partX; x < partX + (partWidth / 2); x++) {
			
			for (int y = partY; y < partY + partHeight; y++) {
			
				int temp = image[x][y];
				
				image[x][y] = image[partX + partWidth - 1 - (x - partX)][y];
				
				image[partX + partWidth - 1 - (x - partX)][y] = temp;
			
			}
			
		}*/
		
		Picture.show(image);
	}
	
	//region 18.06
	// Erstellt ein Bild mit der angegebenen Breite, Höhe und Farbe
	public static int[][] createImage(int width, int height, int color) {
		
		// Erstellt ein zweidimensionales Array, das die Pixel des Bildes repräsentiert
		int[][] image = new int[width][height];
		
		// Füllt das Bild mit der angegebenen Farbe
		// Das Array wird erst in der ersten Dimension (Breite) und dann in der zweiten Dimension (Höhe) durchlaufen
		for (int x = 0; x < width; x++) {
			
			for (int y = 0; y < height; y++) {
				
				image[x][y] = color;
				
			}
			
		}
		
		return image;
	}
	
	// Fügt einen Kreis in das Bild ein, der durch den Mittelpunkt (centerX, centerY) und den Radius definiert ist
	public static void insertCircle(int[][] image, int centerX, int centerY, int radius, int color) {
		
		// Durchläuft das gesamte Bild, um die Pixel zu überprüfen
		for (int x = 0; x < image.length; x++) {
			
			for (int y = 0; y < image[0].length; y++) {
				
				// Berechnung der Distanz vom Mittelpunkt des Kreises zum aktuellen Pixel
				double distanceX = x - centerX;
				double distanceY = y - centerY;
				
				// Berechnet die quadratische Distanz, um die Wurzel zu vermeiden
				// Berechnung erfolgt nach dem Satz des Pythagoras: a^2 + b^2 = c^2
				double distance = distanceX * distanceX + distanceY * distanceY;
				
				// Überprüft, ob die Distanz kleiner oder gleich dem Quadrat des Radius ist
				// Wenn ja, wird der Pixel auf die angegebene Farbe gesetzt
				// Der radius muss quadriert werden, um die Wurzel zu vermeiden (und da die Distanz auch quadriert vorliegt)
				if (distance <= radius * radius) {
					
					image[x][y] = color;
					
				}
				
			}
		}
		
	}
	
	// Fügt einen Ring in das Bild ein, der durch den Mittelpunkt (centerX, centerY), den inneren und äußeren Radius definiert ist
	public static void insertRing(int[][] image, int centerX, int centerY, int innerRadius, int outerRadius, int color) {
		
		// Durchläuft das gesamte Bild, um die Pixel zu überprüfen
		for (int x = 0; x < image.length; x++) {
			
			for (int y = 0; y < image[0].length; y++) {
				
				// Berechnung der Distanz vom Mittelpunkt des Kreises zum aktuellen Pixel
				double distanceX = x - centerX;
				double distanceY = y - centerY;
				
				// Berechnet die quadratische Distanz, um die Wurzel zu vermeiden
				// Berechnung erfolgt nach dem Satz des Pythagoras: a^2 + b^2 = c^2
				double distance = distanceX * distanceX + distanceY * distanceY;
				
				// Überprüft, ob die Distanz im Bereich zwischen dem inneren und äußeren Radius liegt
				// Wenn ja, wird der Pixel auf die angegebene Farbe gesetzt
				// Der innerRadius und outerRadius müssen quadriert werden, um die Wurzel zu vermeiden (und da die Distanz auch quadriert vorliegt)
				if (distance <= outerRadius * outerRadius && distance >= innerRadius * innerRadius) {
					
					image[x][y] = color;
					
				}
				
			}
			
		}
		
	}
	
	// Fügt den Rahmen eines Rechtecks in das Bild ein, das durch die Koordinaten (x, y), die Breite und Höhe definiert ist
	// Die Breite und Höhe des Rahmens werden durch borderSize bestimmt
	public static void insertRectangle(int[][] image, int x, int y, int width, int height, int color, int borderSize) {
		
		// Überprüft, ob die angegebenen Koordinaten und Dimensionen im gültigen Bereich liegen
		for (int xInReg = x; xInReg < x + width; xInReg++) {
			
			for (int yInReg = y; yInReg < y + height; yInReg++) {
				
				// Überprüft, ob die größe des Rahmens größer als 0 ist
				if (borderSize > 0) {
					
					// Überprüft, ob die aktuellen Koordinaten innerhalb des Rahmens liegen
					boolean isInBorder =
						xInReg < x + borderSize || // Linker Rand
							xInReg >= x + width - borderSize || // Rechter Rand
							yInReg < y + borderSize || // Oberer Rand
							yInReg >= y + height - borderSize; // Unterer Rand
					
					// Wenn die aktuellen Koordinaten innerhalb des Rahmens liegen, wird der Pixel auf die angegebene Farbe gesetzt
					if (isInBorder) {
						
						image[yInReg][xInReg] = color;
						
					}
					
				}
				
			}
			
		}
		
	}
	//endregion
	
	//region 25.06
	// Spiegelt das Bild entlang der X-Achse (horizontal)
	// Die obere Hälfte des Bildes wird mit der unteren Hälfte vertauscht
	public static void mirrorXAxis(int[][] image) {
		int height = image.length;
		int width = image[0].length;
		
		// Durchläuft jede Zeile des Bildes bis zur Mitte
		for (int y = 0; y < height / 2; y++) {
			
			// Durchläuft die Pixel in der Zeile von links nach rechts
			for (int x = 0; x < width; x++) {
				
				// Tauscht die Pixel an der aktuellen Position mit dem entsprechenden Pixel auf der anderen Seite der Zeile
				int current = image[y][x];
				
				image[y][x] = image[height - 1 - y][x];
				
				image[height - 1 - y][x] = current;
				
			}
			
		}
		
	}
	
	// Spiegelt das Bild entlang der Y-Achse (vertikal)
	// Die linke Hälfte des Bildes wird mit der rechten Hälfte vertauscht
	public static void mirrorYAxis(int[][] image) {
		int height = image.length;
		int width = image[0].length;
		
		// Durchläuft jede Zeile des Bildes
		for (int y = 0; y < height; y++) {
			
			// Durchläuft die Pixel in der Zeile von links nach rechts bis zur Mitte
			for (int x = 0; x < width / 2; x++) {
				
				// Tauscht die Pixel an der aktuellen Position mit dem entsprechenden Pixel auf der anderen Seite der Zeile
				int current = image[y][x];
				
				image[y][x] = image[y][width - 1 - x];
				
				image[y][width - 1 - x] = current;
				
			}
			
		}
		
	}
	
	// Einfaches Beispiel für das Spiegeln eines eindimensionalen Arrays
	public static void mirrorArray(int[] array) {
		
		for (int i = 0; i < array.length / 2; i++) {
			
			int temp = array[i];
			
			array[i] = array[array.length - 1 - i];
			
			array[array.length - 1 - i] = temp;
			
		}
		
	}
	//endregion
}
