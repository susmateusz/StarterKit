package com.msus.gameOfLife;

public class Cell implements Cloneable{
	private int state;
	private int x;
	private int y;

	public Cell(int x, int y,int state) {
		this.state = state;
		this.x = x;
		this.y = y;
		
	}
	public Cell(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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
		return "C(" + x + ", " + y +")";
//		return "C(" + x + ", " + y +", "+state+")";
	}

	@Override
	public boolean equals(Object obj) {
		Cell c = (Cell)obj;
		return this.x==c.x && this.y==c.y;
	}
	
	@Override
	protected Object clone() {
		return new Cell(getX(),getY(),getState());
	}

	
}
