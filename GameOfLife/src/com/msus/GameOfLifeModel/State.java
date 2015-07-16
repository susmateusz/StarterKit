package com.msus.GameOfLifeModel;

public enum State {
	DEAD(0),ALIVE(1);
	
	private final int state;

	private State(int state) {
		this.state = state;
	}
	
	public int intValue(){
		return state;
	}
	
	public State getState(int state){
		for(State s : State.values())
			if( s.intValue() == state)
				return s;
		return null;
	}
	
}
