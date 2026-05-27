package wise2526;

import static prog.Picture.*;

public class BildbearbeitungVorlage {
	
	public static void main(String[] args) {
		int[][] image = loadResource("");
		
		f(image);
	}
	
	public static void f(int[][] p) {
		
		int breite = p.length;
		int hoehe = p[0].length;
		
		int xStartpunkt = 200;
		int xEndpunkt = 400;
		
		int yStartpunkt = 200;
		int yEndpunkt = 400;
		
		for (int x = xStartpunkt; x < xEndpunkt; x++) {
			
			for (int y = yStartpunkt; y < yEndpunkt; y++) {
				
				int aktuellesPixel = p[x][y];
				
				p[x][y] = 0;
				
				// Lösung der Aufgabe hier
				
			}
			
		}
		
		show(p);
	}
	
}
