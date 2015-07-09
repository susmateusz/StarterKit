package com.capgemini.taxi;

public class Taxi {

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

	@Override
	public String toString() {
		return "Taxi [x=" + x + ", y=" + y + ", r="+(x*x+y*y)+"]";
	}
	

}
