package sose25.ka;

/**
 *
 * @author Luis-St
 *
 */

public class Aufgabe_29 {
	
	public static void main(String[] args) {
		
		int[][] x = {
			{ 2, 3, 5 },
			{ 3, 8, 2 },
			{},
			{ 2, 7, 4 },
			{ 2, 4, 1, 7, 3}
		};
		
		
		System.out.println(sumMax(x));
		
	}
	
	public static int sumMax(int[][] x) {
		
		int summe = 0;
		
		for (int i = 0; i < x.length; i++) {
		
			int[] array = x[i];
			
			if (array.length != 0) {
			
				int max = max(array);
				summe = summe + max;
				
			}
			
		}
		
		return summe;
	}
	
	public static int max(int[] array) {
		
		int max = array[0];
		
		for (int i = 1; i < array.length; i++) {
			
			if (array[i] > max) {
				
				max = array[i];
				
			}
			
		}
		
		return max;
	}
	
}
