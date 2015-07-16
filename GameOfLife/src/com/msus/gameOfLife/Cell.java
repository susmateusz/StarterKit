package com.msus.gameOfLife;

import java.util.List;

public class Cell implements Cloneable{
	private State state;
//	private int x;
//	private int y;

	public Cell(int x, int y,State state) {
		this.state = state;
//		this.x = x;
//		this.y = y;
		
	}
	public Cell(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

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
			sum += cell.getState().intValue();
		}
		if(sum == 3 || (sum==2 && getState()==State.ALIVE))
			setState(State.ALIVE);
		else
			setState(State.DEAD);
	}

	
}
