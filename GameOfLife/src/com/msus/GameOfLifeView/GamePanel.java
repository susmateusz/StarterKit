package com.msus.GameOfLifeView;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Integer> bounds;
	private List<List<Integer>> active;
	private Set<List<Integer>> dying = new HashSet<List<Integer>>();
	private Color deadCellColor = new Color(200,200,200);
	private Color dyingCellColor = new Color(170,170,170);
	private Color activeCellColor = new Color(0,0,0);
	public ShadowMode shadowMode = ShadowMode.FULL_TRACE;
	
	public GamePanel(List<Integer> bounds) {
		super(true);
//		super(true);
		this.bounds = bounds;
	}

	public void addAll(List<List<Integer>> active) {
		this.active = active;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// color between rows and cols
		Color defaultColor = getBackground();
		// color of empty cell
		g.setColor(deadCellColor);
		// board background
		g.fillRect(0,0,bounds.get(1)*GOLSwingView.CELL_SIZE, bounds.get(0)*GOLSwingView.CELL_SIZE);
		// drawing cols and rows
		g.setColor(defaultColor);
		for (int i = 0; i < bounds.get(1); i++) 
			g.fillRect((i+1)*GOLSwingView.CELL_SIZE-2, 0, 2, bounds.get(0)*GOLSwingView.CELL_SIZE);
		for (int j = 0; j < bounds.get(0); j++) 
			g.fillRect(0,(j+1)*GOLSwingView.CELL_SIZE-2, bounds.get(1)*GOLSwingView.CELL_SIZE,2);
		// drawing dying cells
		g.setColor(dyingCellColor);
		for(List<Integer> cell : dying)
			g.fillRect(cell.get(1) * GOLSwingView.CELL_SIZE, cell.get(0) * GOLSwingView.CELL_SIZE, GOLSwingView.CELL_SIZE - 2,
					GOLSwingView.CELL_SIZE - 2);
		// drawing active cells
		g.setColor(activeCellColor);
		for(List<Integer> cell : active)
			g.fillRect(cell.get(1) * GOLSwingView.CELL_SIZE, cell.get(0) * GOLSwingView.CELL_SIZE, GOLSwingView.CELL_SIZE - 2,
					GOLSwingView.CELL_SIZE - 2);	
		
		switch(shadowMode){
		case ONLY_DYING:
			dying = new HashSet<List<Integer>>(active);
			break;
		case FULL_TRACE:
			dying.addAll(active);
			break;
		case NO_SHADOW:
			break;
		}

	
	}
	
	
	public void clear(){
		dying = new HashSet<List<Integer>>();
	}
	

}
