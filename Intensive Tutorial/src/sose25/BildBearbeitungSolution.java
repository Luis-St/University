package sose25;

/**
 *
 * @author Luis-St
 *
 */

public class BildBearbeitungSolution {
	
	/**
	 * Erstellt ein Bild mit gegebener Breite, Höhe und Farbe
	 */
	public static int[][] createImage(int width, int height, int color) {
		int[][] image = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				image[i][j] = color;
			}
		}
		return image;
	}
	
	/**
	 * Extrahiert einen rechteckigen Teil des Bildes
	 * x, y = obere rechte Ecke des zu extrahierenden Bereichs
	 */
	public static int[][] getRectangleImagePart(int[][] image, int x, int y, int width, int height) {
		int[][] part = new int[height][width];
		
		// Berechne die obere linke Ecke basierend auf der oberen rechten Ecke
		int startX = x - width + 1;
		int startY = y;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int srcY = startY + i;
				int srcX = startX + j;
				
				// Prüfe Grenzen
				if (srcY >= 0 && srcY < image.length && srcX >= 0 && srcX < image[0].length) {
					part[i][j] = image[srcY][srcX];
				} else {
					part[i][j] = 0; // Standardfarbe für Bereiche außerhalb des Bildes
				}
			}
		}
		return part;
	}
	
	/**
	 * Extrahiert einen kreisförmigen Teil des Bildes
	 * centerX, centerY = Mittelpunkt des Kreises
	 */
	public static int[][] getCircleImagePart(int[][] image, int centerX, int centerY, int radius) {
		int size = 2 * radius + 1;
		int[][] part = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int srcY = centerY - radius + i;
				int srcX = centerX - radius + j;
				
				// Berechne die Entfernung vom Zentrum
				double distance = Math.sqrt(Math.pow(j - radius, 2) + Math.pow(i - radius, 2));
				
				// Prüfe Grenzen und ob der Punkt im Kreis liegt
				if (distance <= radius && srcY >= 0 && srcY < image.length && srcX >= 0 && srcX < image[0].length) {
					part[i][j] = image[srcY][srcX];
				} else {
					part[i][j] = -1; // Standardfarbe für Bereiche außerhalb des Bildes oder Kreises
				}
			}
		}
		return part;
	}
	
	/**
	 * Fügt einen Bildteil in das Hauptbild an einer bestimmten Position ein
	 * x, y = Position im Zielbild, wo das imagePart eingefügt wird
	 * Pixel mit Wert -1 werden als transparent behandelt und nicht eingefügt
	 */
	public static void insertImagePart(int[][] image, int x, int y, int[][] imagePart) {
		for (int i = 0; i < imagePart.length; i++) {
			for (int j = 0; j < imagePart[0].length; j++) {
				int targetY = y + i;
				int targetX = x + j;
				
				// Prüfe Grenzen des Zielbildes
				if (targetY >= 0 && targetY < image.length && targetX >= 0 && targetX < image[0].length) {
					
					// Nur einfügen wenn der Pixel nicht transparent ist (-1)
					if (imagePart[i][j] != -1) {
						image[targetY][targetX] = imagePart[i][j];
					}
				}
			}
		}
	}
	
	/**
	 * Fügt ein Rechteck in das Bild ein
	 * x, y = obere rechte Ecke des Rechtecks
	 */
	public static void insertRectangle(int[][] image, int x, int y, int width, int height, int color) {
		// Berechne die obere linke Ecke basierend auf der oberen rechten Ecke
		int startX = x - width + 1;
		int startY = y;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int targetY = startY + i;
				int targetX = startX + j;
				
				// Prüfe Grenzen
				if (targetY >= 0 && targetY < image.length && targetX >= 0 && targetX < image[0].length) {
					image[targetY][targetX] = color;
				}
			}
		}
	}
	
	/**
	 * Fügt ein Rechteck mit Rand in das Bild ein
	 * Verwendet insertRectangle und getImagePart
	 */
	public static void insertRectangle(int[][] image, int x, int y, int width, int height, int color, int borderSize) {
		// Speichere den ursprünglichen Inhalt des inneren Bereichs
		int innerWidth = width - 2 * borderSize;
		int innerHeight = height - 2 * borderSize;
		
		if (innerWidth > 0 && innerHeight > 0) {
			// Berechne die Position der oberen rechten Ecke des inneren Bereichs
			int innerX = x - borderSize;
			int innerY = y + borderSize;
			
			// Speichere den inneren Bereich
			int[][] innerPart = getRectangleImagePart(image, innerX, innerY, innerWidth, innerHeight);
			
			// Zeichne das komplette Rechteck
			insertRectangle(image, x, y, width, height, color);
			
			// Füge den ursprünglichen inneren Bereich wieder ein
			int startX = innerX - innerWidth + 1;
			int startY = innerY;
			
			for (int i = 0; i < innerHeight; i++) {
				for (int j = 0; j < innerWidth; j++) {
					int targetY = startY + i;
					int targetX = startX + j;
					
					if (targetY >= 0 && targetY < image.length && targetX >= 0 && targetX < image[0].length) {
						image[targetY][targetX] = innerPart[i][j];
					}
				}
			}
		} else {
			// Wenn der innere Bereich zu klein ist, zeichne nur das gefüllte Rechteck
			insertRectangle(image, x, y, width, height, color);
		}
	}
	
	/**
	 * Fügt einen Kreis in das Bild ein
	 * centerX, centerY = Mittelpunkt des Kreises
	 */
	public static void insertCircle(int[][] image, int centerX, int centerY, int radius, int color) {
		// Zeichne den Kreis
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				// Berechne die Entfernung vom Zentrum
				double distance = Math.sqrt(Math.pow(j - centerX, 2) + Math.pow(i - centerY, 2));
				
				// Wenn der Punkt innerhalb des Radius liegt, färbe ihn
				if (distance <= radius) {
					image[i][j] = color;
				}
			}
		}
	}
	
	/**
	 * Spiegelt das Bild entlang der Achsen
	 */
	public static void mirror(int[][] image, boolean mirrorXAxis, boolean mirrorYAxis) {
		int height = image.length;
		int width = image[0].length;
		
		if (mirrorYAxis) {
			// Spiegelung entlang der Y-Achse (horizontal)
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width / 2; j++) {
					int temp = image[i][j];
					image[i][j] = image[i][width - 1 - j];
					image[i][width - 1 - j] = temp;
				}
			}
		}
		
		if (mirrorXAxis) {
			// Spiegelung entlang der X-Achse (vertikal)
			for (int i = 0; i < height / 2; i++) {
				for (int j = 0; j < width; j++) {
					int temp = image[i][j];
					image[i][j] = image[height - 1 - i][j];
					image[height - 1 - i][j] = temp;
				}
			}
		}
	}
	
	/**
	 * Spiegelt das Bild entlang spezifischer Achsen
	 */
	public static void mirror(int[][] image, boolean mirrorXAxis, int xAxis, boolean mirrorYAxis, int yAxis) {
		int height = image.length;
		int width = image[0].length;
		
		if (mirrorYAxis && yAxis >= 0 && yAxis < width) {
			// Spiegelung entlang einer vertikalen Linie bei yAxis
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					int mirroredJ = 2 * yAxis - j;
					if (mirroredJ >= 0 && mirroredJ < width && j != mirroredJ) {
						// Tausche nur wenn noch nicht getauscht
						if (j < yAxis) {
							int temp = image[i][j];
							image[i][j] = image[i][mirroredJ];
							image[i][mirroredJ] = temp;
						}
					}
				}
			}
		}
		
		if (mirrorXAxis && xAxis >= 0 && xAxis < height) {
			// Spiegelung entlang einer horizontalen Linie bei xAxis
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					int mirroredI = 2 * xAxis - i;
					if (mirroredI >= 0 && mirroredI < height && i != mirroredI) {
						// Tausche nur wenn noch nicht getauscht
						if (i < xAxis) {
							int temp = image[i][j];
							image[i][j] = image[mirroredI][j];
							image[mirroredI][j] = temp;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Rotiert das Bild um degree Grad (nur Vielfache von 90)
	 */
	public static void rotate(int[][] image, int degree) {
		// Normalisiere den Winkel auf 0-360 Bereich
		degree = ((degree % 360) + 360) % 360;
		
		// Nur Vielfache von 90 sind erlaubt
		if (degree % 90 != 0) {
			throw new IllegalArgumentException("Nur Vielfache von 90 Grad sind erlaubt");
		}
		
		int rotations = degree / 90;
		
		for (int r = 0; r < rotations; r++) {
			rotate90Clockwise(image);
		}
	}
	
	/**
	 * Hilfsmethode: Rotiert das Bild um 90 Grad im Uhrzeigersinn
	 */
	private static void rotate90Clockwise(int[][] image) {
		int height = image.length;
		int width = image[0].length;
		
		// Für eine In-Place-Rotation bei quadratischen Bildern
		if (height == width) {
			// Transponiere die Matrix
			for (int i = 0; i < height; i++) {
				for (int j = i + 1; j < width; j++) {
					int temp = image[i][j];
					image[i][j] = image[j][i];
					image[j][i] = temp;
				}
			}
			
			// Spiegle jede Zeile horizontal
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width / 2; j++) {
					int temp = image[i][j];
					image[i][j] = image[i][width - 1 - j];
					image[i][width - 1 - j] = temp;
				}
			}
		} else {
			throw new UnsupportedOperationException("Rotation von nicht-quadratischen Bildern ist nicht implementiert");
		}
	}
	
}
