package com.msus.GameOfLifeModel;

import java.util.List;

import com.msus.GameOfLifeMVCInterfaces.Model;

public interface CellulatAutomation extends Model {

	void setCellState(List<Integer> coords,State state);

	void setCellState(List<Integer> coords);

	State getCellState(List<Integer> coords);
	void next();

	List<List<Integer>> toArrayOfState(State state);
	
	boolean hasChanged();
	void notifyObservers(Object arg);

}
