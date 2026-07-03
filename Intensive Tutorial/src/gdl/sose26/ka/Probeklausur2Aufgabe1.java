package gdl.sose26.ka;

import static prog.Picture.*;

public class Probeklausur2Aufgabe1 {
	
	public static void main(String[] args) {
		int[][] p = loadFromFileSystem("./MyPicture.jpg");
		
		f(p);
		show(p);
	}
	
	public static void f(int[][] p) {
		
		int hoehe = p[0].length;
		
		for (int x = 0; x < p.length; x++) {
			for (int y = 0; y < hoehe / 2; y++) {
				
				int temp = p[x][y];
				p[x][y] = p[x][y + (hoehe / 2)];
				p[x][y + (hoehe / 2)] = temp;
				
			}
		}
	}
}
