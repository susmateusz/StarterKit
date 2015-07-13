package com.capgemini.pascalrectangle;

import org.junit.Test;
import static org.junit.Assert.*;

//@formatter:off
/**
 * Tests if PascalRectangle works correctly
 * 0                     1
 * 1                   1   1
 * 2                 1   2   1
 * 3               1   3   3   1
 * 4             1   4   6   4   1
 * 5           1   5   10  10   5   1
 * 6         1   6   15  20  15   6   1
 * 7       1   7   21  35  35   21  7   1
 * 8     1   8   28  56  70  56   28  8   1
 * 9   1   9  36   84  126 126  84  36  9   1
 */
//@formatter:on
public class testPascalRectangle {

	/**
	 * Tests if function returns correct values for borders of triangle, which
	 * should contains only ones
	 */
	@Test
	public void testBorders() {
		for (int i = 0; i < 10; i++) {
			assertEquals("row "+i+" column 0",1, PascalRectangle.pascal(0, i));
			assertEquals("row "+i+" column "+i,1, PascalRectangle.pascal(i, i));
		}
	}

	/**
	 * Tests if function works correctly inside triangle. Checking by recursive
	 * algorithm(every value is sum of two values above).
	 */
	@Test
	public void testInnerValues() {
		for (int i = 0; i < 10; i++)
			for (int j = 1; j < i; j++)
				assertEquals("row "+i+" column "+j,PascalRectangle.pascal(j - 1, i - 1) + PascalRectangle.pascal(j, i - 1),
						PascalRectangle.pascal(j, i));
	}

}
