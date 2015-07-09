package com.capgemini.taxi;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.taxi.NearestTaxiCalculator;
import com.capgemini.taxi.Taxi;
import com.capgemini.taxi.TaxiModuleCalc;

public class TaxiTest {

	TaxiModuleCalc ntc;

	@Before
	public void initializeNearestTaxiModule() {
		ntc = new NearestTaxiCalculator();
	}
//
//	@Test
//	public void shouldReturn0TaxiWhen0TaxiExists() {
//		assertEquals(0, ntc.countTaxiInZone());
//	}
//
//	@Test
//	public void shouldReturn1TaxiWhenT500_500() {
//		ntc.releaseTaxi(500, 500);
//		assertEquals(1, ntc.countTaxiInZone());
//	}
//
//	@Test
//	public void shouldReturn0TaxiWhenT1000_1000() {
//		ntc.releaseTaxi(1000, 1000);
//		assertEquals(0, ntc.countTaxiInZone());
//	}
//
//	@Test
//	public void shouldReturn1TaxiWhenT500_500ANDT1000_1000() {
//		ntc.releaseTaxi(500, 500);
//		ntc.releaseTaxi(1000, 1000);
//		assertEquals(1, ntc.countTaxiInZone());
//	}
//
//	@Test
//	public void shouldReturnT500_500WhenT500_500ANDT1000_1000() {
//		ntc.releaseTaxi(500, 500);
//		ntc.releaseTaxi(1000, 1000);
//		Taxi[] t1 = new Taxi[] { new Taxi(500, 500) };
//		Taxi[] t2 = ntc.setOfTaxiInZone();
//		assertArrayEquals(t1, t2);
//	}

	@Test
	public void shouldReturn50_05_m50_0m5When55_m55_m5m5_5m5_50_05_m50_0m5_1010() {
		System.out.println("new test");
		ntc.releaseTaxi(500, 500);
		ntc.releaseTaxi(500, 0);
		ntc.releaseTaxi(500, -500);
		ntc.releaseTaxi(0, -500);
		ntc.releaseTaxi(-500, -500);
		ntc.releaseTaxi(-500, 0);
		ntc.releaseTaxi(-500, 500);
		ntc.releaseTaxi(0, 500);
		ntc.releaseTaxi(1000, 1000);
		Taxi[] t1 = new Taxi[] { new Taxi(500, 0), new Taxi(0,500), new Taxi(-500,0), new Taxi(0,-500) };
		ntc.printSet();
		Taxi[] t2 = ntc.setOfTaxiInZone();
		
		assertArrayEquals(t1, t2);
		System.out.println("finished");
	}	
	

}
