package com.capgemini.taxi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Taxi implements Subject, Runnable {
	private List<Observer> observers = new ArrayList<Observer>();
	private int x, y;

	public Taxi(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Object o) {
		Taxi taxi = (Taxi) o;
		return (this.x * this.x + this.y * this.y) == (taxi.x * taxi.x + taxi.y * taxi.y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getR() {
		return Integer.valueOf((int) Math.sqrt(this.x * this.x + this.y * this.y));
	}

	@Override
	public String toString() {
		return "Taxi [x=" + x + ", y=" + y + ", r=" + Math.sqrt(x * x + y * y) + "]";
	}

	public void notifyObservers() {
		for (Observer o : observers)
			o.notifyObserver();
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void unregisterObserver(Observer observer) {
		observers.remove(observer);
	}

	public void run() {
		Random rand = new Random(10);
		while (true) {
			
		}
	}

}
