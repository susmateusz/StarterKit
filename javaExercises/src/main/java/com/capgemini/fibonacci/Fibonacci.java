package com.capgemini.fibonacci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ldrygala on 2015-01-23.
 * F1	F2	F3	F4	F5	F6	F7	F8	F9	F10	F11	F12	F13	F14	F15	F16	F17	  F18	F19
 1	1	2	3	5	8	13	21	34	55	89	144	233	377	610	987	1597  2584	4181
 */
public class Fibonacci {
    public static long fib(int n) {
        return (n > 2) ? fib(n-1)+fib(n-2) : 1;
    }
    
    public static void main(String [] args){
    	System.out.print("Fibonacci\nEnter positive n:");
    	// Reading length of Fibonacci sequence
    	InputStreamReader read = new InputStreamReader(System.in);
    	BufferedReader in = new BufferedReader(read);
    	int n = 0;
    	boolean succeed;
    	do {
    		succeed = true;
    		try {
    			n = Integer.parseInt(in.readLine());
    		} catch (NumberFormatException e){
    			System.err.print("NumberFormatException. Please try again:");
    			succeed = false;
    		}
    		catch(IOException e){
    			System.err.print("IOException. Please try again:");
    			succeed = false;
    		}
    	} while(!succeed);
    	// Printing first line of output
    	for(int i=1;i<=n;i++)
    		System.out.print("F"+i+"\t");
    	System.out.println();
    	for(int i=1;i<=n;i++)
    		System.out.print(fib(i)+"\t");
    }
}
