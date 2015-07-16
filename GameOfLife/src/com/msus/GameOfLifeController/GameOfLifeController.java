package com.msus.GameOfLifeController;

import java.util.List;

import com.msus.GameOfLifeMVCInterfaces.Controller;
import com.msus.GameOfLifeMVCInterfaces.Model;
import com.msus.GameOfLifeMVCInterfaces.View;

public class GameOfLifeController implements Controller {

	private Model model;

	private View view;

	private boolean isGameFinished = false;

	public GameOfLifeController(Model model, View view) {
		super();
		this.model = model;
		this.view = view;
		model.addObserver(view);
		view.setModel(model);
		view.print();
	}

	@Override
	public void setModel(Model model) {
		this.model = model;
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}

	@Override
	public void nextMoveEvent() {
		List<List<Integer>> currentFrame = model.getData();
		if (!isGameFinished())
			model.next();
		List<List<Integer>> nextFrame = model.getData();
		if (currentFrame.containsAll(nextFrame) && nextFrame.containsAll(currentFrame)){
			setGameFinished(true);
			return;
		}
		view.print();
	}

	public boolean isGameFinished() {
		return isGameFinished;
	}

	public void setGameFinished(boolean isGameFinished) {
		this.isGameFinished = isGameFinished;
	}

}
