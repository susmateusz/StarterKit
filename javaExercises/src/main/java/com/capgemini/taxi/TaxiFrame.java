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
	public TaxiModuleCalc ntc = new NearestTaxiCalculator();

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
		setBounds(100, 100, 1000, 1000);
		contentPane = new TaxiPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ntc.releaseTaxi(0, 0);
		ntc.releaseTaxi(0, 100);
		ntc.releaseTaxi(100, 0);
		synchronized (contentPane.taxi) {
			contentPane.taxi = ntc.setOfTaxiInZone();
		}
		ActionListener taskPerformer = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				contentPane.repaint();
			}

		};
		new Timer(50, taskPerformer).start();

	}

}
