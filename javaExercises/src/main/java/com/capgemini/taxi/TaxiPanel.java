package com.capgemini.taxi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class TaxiPanel extends JPanel {
	public Taxi[] taxi = new Taxi[0];
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TaxiPanel() {
		super();
//		taxi[0] = new Taxi(0, 0);
//		taxi[1] = new Taxi(0, 100);
//		taxi[2] = new Taxi(100, 0);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		doDrawing(g);
		AffineTransform at = new AffineTransform(1, 0, -100, 0, 1, -100);
		
	}

	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setPaint(new Color(100,100,100));
		g2d.translate(500, 500);
		for(Taxi t: taxi)
			t.draw(g2d);
		g2d.dispose();
		
	}

	public void moveTaxi() {
//		for (Taxi t : taxi) {
//			// t.move();
//		}

	}

}
