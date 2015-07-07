package com.capgemini.fibonacci;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit tests for class Fibonacci
 * 
 * @author MATSUS
 *
 */
public class testFibonacci {

	/**
	 * Tests fib() function for the typical(positive) inputs.
	 */
	@Test
	public void testFib() {
		long[] expected = new long[] { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584,
				4181 };
		for (int i = 1; i < 20; i++)
			assertEquals("Wrong result.", expected[i - 1], Fibonacci.fib(i));
	}

	
	/**
	 * Tests fib() for negative inputs.
	 */
	@Test
	public void testFibForWrongInput() {
		for (int i = 0; i > -10; i--)
			assertEquals(1, Fibonacci.fib(i));
	}

}
