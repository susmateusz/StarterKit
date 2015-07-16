package com.msus.gameOfLife;

import java.util.List;

public class Cell implements Cloneable{
	private int state;
//	private int x;
//	private int y;

	public Cell(int x, int y,int state) {
		this.state = state;
//		this.x = x;
//		this.y = y;
		
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


//	public int getX() {
//		return x;
//	}
//
//	public void setX(int x) {
//		this.x = x;
//	}
//
//	public int getY() {
//		return y;
//	}
//
//	public void setY(int y) {
//		this.y = y;
//	}

	@Override
	public String toString() {
		return "C(" + state +")";
//		return "C(" + x + ", " + y +", "+state+")";
	}

	@Override
	public boolean equals(Object obj) {
		Cell c = (Cell)obj;
		return this.getState()==c.getState();
	}
	
	@Override
	protected Object clone() {
		return new Cell(getState());
	}

	public void next(List<Cell> neighbourhood) {
		int sum = 0;
		for(Cell cell : neighbourhood){
			sum += cell.getState();
		}
		if(sum == 3 || (sum==2 && getState()==1))
			setState(1);
		else
			setState(0);
	}

	
}
