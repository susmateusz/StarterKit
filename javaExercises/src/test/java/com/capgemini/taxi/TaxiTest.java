package com.capgemini.taxi;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TaxiTest {

	TaxiModuleCalc ntc;

	@Before
	public void initializeNearestTaxiModule() {
		ntc = new NearestTaxiCalculator();
		ntc.setStop(true);
	}

	@Test
	public void shouldReturn0TaxiWhen0TaxiExists() {
		assertEquals(0, ntc.countTaxiInZone());
	}

	@Test
	public void shouldReturn1TaxiWhenT500_500() {
		ntc.releaseTaxi(500, 500);
		assertEquals(1, ntc.countTaxiInZone());
	}

	@Test
	public void shouldReturn0TaxiWhenT1000_1000() {
		ntc.releaseTaxi(1000, 1000);
		assertEquals(0, ntc.countTaxiInZone());
	}

	@Test
	public void shouldReturn1TaxiWhenT500_500ANDT1000_1000() {
		ntc.releaseTaxi(500, 500);
		ntc.releaseTaxi(1000, 1000);
		assertEquals(1, ntc.countTaxiInZone());
	}

	@Test
	public void shouldReturnT500_500WhenT500_500ANDT1000_1000() {
		ntc.releaseTaxi(500, 500);
		ntc.releaseTaxi(1000, 1000);
		Taxi[] t1 = new Taxi[] { new Taxi(500, 500) };
		Taxi[] t2 = ntc.setOfTaxiInZone();
		assertArrayEquals(t1, t2);
	}

	@Test
	public void shouldReturn50_05_m50_0m5When55_m55_m5m5_5m5_50_05_m50_0m5_1010() {
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
		Taxi[] t2 = ntc.setOfTaxiInZone();
		assertArrayEquals(t1, t2);
		ntc.printSet();
	}	

	@Test
	public void shouldPlay() {
		Taxi.counter = 0;
		ntc.setStop(false);
		ntc.releaseTaxi(500, 500);
		ntc.releaseTaxi(500, 0);
		ntc.releaseTaxi(500, -500);
		ntc.releaseTaxi(0, -500);
		ntc.releaseTaxi(-500, -500);
		ntc.releaseTaxi(-500, 0);
		ntc.releaseTaxi(-500, 500);
		ntc.releaseTaxi(0, 500);
		ntc.releaseTaxi(1000, 1000);
		try {
			Thread.sleep(1*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Taxi t : ntc.setOfTaxiInZone())
			System.out.println(t);
	}	
	

}
