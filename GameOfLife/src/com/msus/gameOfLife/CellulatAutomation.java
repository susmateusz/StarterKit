package com.msus.gameOfLife;

import java.util.List;

public interface CellulatAutomation {

	void setCellState(List<Integer> coords,int state);

	void next();

	List<List<Integer>> toArrayOfState(int state);

}
