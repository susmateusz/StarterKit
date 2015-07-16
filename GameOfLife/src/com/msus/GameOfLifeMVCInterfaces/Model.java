package com.msus.GameOfLifeMVCInterfaces;

import java.util.List;
import java.util.Observer;

public interface Model {
	
	void addObserver(Observer o);
	
	List<List<Integer>> getData();
	
	void next();

}
