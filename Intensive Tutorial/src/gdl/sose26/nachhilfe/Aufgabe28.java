package gdl.sose26.nachhilfe;

import static prog.Picture.*;
import static prog.Picture.show;

public class Aufgabe28 {
	
	public static void main(String[] args) {
		
		int[][] p = loadFromFileSystem("./MyPicture.jpg");
		spiegeln(p);
		show(p);
	}
	
	
	public static void spiegeln(int[][] p) {
		
		int breite = p.length;
		int hoehe = p[0].length;
		int mitte = breite / 2;
		
		for (int x = 0; x < mitte; x++) {
			for (int y = 0; y < hoehe; y++) {
				
				int temp = p[x][y];
				p[x][y] = p[x + mitte][y];
				p[x + mitte][y] = temp;
				
				// start = links
				// links = rechts
				// rechts = start
				
			}
		}
	}
	
}
