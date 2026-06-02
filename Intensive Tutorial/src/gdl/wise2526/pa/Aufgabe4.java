package gdl.wise2526.pa;

public class Aufgabe4 {
	
	public static int alter = 0;
	
	public static void main(String[] args) {
		
		String x = "Das ist ein einfacher Teststring";
		String y = "in";
		
		for (int i = 0; i < x.length() - 1; i++) {
			
			/*char zeichenEins = x.charAt(i);
			char zeichenZwei = x.charAt(i + 1);
			
			if (zeichenEins == y.charAt(0) && zeichenZwei == y.charAt(1)) {
				
				System.out.println("Zeichen '" + y + "' gefunden an Position: " + i);
				
			}*/
			
			for (int j = 0; j < y.length(); j++) {
			
			
			
			}
			
			
			
		}
	}
	
	//D
	//a
	//s
	//
	//i
	//s
	//t
	//
	//e
	//i
	//n
	//
	//T
	//e
	//s
	//t
	//s
	//t
	//r
	//i
	//n
	//g
	
	public static int teilStringPosition(String x, String y) {
		for (int i = 0; i < x.length() - (y.length() - 1); i++) {
			boolean gefunden = true;
			
			for (int indexFürY = 0; indexFürY < y.length(); indexFürY++) {
				
				if (y.charAt(indexFürY) != x.charAt(i + indexFürY)) {
					
					gefunden = false;
					
				}
			}
			
			if (gefunden) {
				return i;
			}
		}
		
		return -1;
	}
}
