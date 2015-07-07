package com.capgemini.coins;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Tests for class Coins.
 * 
 * @author MATSUS
 *
 */
public class testCoins {

	/**
	 * Checks is function countPairs work properly. Checks reactions for empty
	 * set or for a set with one element.
	 */
	@Test
	public void testCountPairs() {
		List<Integer> A;
		// empty set
		A = new ArrayList<Integer>();
		assertEquals(0, Coins.countPairs(A));
		// set with one coin
		A = new ArrayList<Integer>(Arrays.asList(1));
		assertEquals(0, Coins.countPairs(A));
		// set with 2 different coins
		A = new ArrayList<Integer>(Arrays.asList(1, 0));
		assertEquals(0, Coins.countPairs(A));
		// set with 2 identical coins
		A = new ArrayList<Integer>(Arrays.asList(1, 1));
		assertEquals(1, Coins.countPairs(A));
		// set with 3 coins
		A = new ArrayList<Integer>(Arrays.asList(0, 0, 0));
		assertEquals(2, Coins.countPairs(A));
		// set with more coins
		A = new ArrayList<Integer>(Arrays.asList(1, 1, 0, 1, 0, 0));
		assertEquals(2, Coins.countPairs(A));
	}

	/**
	 * Tests if function Solution returns proper result. Especially, if it
	 * ignores number of pairs in initial list, even if it is the best result.
	 */
	@Test
	public void testSolution() {
		List<Integer> A;
		// first example
		A = new ArrayList<Integer>(Arrays.asList(1, 1, 0, 1, 0, 0));
		assertEquals(4, Coins.solution(A));
		A = new ArrayList<Integer>(Arrays.asList(0, 0, 0));
		assertEquals(1, Coins.solution(A));
		A = new ArrayList<Integer>(Arrays.asList(1, 0, 1));
		assertEquals(2, Coins.solution(A));
		// one element set
		A = new ArrayList<Integer>(Arrays.asList(0));
		assertEquals(0, Coins.solution(A));
		A = new ArrayList<Integer>(Arrays.asList(0, 0));
		assertEquals(0, Coins.solution(A));
		A = new ArrayList<Integer>(Arrays.asList(1, 0));
		assertEquals(1, Coins.solution(A));
		// empty set
		A = new ArrayList<Integer>();
		assertEquals(0, Coins.solution(A));
	}

}
