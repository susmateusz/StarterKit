package com.capgemini.taxi;

public interface TaxiModuleCalc {
	
	void releaseTaxi(int x,int y);

	int countTaxiInZone();
	
	Taxi[] setOfTaxiInZone();

	void printSet();
}
