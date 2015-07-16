package com.msus.GameOfLifeView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.msus.GameOfLifeMVCInterfaces.Model;
import com.msus.GameOfLifeMVCInterfaces.View;

public class GOLSwingView extends JFrame implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GamePanel contentPane;
	private List<List<Integer>> active = new ArrayList<List<Integer>>();
	private List<Integer> bounds = new ArrayList<Integer>();

	public GOLSwingView(int n, int m) {
		bounds.add(n);
		bounds.add(m);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, (1+bounds.get(1)) * 20, (2+bounds.get(0)) * 20);
		contentPane = new GamePanel(bounds);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		System.out.println("DUDUD");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		if (arg.getClass().isInstance(active)) {
			active = (List<List<Integer>>) arg;
		}
	}

	@Override
	public void setModel(Model model) {
		active = model.getData();
	}

	@Override
	public void print() {
		System.out.println("Inside");
		contentPane.addAll(active);
		contentPane.repaint();
	}

}
