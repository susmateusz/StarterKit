package com.capgemini.pascalrectangle;

//@formatter:off
/**
 * Created by ldrygala on 2015-01-23.
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
public class PascalRectangle {

	/**
	 * Returns value of pascal triangle at c<sup>th</sup> place in r
	 * <sup>th</sup> row. Using iterative algorithm.
	 * 
	 * @param c
	 *            column
	 * @param r
	 *            row
	 * @return pascal triangle point (r,c)
	 */
	public static long pascal(int c, int r) {
		// select lower number. Pascal triangle is symetric
		if (c >= r - c)
			c = r - c;
		long result = 1;
		for (int i = 1, m = r; i <= c; i++, m--)
			result = result * m / i;
		return result;
	}

}
