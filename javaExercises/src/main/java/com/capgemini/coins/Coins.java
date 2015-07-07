package com.capgemini.coins;

import java.util.List;

/**
 * Created by ldrygala on 2015-02-06.
 * <p/>
 * Consider N coins aligned in a row. Each coin is showing either heads or
 * tails. The adjacency of these coins is the number of adjacent pairs of coins
 * with the same side facing up.
 * <p/>
 * It must return the maximum possible adjacency that can be obtained by
 * reversing exactly one coin (that is, one of the coins must be reversed).
 * Consecutive elements of array A represent consecutive coins in the row. Array
 * A contains only 0s and/or 1s: 0 represents a coin with heads facing up; 1
 * represents a coin with tails facing up. For example, given array A consisting
 * of six numbers, such that:
 * <p/>
 * A[0] = 1 A[1] = 1 A[2] = 0 A[3] = 1 A[4] = 0 A[5] = 0
 * <p/>
 * the function should return 4. The initial adjacency is 2, as there are two
 * pairs of adjacent coins with the same side facing up, namely (0, 1) and (4,
 * 5). After reversing the coin represented by A[2], the adjacency equals 4, as
 * there are four pairs of adjacent coins with the same side facing up, namely:
 * (0, 1), (1, 2), (2, 3) and (4, 5), and it is not possible to obtain a higher
 * adjacency. The same adjacency can be obtained by reversing the coin
 * represented by A[3].
 */
public class Coins {

	/**
	 * Returns a number of adjacent pairs with the same value in given
	 * <code>List</code> of <code>Integers</code>. When List is empty returns 0.
	 * 
	 * @param coins
	 *            - list of the coins representing by Integers
	 * @return Number of adjacent pairs with the same value
	 */
	public static int countPairs(List<Integer> coins) {
		int pairs = 0;
		for (int i = 0; i < coins.size() - 1; i++)
			if (coins.get(i) == coins.get(i + 1))
				pairs++;
		return pairs;
	}

	/**
	 * Returns the maxumim number of adjacent pairs given by reversing EXACTLY
	 * one coin. As is specified in requirements, one coin must be reversed,
	 * even if the initial set gives the highest score. Requires function
	 * countPairs(List<Integer> coins). Assume that 0 represents coin with head
	 * facing up and 1 represents coin with tails facing up.
	 * 
	 * @param coins
	 *            - list of coins in current set
	 * @return Maximum number of adjacent pairs given by reversing one coin
	 */
	public static int solution(List<Integer> coins) {
		// number of pairs in the initial List is the initial max number of
		// pairs
		int maxPairs = 0;
		int currentPairs = 0;
		for (int i = 0; i < coins.size(); i++) {
			// reverse coin
			coins.set(i, (coins.get(i) == 0) ? 1 : 0);
			currentPairs = countPairs(coins);
			// reverse coin
			coins.set(i, (coins.get(i) == 0) ? 1 : 0);
			// update maximum number of pairs
			maxPairs = Math.max(maxPairs, currentPairs);
		}
		return maxPairs;
	}
}
