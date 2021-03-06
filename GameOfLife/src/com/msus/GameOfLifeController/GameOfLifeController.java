package com.msus.GameOfLifeController;

import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;

import javax.swing.Timer;

import com.msus.GameOfLifeMVCInterfaces.Controller;
import com.msus.GameOfLifeMVCInterfaces.Model;
import com.msus.GameOfLifeMVCInterfaces.View;
import com.msus.GameOfLifeModel.GameOfLife;
import com.msus.GameOfLifeView.GOLSwingView;

public class GameOfLifeController implements Controller {

	private Model model;

	private View view;

	private boolean isGameFinished = false;

	private int speed = 300;

	private Timer timer = new Timer(speed, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (speed > 0) {
				nextMoveEvent();
				timer.setDelay(speed);
			} else
				timer.stop();
		}
	});

	public GameOfLifeController() {
		Integer size_y = null;
//		while (size_y == null || size_y < 1) {
//			try{
//			size_y = Integer.parseInt(JOptionPane.showInputDialog(null, "Size y ( int and >=1):"));
//			} catch(NumberFormatException e){
//				JOptionPane.showMessageDialog(null, "Wrong number format. Please enter positive integer.");
//			}
//		}
		Integer size_x = null;
//		while (size_x == null || size_x < 1) {
//			try{
//				size_x = Integer.parseInt(JOptionPane.showInputDialog(null, "Size x ( int and >=1):"));
//			} catch(NumberFormatException e){
//				JOptionPane.showMessageDialog(null, "Wrong number format. Please enter positive integer.");
//			}
//		}
		size_y = 80;
		size_x = 120;
		int width = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
		int height = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();
		GOLSwingView.CELL_SIZE = Math.min((width - 226) / size_x, (height - 40) / size_y);
		model = new GameOfLife(size_y, size_x);
		try {
			// Necessary when creating model takes too much time, if commented
			// then for 120x180 throws nullPointException in first frame
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		view = new GOLSwingView(size_y, size_x);
		model.addObserver(view);
		model.addObserver(this);
		view.setModel(model);
		view.setController(this);
		view.print();
		initialize();
		timer.start();

	}

	public GameOfLifeController(Model model, View view) {
		super();
		this.model = model;
		this.view = view;
		model.addObserver(view);
		model.addObserver(this);
		view.setModel(model);
		view.setController(this);
		view.print();
		initialize();
		timer.start();

	}

	public GameOfLifeController(Integer size_y, Integer size_x) {
		model = new GameOfLife(size_y, size_x);
		view = new GOLSwingView(size_y, size_x);
		model.addObserver(view);
		model.addObserver(this);
		view.setModel(model);
		view.setController(this);
		view.print();
		initialize();
		timer.start();

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
		if (currentFrame.containsAll(nextFrame) && nextFrame.containsAll(currentFrame)) {
			update(0);
			// return;
		}
		view.print();
	}

	public boolean isGameFinished() {
		return isGameFinished;
	}

	public void setSpeed(int i) {
		this.speed = i;
	}

	private void initialize() {
		// nextMoveEvent();
		while (!isGameFinished()) {
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				System.out.println("Exception.");
				e.printStackTrace();
			}
		}
	}

	public void update(Integer speed) {
		System.out.println(speed);
		if (speed == -1)
			nextMoveEvent();
		else if (speed == 0) {
			timer.stop();
		} else {
			setSpeed(speed);
			timer.start();
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		view.print();
	}

	@Override
	public void setGameFinished(boolean bool) {
		isGameFinished = true;
	}

}
