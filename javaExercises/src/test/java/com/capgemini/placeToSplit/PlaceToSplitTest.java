package com.capgemini.placeToSplit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.placeToSplit.PlaceToSplit;

/**
 * Tests class PlaceToSplit - Tests if function canBalance(...) returns correct
 * value for most common examples: when array is empty, has one or two elements,
 * all elements are equal 0, some elements are nagative etc
 * 
 * @author MATSUS
 *
 */
public class PlaceToSplitTest {

	@Test
	public void shouldReturnFalseWhenEmpty() {
		assertFalse(PlaceToSplit.canBalance(new int[] {}));
	}

	@Test
	public void shouldReturnFalseWhen0() {
		assertFalse(PlaceToSplit.canBalance(new int[] { 0 }));
	}

	@Test
	public void shouldReturnFalseWhenOneElement() {
		assertFalse(PlaceToSplit.canBalance(new int[] { 10 }));
	}

	@Test
	public void shouldReturnFalseWhen2_1_1_2_1() {
		assertFalse(PlaceToSplit.canBalance(new int[] { 2, 1, 1, 2, 1 }));
	}

	@Test
	public void shouldReturnTrueWhen0_0_0() {
		assertTrue(PlaceToSplit.canBalance(new int[] { 0, 0, 0 }));
	}

	@Test
	public void shouldReturnTrueWhen1_3_minus2() {
		assertTrue(PlaceToSplit.canBalance(new int[] { 1, 3, -2 }));
	}

	@Test
	public void shouldReturnTrueWhen10_10() {
		assertTrue(PlaceToSplit.canBalance(new int[] { 10, 10 }));
	}

	@Test
	public void shouldReturnTrueWhen1_1_1_2_1() {
		assertTrue(PlaceToSplit.canBalance(new int[] { 1, 1, 1, 2, 1 }));
	}

}
