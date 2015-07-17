package com.msus.GameOfLifeMVCInterfaces;

import java.util.Observer;

public interface Controller extends Observer {

	void setModel(Model model);

	void setView(View view);

	void nextMoveEvent();

	boolean isGameFinished();

	void setGameFinished(boolean bool);

	public void update(Integer speed);

}
