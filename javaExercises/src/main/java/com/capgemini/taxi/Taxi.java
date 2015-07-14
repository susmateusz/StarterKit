package com.capgemini.taxi;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Taxi implements Subject, Runnable {
	private static final int TAXI_WIDTH = 20;
	private static final int TAXI_LENGTH = 40;
	private static final long UPDATE_TIME = 500;
	private static final int MAX_SPEED = 10;
	public static int counter = 0;
	private List<Observer> observers = new ArrayList<Observer>();
	private int x, y;
	private int id;
	private double angle;
	public Taxi(int x, int y) {
		this.x = x;
		this.y = y;
		this.id = Taxi.counter++;
		Random rand = new Random();
		angle = rand.nextDouble()%(Math.PI*2);

	}

	public boolean equals(Object o) {
		Taxi taxi = (Taxi) o;
		return (this.x * this.x + this.y * this.y) == (taxi.x * taxi.x + taxi.y * taxi.y);
	}

	synchronized public int getX() {
		return x;
	}

	synchronized public void setX(int x) {
		this.x = x;
	}

	synchronized public int getY() {
		return y;
	}

	synchronized public void setY(int y) {
		this.y = y;
	}

	synchronized public int getR() {
		return Integer.valueOf((int) Math.sqrt(this.x * this.x + this.y * this.y));
	}

	@Override
	public String toString() {
		// return "Taxi [x=" + getX() + ", y=" + getY() + ", r=" +
		// Math.sqrt(getX() * getX() + getY() * getY()) + "]";
		return "T" + id + "(" + getX() + "," + getY() +","+getR()+ ")";
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
		while (true) {
			update();
			notifyObservers();
			try {
				Thread.sleep(Taxi.UPDATE_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	synchronized public void update() {
		Random rand = new Random();
		int dist = rand.nextInt(Taxi.MAX_SPEED);
		angle = 0;
		setX((int)(getX() + dist*Math.cos(angle)));
		setY((int)(getY() + dist*Math.sin(angle)));
		angle += rand.nextDouble()%Math.PI/4-Math.PI/8;
	}

	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x-TAXI_LENGTH, y-TAXI_WIDTH, TAXI_LENGTH, TAXI_WIDTH);
		System.out.println("Komponent");
		
	}




	
//	public void draw(Graphics g) {
//		g.setColor(new Color(150,150,150));
//		Graphics2D g2d = (Graphics2D)g.create();
//		g2d.rotate(angle);
//		g2d.fillRect(x-width/2, y-width/2, width, height);
//		g2d.rotate(-angle);
//		g2d.dispose();
//		
//
//	}

}
