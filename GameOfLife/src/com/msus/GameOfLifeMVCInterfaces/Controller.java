package com.msus.GameOfLifeMVCInterfaces;

public interface Controller {
	
	void setModel(Model model);
	
	void setView(View view);
	
	void nextMoveEvent();
	
	boolean isGameFinished();
	
	
}
