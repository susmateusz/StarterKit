package com.capgemini.taxi;

public interface TaxiModuleCalc {
	
	void releaseTaxi(Taxi t);

	int countTaxiInZone();
	
	Taxi[] setOfTaxiInZone();

	void printSet();

	// stops movement of taxis
	void setStop(boolean stop);
}
