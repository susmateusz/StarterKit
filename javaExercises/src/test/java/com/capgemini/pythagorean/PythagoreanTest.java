package com.capgemini.pythagorean;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.pythagorean.Pythagorean;

/**
 * Tests Pythagorean class
 * 
 * @author MATSUS
 *
 */
public class PythagoreanTest {

	@Test
	public void shouldFailFor3() {
		assertEquals(-1, Pythagorean.findTriplet(3));
	}

	/**
	 * 375 + 200 + 425 = 1000 375 * 200 * 425 = 3187500
	 */
	@Test
	public void shouldReturn3187500For1000() {
		assertEquals(375 * 200 * 425, Pythagorean.findTriplet(1000));
	}

	@Test
	public void shouldReturn60For3_4_5() {
		assertEquals(3 * 4 * 5, Pythagorean.findTriplet(3 + 4 + 5));
	}

	@Test
	public void shouldReturn480For6_8_10() {
		assertEquals(6 * 8 * 10, Pythagorean.findTriplet(6 + 8 + 10));
	}

	@Test
	public void shouldReturn780For5_12_13() {
		assertEquals(5 * 12 * 13, Pythagorean.findTriplet(5 + 12 + 13));
	}

}
