package com.capgemini.taxi;

import java.net.Socket;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class NearestTaxiCalculator implements TaxiModuleCalc {

	private Set<Taxi> setOfTaxi = new TreeSet<Taxi>(new Comparator<Taxi>() {

		public int compare(Taxi o1, Taxi o2) {
			return o1.getX() * o1.getX() + o1.getY() * o1.getY() - o2.getX() * o2.getX() - o2.getY() * o2.getY();
		}
	});

	public void releaseTaxi(int x, int y) {
		if (x * x + y * y < 1000*1000){
			setOfTaxi.add(new Taxi(x, y));
			System.out.println("Entered for "+x+" "+y);
		} else {
			System.out.println("Failed for "+x+" "+y);
		}
		System.out.println(setOfTaxi.size());
	}

	public int countTaxiInZone() {
		return setOfTaxi.size();
	}

	public Taxi[] setOfTaxiInZone() {
		System.out.println("Length of set:"+setOfTaxi.size());
		return setOfTaxi.toArray(new Taxi[setOfTaxi.size()]);
	}
	
	public void printSet(){
		for(Taxi t : setOfTaxi)
			System.out.println(t);
	}

}
