package com.capgemini.taxi;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class TaxiPanel extends JPanel {

	List<Taxi> taxies	= new ArrayList<Taxi>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Taxi t : taxies)
			t.draw(g);
	}

	public void addTaxi(Taxi taxi) {
		taxies.add(taxi);
	}

	public void updatePositions() {
		for(Taxi t : taxies)
			t.update();
	}
	
	

}
