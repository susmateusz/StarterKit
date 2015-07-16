package com.msus.GameOfLifeController;

import java.util.List;

import com.msus.GameOfLifeMVCInterfaces.Controller;
import com.msus.GameOfLifeMVCInterfaces.Model;
import com.msus.GameOfLifeMVCInterfaces.View;

public class GameOfLifeController implements Controller {

	private Model model;

	private View view;

	private boolean isGameFinished = false;
	
	private int speed=300;

	public GameOfLifeController(Model model, View view) {
		super();
		this.model = model;
		this.view = view;
		model.addObserver(view);
		view.setModel(model);
		view.print();
		try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isGameFinished() {
		return isGameFinished;
	}

	public void setGameFinished(boolean isGameFinished) {
		this.isGameFinished = isGameFinished;
	}

	public void setSpeed(int i) {
		this.speed = i;
	}

	public void start() {
		while (!isGameFinished()){
			System.out.println(speed);
			nextMoveEvent();
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
