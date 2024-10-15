package net.luis.sheet5;

import net.luis.util.Task;

/**
 *
 * @author Luis-St
 *
 */

@Task("1")
public class IntMergesort {
	
	public static void sort(int[] array) {
		int[] helper = new int[array.length];
		sort(array, helper, 0, array.length - 1);
	}
	
	private static void sort(int[] array, int[] helper, int low, int high) {
		if (low < high) {
			int middle = (low + high) / 2;
			sort(array, helper, low, middle);
			sort(array, helper, middle + 1, high);
			merge(array, helper, low, middle, high);
		}
	}
	
	private static void merge(int[] array, int[] helper, int low, int middle, int high) {
		if (high + 1 - low >= 0) {
			System.arraycopy(array, low, helper, low, high + 1 - low);
		}
		int helperLeft = low;
		int helperRight = middle + 1;
		int current = low;
		while (helperLeft <= middle && helperRight <= high) {
			if (helper[helperLeft] <= helper[helperRight]) {
				array[current] = helper[helperLeft];
				helperLeft++;
			} else {
				array[current] = helper[helperRight];
				helperRight++;
			}
			current++;
		}
		int remaining = middle - helperLeft;
		if (remaining + 1 >= 0) {
			System.arraycopy(helper, helperLeft, array, current, remaining + 1);
		}
	}
	
}
