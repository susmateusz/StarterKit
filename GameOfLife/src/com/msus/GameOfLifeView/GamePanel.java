package com.msus.GameOfLifeView;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
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
		super();
		this.bounds = bounds;
	}

	public void addAll(List<List<Integer>> active) {
		this.active = active;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("Painting.");
		for (int i = 0; i < bounds.get(1); i++) {
			for (int j = 0; j < bounds.get(0); j++) {
				g.setColor(new Color(200,200,200));
				if(active.contains(Arrays.asList(new Integer[]{j,i})))
					g.setColor(new Color(20,20,20));
				g.fillRect(i*20, j*20, 18, 18);
			}
		}
		g.dispose();
	}
	
	

}
