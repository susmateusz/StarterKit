package com.capgemini.coins;

import static org.junit.Assert.assertEquals;

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
	List<Integer> A;

	@Test
	public void shouldReturn0PairsWhenEmpty() {
		// empty set
		A = new ArrayList<Integer>();
		assertEquals(0, Coins.countPairs(A));
	}

	@Test
	public void shouldReturn0PairsWhen1() {
		// set with one coin
		A = new ArrayList<Integer>(Arrays.asList(1));
		assertEquals(0, Coins.countPairs(A));
	}

	@Test
	public void shouldReturn0PairsWhen10() {
		// set with 2 different coins
		A = new ArrayList<Integer>(Arrays.asList(1, 0));
		assertEquals(0, Coins.countPairs(A));
	}

	@Test
	public void shouldReturn1PairsWhen11() {
		// set with 2 identical coins
		A = new ArrayList<Integer>(Arrays.asList(1, 1));
		assertEquals(1, Coins.countPairs(A));
	}

	@Test
	public void shouldReturn2PairsWhen000() {
		// set with 3 coins
		A = new ArrayList<Integer>(Arrays.asList(0, 0, 0));
		assertEquals(2, Coins.countPairs(A));
	}

	@Test
	public void shouldReturn2PairsWhen110100() {
		// set with more coins
		A = new ArrayList<Integer>(Arrays.asList(1, 1, 0, 1, 0, 0));
		assertEquals(2, Coins.countPairs(A));
	}

	/**
	 * Tests if function Solution returns proper result. it should
	 * ignore number of pairs in initial list, even if it is the best result.
	 */
	@Test
	public void shouldReturn0WhenEmpty() {
		A = new ArrayList<Integer>();
		assertEquals(0, Coins.solution(A));
	}

	@Test
	public void shouldReturn0When0() {
		A = new ArrayList<Integer>(Arrays.asList(0));
		assertEquals(0, Coins.solution(A));
	}

	@Test
	public void shouldReturn0When00() {
		A = new ArrayList<Integer>(Arrays.asList(0, 0));
		assertEquals(0, Coins.solution(A));
	}

	@Test
	public void shouldReturn1When10() {
		A = new ArrayList<Integer>(Arrays.asList(1, 0));
		assertEquals(1, Coins.solution(A));
	}

	@Test
	public void shouldReturn1When00() {
		A = new ArrayList<Integer>(Arrays.asList(0, 0, 0));
		assertEquals(1, Coins.solution(A));
	}

	@Test
	public void shouldReturn2When101() {
		A = new ArrayList<Integer>(Arrays.asList(1, 0, 1));
		assertEquals(2, Coins.solution(A));
	}

	@Test
	public void shouldReturn4When110100() {
		A = new ArrayList<Integer>(Arrays.asList(1, 1, 0, 1, 0, 0));
		assertEquals(4, Coins.solution(A));
	}

}
