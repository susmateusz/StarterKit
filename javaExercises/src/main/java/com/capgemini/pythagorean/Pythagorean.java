package com.capgemini.pythagorean;

/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for
 * which, a2 + b2 = c2 For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2. There exists
 * exactly one Pythagorean triplet for which a + b + c = 1000. Find the product
 * abc.
 */
public class Pythagorean {

	public static long findTriplet(long sum) {
		long a, b, c;
		for (long m = 1; m < 100; m++)
			for (long n = 1; n < m; n++)
				for (long k = 1; k < 100; k++) {
					a = k * (m * m - n * n);
					b = k * (2 * m * n);
					c = k * (m * m + n * n);
					if (a + b + c == 1000)
						return a * b * c;
				}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(findTriplet(1000));
	}
}
