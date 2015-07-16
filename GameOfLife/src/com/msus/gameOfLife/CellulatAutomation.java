package com.msus.gameOfLife;

import java.util.List;

public interface CellulatAutomation {

	void setCellState(List<Integer> coords,State state);

	void next();

	List<List<Integer>> toArrayOfState(State state);

}
