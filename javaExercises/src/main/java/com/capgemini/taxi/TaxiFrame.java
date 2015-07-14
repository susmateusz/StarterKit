package com.capgemini.taxi;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class TaxiFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TaxiPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaxiFrame frame = new TaxiFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TaxiFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new TaxiPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Taxi taxi = new Taxi(100,200);
		contentPane.addTaxi(taxi);
		Timer timer = new Timer(1000,new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				contentPane.updatePositions();
				contentPane.repaint();
				System.out.println("refreshed");
			}
		});
		timer.start();
		
	}

}
