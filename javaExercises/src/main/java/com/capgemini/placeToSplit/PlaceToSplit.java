package com.capgemini.placeToSplit;

import java.util.Arrays;

/**
 * Given a non-empty array, return true if there is a place to split the array
 * so that the sum of the numbers on one side is equal to the sum of the numbers
 * on the other side. Example: {{{ canBalance({1, 1, 1, 2, 1}) → true
 * canBalance({2, 1, 1, 2, 1}) → false canBalance({10, 10}) → true }}}
 */
public final class PlaceToSplit {
	private PlaceToSplit() {
	}

	public static boolean canBalance(int[] nums) {

		for (int slice = 1; slice < nums.length; slice++) {
			System.out.print("slice: "+slice);
			int[] left = Arrays.copyOfRange(nums, 0, slice);
			int[] right = Arrays.copyOfRange(nums, slice, nums.length);
			int sumLeft = 0;
			int sumRight = 0;
			System.out.print("\nLeft: ");
			for(int i : left){
				System.out.print(i+" ");
				sumLeft += i;
			}
			System.out.print("\nRight: ");
			for(int i : right){
				System.out.print(i+" ");
				sumRight += i;
			}
			if(sumLeft == sumRight)
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(canBalance(new int[] {10}));
	}
}
