package sose25.ka;

import prog.Picture;

/**
 *
 * @author Luis-St
 *
 */

public class Aufgabe_32 {
	
	public static void main(String[] args) {
		
		int[][] image = Picture.loadFromFileSystem("./MyPicture.jpg");
		
		int centerX = 60;
		int centerY = 100;
		
		int targetX = 220;
		int targetY = 100;
		
		int radius = 40;
		
		for (int x = 0; x < image.length; x++) {
			
			for (int y = 0; y < image[0].length; y++) {
				
				int distanceX = x - centerX;
				int distanceY = y - centerY;
				
				int distance = distanceX * distanceX + distanceY * distanceY;
				// int distance = Math.pow(distanceX, 2) + Math.pow(distanceY, 2);
				
				if (distance <= radius * radius) {
					// if (Math.sqrt(distance) <= radius) {
				
					int temp = image[x][y];
					
					image[x][y] = image[targetX + distanceX][targetY + distanceY];
					
					image[targetX + distanceX][targetY + distanceY] = temp;
					
				}
				
			}
			
		}
		
		Picture.show(image);
	}
	
}
