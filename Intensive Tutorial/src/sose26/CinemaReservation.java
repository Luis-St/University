package sose26;

public class CinemaReservation {
	
	public static void main(String[] args) {
		
		int reihen = 12;
		int plaetze = 8;
		int zufall = 10;
		java.util.Random rng = new java.util.Random(zufall);
		
		boolean[][] kino = new boolean[reihen][plaetze];
		
		
		for (int reihe = 0; reihe < kino.length; reihe++) {
			
			for (int platz = 0; platz < kino[reihe].length; platz++) {
				
				kino[reihe][platz] = rng.nextBoolean();
				
			}
		}
		
		anzeigen(kino);
		
	}
	
	public static void anzeigen(boolean[][] kino) {
	
		for (int reihe = 0; reihe < kino.length; reihe++) {
			
			for (int platz = 0; platz < kino[reihe].length; platz++) {
				
				if (kino[reihe][platz]) {
					System.out.print("X");
				} else {
					System.out.print("O");
				}
				
				
			}
			
			System.out.println();
		}
		
	}
	
}
