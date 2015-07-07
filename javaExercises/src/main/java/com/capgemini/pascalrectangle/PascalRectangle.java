package com.capgemini.pascalrectangle;

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
public class PascalRectangle {
	/*
	 * pascal c - column r - row
	 */
	public static long pascal(int c, int r) {
		// if (r == 0 || r == c)
		// return 1;
		// else if (r > c - r)
		// return pascal(c, c - r);
		// else
		// return pascal(c - 1, r - 1) + pascal(c - 1, r);
		// select lower number. Pascal triangle is symetric
		if (c >= r - c)
			c = r - c;
		long result = 1;
		for (int i = 1, m = r; i <= c; i++, m--)
			result = result * m / i;
		return result;
	}

	static public void main(String[] args) {
		int rows = 10;
		for (int i = 0; i < rows; i++) {
			System.out.print("\n"+ i + ":  ");
			for (int j = 0; j < i + 1; j++)
				System.out.print(pascal(j, i) + "  ");
		}
	}
}
