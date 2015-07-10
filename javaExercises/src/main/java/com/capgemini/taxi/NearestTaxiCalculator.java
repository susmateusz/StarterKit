package com.capgemini.taxi;

import java.awt.Container;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NearestTaxiCalculator implements TaxiModuleCalc, Observer {

	private static int MAX_TAXI = 4;
	private List<Taxi> taxiList = Collections.synchronizedList(new ArrayList<Taxi>());
	private ExecutorService exec = Executors.newCachedThreadPool(new TaxiFactory("Taxes"));
	private boolean stop = false;

	public NearestTaxiCalculator() {

	}

	public void releaseTaxi(int x, int y) {
		Taxi t = new Taxi(x, y);
		t.registerObserver(this);
		taxiList.add(t);
		if (!stop)
			exec.execute(t);
	}

	public int countTaxiInZone() {
		int result = 0;
		for (Taxi t : taxiList)
			if (t.getR() < 1000)
				++result;
		return result;
	}

	public Taxi[] setOfTaxiInZone() {
		Collections.sort(taxiList, new Comparator<Taxi>() {

			public int compare(Taxi o1, Taxi o2) {
				return o1.getX() * o1.getX() + o1.getY() * o1.getY() - o2.getX() * o2.getX() - o2.getY() * o2.getY();
			}
		});
		int howMuchReturn = Math.min(countTaxiInZone(), MAX_TAXI);
		return taxiList.subList(0, howMuchReturn).toArray(new Taxi[Math.min(countTaxiInZone(), MAX_TAXI)]);
	}

	public void printSet() {
		for (Taxi t : taxiList)
			System.out.println(t);
	}

	synchronized public void notifyObserver() {
		System.out.println("All taxes:");
		System.out.println(taxiList);
		System.out.println("Nearest taxes:");
		for (Taxi t : setOfTaxiInZone())
			System.out.print(t);
		System.out.println();
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

}
