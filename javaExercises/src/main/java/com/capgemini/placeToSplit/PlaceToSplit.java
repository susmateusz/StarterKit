package com.capgemini.placeToSplit;

import java.util.Arrays;

/**
 * Given a non-empty array, return true if there is a place to split the array
 * so that the sum of the numbers on one side is equal to the sum of the numbers
 * on the other side. Example: {{{ canBalance({1, 1, 1, 2, 1}) → true
 * canBalance({2, 1, 1, 2, 1}) → false canBalance({10, 10}) → true }}}
 */
public final class PlaceToSplit {

	/**
	 * Checks if given array if ints can be split into 2 parts with equal sum of values
	 * @param nums array of ints
	 * @return true if array can be split, false otherwise
	 */
	public static boolean canBalance(int[] nums) {
		// slice - place of split
		for (int slice = 1; slice < nums.length; slice++) {
			// Spliting original array into left and right part at the split point
			int[] left = Arrays.copyOfRange(nums, 0, slice);
			int[] right = Arrays.copyOfRange(nums, slice, nums.length);
			int sumLeft = 0;
			int sumRight = 0;
			for(int i : left)
				sumLeft += i;
			for(int i : right)
				sumRight += i;
			if(sumLeft == sumRight)
				return true;
		}
		return false;
	}
}
