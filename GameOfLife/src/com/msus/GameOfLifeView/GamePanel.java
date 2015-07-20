package com.msus.GameOfLifeView;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Integer> bounds;
	List<List<Integer>> active;
	
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
		g.setColor(new Color(200,200,200));
		// board background
		g.fillRect(0,0,bounds.get(1)*GOLSwingView.CELL_SIZE, bounds.get(0)*GOLSwingView.CELL_SIZE);
		// drawing cols and rows
		g.setColor(defaultColor);
		for (int i = 0; i < bounds.get(1); i++) 
			g.fillRect((i+1)*GOLSwingView.CELL_SIZE-2, 0, 2, bounds.get(0)*GOLSwingView.CELL_SIZE);
		for (int j = 0; j < bounds.get(0); j++) 
			g.fillRect(0,(j+1)*GOLSwingView.CELL_SIZE-2, bounds.get(1)*GOLSwingView.CELL_SIZE,2);
		// drawing active cells
		g.setColor(new Color(20,20,20));
		for(List<Integer> cell : active)
			g.fillRect(cell.get(1) * GOLSwingView.CELL_SIZE, cell.get(0) * GOLSwingView.CELL_SIZE, GOLSwingView.CELL_SIZE - 2,
					GOLSwingView.CELL_SIZE - 2);			
		
//		for (int i = 0; i < bounds.get(1); i++) {
//			for (int j = 0; j < bounds.get(0); j++) {
//				g.setColor(new Color(200,200,200));
//				if(active.contains(Arrays.asList(new Integer[]{j,i})))
//					g.setColor(new Color(20,20,20));
//				g.fillRect(i*GOLSwingView.CELL_SIZE, j*GOLSwingView.CELL_SIZE, GOLSwingView.CELL_SIZE-2, GOLSwingView.CELL_SIZE-2);
//			}
//		}

	
	}
	
	

}
