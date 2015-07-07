package com.capgemini.fibonacci;

/**
 * Created by ldrygala on 2015-01-23.<br>
 * F1 F2 F3 F4 F5 F6 F7 F8 F9 F10 F11 F12 F13 F14 F15 F16 F17 F18 F19<br>
 * 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181
 */
public class Fibonacci {
	/**
	 * Returns the n<sup>th</sup> number from Fibonaci sequence. Assume that fib(2),
	 * fib(1), fib(0) fib(-1)... are equal 1.
	 * 
	 * @param n - which number from Fibonacci sequence should be returned.
	 * @return n<sup>th</sup> number from Fibonacci sequence
	 */
	public static long fib(int n) {
		return (n > 2) ? fib(n - 1) + fib(n - 2) : 1;
	}

}
