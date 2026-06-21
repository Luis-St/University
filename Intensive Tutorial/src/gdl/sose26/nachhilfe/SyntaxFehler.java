package gdl.sose26.nachhilfe;

public class SyntaxFehler {
	
	public static void main(String[] args) {
		
		String c = "Hase";
		c = c + 10;
		c = c + args;
		c = c + new int[] { 2, 4 };
		System.out.println(c);
		
		int i = 10;
		i = i + (byte) 18;
		i = i + (short) 18;
		i = i + (int) 18;
		i = (int) (i + (long) 18);
		System.out.println(i);
		
		// i = i + 1.0; // Aufpassen!!!
		
/*		StringBuffer buffer = new StringBuffer();
		buffer.append(wert).append(",");
		
		buffer.toString()*/
		
	}
	
}
