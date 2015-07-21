package singletonTest;

import static org.junit.Assert.*;

import org.junit.Test;

import singletonEnum.SingletonEnum;
import singletonException.PrintSpooler;
import singletonException.SingletonException;
import singletonPrivateClass.Singleton;
import staticMethod.ISpooler;

public class SingletonTests {

	@Test
	public void shouldThrowSingletonException() {
		PrintSpooler pr1,pr2;
		System.out.println("***Test PrintSpooler with exception.");
		System.out.println("Opening spooler.");
		try {
			pr1 = new PrintSpooler();
		} catch (SingletonException e) {
			System.out.println(e.getMessage());
			fail("Should create first object.");
		}
		System.out.println("Opening second spooler.");
		try {
			pr2 = new PrintSpooler();
			fail("Should fail to create second object.");
		} catch (SingletonException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void shouldCreateInstanceOfFinal(){
		System.out.println("***Test PrintSpooler with final.");
//		finalClass.PrintSpooler ps = new finalClass.PrintSpooler();
//		ps.printInstance("final class instance");
		singletonFinalClass.PrintSpooler.print("Test to final class");
	}
	
	@Test
	public void shouldCreateISpooler(){
		System.out.println("***Test of iSpooler.");
		ISpooler isp1 = ISpooler.getInstance();
		ISpooler isp2 = ISpooler.getInstance();
		assertTrue(isp1.equals(isp2));	
	}

	@Test
	public void shouldCreateSingletonFromPrivateStaticClass(){
		Singleton isp1,isp2;
		System.out.println("***Test of singleton in private static class.");
		isp1 = Singleton.getInstance();
		isp2 = Singleton.getInstance();
		assertTrue(isp1.equals(isp2));	
	}

	@Test
	public void shouldCreateSingletonFromEnum(){
		SingletonEnum isp1,isp2;
		System.out.println("***Test of singleton enum.");
		isp1 = SingletonEnum.INSTANCE;
		isp2 = SingletonEnum.INSTANCE;
		assertTrue(isp1.equals(isp2));	
	}
}
