package gdl.sose25;

import static prog.ConsoleReader.*;

public class Bedingungen {

	@SuppressWarnings("unused")

	public static void main(String[] args) {

		int x = readInt();
		
		
		boolean green = false;
		boolean yellow = false;
		boolean red = false;
		
		boolean redYellow = red && yellow;
		

		if (red) {
			
			System.out.println("Ich muss halten");

		} 
		
		if (redYellow) {
			
			System.out.println("Ich bereite mich auf das losfahren vor");

		}
		
		if (green) {
			
			System.out.println("Ich darf fahren");
			
		}

		if (yellow) {
			
			System.out.println("Ich geb gas");
			
		}

	}
	
	public static void beispiel() {
		
		int x = readInt();
		
		
		
		
		
		
		
		
		if (18 > x) {
			
			System.out.println("x ist größer als 18");
			
		}
		
		if (x >= 16) {
			
			System.out.println("x ist größer gleich 16");
			
		}
		
		if (x > 18 || 6 >= x) {
			
			System.out.println("x ist größer als 18 oder kleiner gleich 6");
			
		}
		
		if (x > 6 && 18 >= x) {
			
			System.out.println("x ist größer als 6 und kleiner gleich 18");
			
		}
		
		
	}
	
	
	
	public static void beispielAlternative() {
		
		int x = readInt();
		
		if (x < 18) {
			
			System.out.println("x ist größer als 18");
			
		}
		
		if (x > 16 || x == 16) {
			
			System.out.println("x ist größer gleich 16");
			
		}
		
		if (6 >= x || x > 18) {
			
			System.out.println("x ist größer als 18 oder kleiner gleich 6");
			
		}
		
		if (18 > x && 18 == x && x > 6) {
			
			System.out.println("x ist größer als 6 und kleiner gleich 18");
			
		}
		
		
	}
	
	
	
	
	
	
	
	/*boolean green = false;
	boolean yellow = false;
	boolean red = false;
	
	boolean redYellow = red && yellow;
	

	if (red) {
		
		System.out.println("Ich muss halten");

	} 
	
	if (redYellow) {
		
		System.out.println("Ich bereite mich auf das losfahren vor");

	}
	
	if (green) {
		
		System.out.println("Ich darf fahren");
		
	}

	if (yellow) {
		
		System.out.println("Ich geb gas");
		
	}*/
	

	/*boolean green = false;
	boolean yellow = false;
	boolean red = false;
	
	boolean redYellow = red && yellow;
	

	if (red) {
		
		System.out.println("Ich muss halten");

	} else if (redYellow) {
		
		System.out.println("Ich bereite mich auf das losfahren vor");

	} else if (green) {
		
		System.out.println("Ich darf fahren");
		
	} else if (yellow) {
		
		System.out.println("Ich geb gas");
		
	}*/
	
	/*boolean green = false;
	boolean yellow = false;
	boolean red = false;
	
	boolean redYellow = red && yellow;
	

	if (red) {
		
		System.out.println("Ich muss halten");

	} else if (redYellow) {
		
		System.out.println("Ich bereite mich auf das losfahren vor");

	} else if (green) {
		
		System.out.println("Ich darf fahren");
		
	} else if (yellow) {
		
		System.out.println("Ich geb gas");
		
	} else {
		
		System.out.print(redYellow);
		
	}*/

}
