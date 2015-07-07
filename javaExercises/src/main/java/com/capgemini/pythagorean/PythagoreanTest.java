package com.capgemini.pythagorean;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests Pythagorean class
 * 
 * @author MATSUS
 *
 */
public class PythagoreanTest {

	/**
	 * Tests if function findTriplet() finds correct value for some examples of
	 * triplets
	 */
	@Test
	public void testFindTriplet() {
		assertEquals(375 * 200 * 425, Pythagorean.findTriplet(375 + 200 + 425));
		assertEquals(5 * 12 * 13, Pythagorean.findTriplet(5 + 12 + 13));
		assertEquals(6 * 8 * 10, Pythagorean.findTriplet(6 + 8 + 10));
		assertEquals(3 * 4 * 5, Pythagorean.findTriplet(3 + 4 + 5));
	}

}
