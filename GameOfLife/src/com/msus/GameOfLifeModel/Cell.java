package com.msus.GameOfLifeModel;

import java.util.List;

public class Cell implements Cloneable{
	private State state;

	public Cell(int x, int y,State state) {
		this.state = state;
		
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
	}
	
	@Override
	protected Object clone() {
		return new Cell(getState());
	}

	public void nextState(List<Cell> neighbourhood) {
		int sum = 0;
		for(Cell cell : neighbourhood){
			sum += cell.getState().intValue();
		}
		if (shouldDie(sum))
			setState(State.DEAD);
		else if ( shouldBorn(sum))
			setState(State.ALIVE);
	}
	
	private boolean shouldDie(int sum){
		return getState()==State.ALIVE && sum!=2 && sum !=3;
		//return getState()==State.ALIVE && sum!=2 && sum != 3 && sum !=4;
	}
	
	private boolean shouldBorn(int sum){
		return getState()==State.DEAD && sum == 3;
	}

	
}
