package com.msus.GameOfLifeView;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.msus.GameOfLifeMVCInterfaces.Controller;
import com.msus.GameOfLifeMVCInterfaces.Model;
import com.msus.GameOfLifeMVCInterfaces.View;
import com.msus.GameOfLifeModel.CellulatAutomation;
import com.msus.GameOfLifeModel.GOLPattern;
import com.msus.GameOfLifeModel.State;

public class GOLSwingView extends JFrame implements View {

	public static Integer CELL_SIZE = 10;
	public static Integer FIELDS_HEIGHT = 30;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GamePanel contentPane;
	public CellulatAutomation model;
	private List<List<Integer>> active = new ArrayList<List<Integer>>();
	private List<Integer> bounds = new ArrayList<Integer>();
	private Controller controller;
	JButton btnNext;
	JTextField textSpeed;
	JButton btnStart;
	JButton btnStop;
	JButton btnAddGlider;
	JComboBox<GOLPattern> selectPattern;
	protected boolean isSetPattern = false;
	protected int pattern = 0;

	public GOLSwingView(int n, int m) {
		bounds.add(n);
		bounds.add(m);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 26 + bounds.get(1) * CELL_SIZE + 200, 40 + bounds.get(0) * CELL_SIZE);
		contentPane = new GamePanel(bounds);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		btnNext = new JButton("Next step");
		btnNext.setBounds(CELL_SIZE / 2 + bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * 0, 200, 30);
		System.out.println("repaint");
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.update(-1);
			}
		});
		contentPane.add(btnNext);

		JLabel labelSpeed = new JLabel("FPS");
		labelSpeed.setBounds(CELL_SIZE / 2 + bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * 1, 200, 30);
		contentPane.add(labelSpeed);
		textSpeed = new JTextField("50");
		textSpeed.setBounds(CELL_SIZE / 2 + bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * 2, 200, 30);
		contentPane.add(textSpeed);

		btnStart = new JButton("Start game / load speed");
		btnStart.setBounds(CELL_SIZE / 2 + bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * 3, 200, 30);
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.update(1000 / Integer.parseInt(textSpeed.getText()));
			}
		});
		contentPane.add(btnStart);

		btnStop = new JButton("Stop game");
		btnStop.setBounds(CELL_SIZE / 2 + bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * 4, 200, 30);
		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.update(0);
			}
		});
		contentPane.add(btnStop);

		btnStop = new JButton("Clear board");
		btnStop.setBounds(CELL_SIZE / 2 + bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * 5, 200, 30);
		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (List<Integer> coords : active)
					model.setCellState(coords, State.DEAD);
				controller.update(-1);
			}
		});
		contentPane.add(btnStop);

		selectPattern = new JComboBox<GOLPattern>(GOLPattern.values());
		selectPattern.setBounds(CELL_SIZE / 2 + bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * 6, 200, 30);
		selectPattern.setBackground(Color.WHITE);
		contentPane.add(selectPattern);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// int n = (e.getY() - 3 * CELL_SIZE / 2) / CELL_SIZE;
				// int m = (e.getX() - 4 * CELL_SIZE / 10) / CELL_SIZE;
				List<Integer> currentCoords = new ArrayList<Integer>();
				currentCoords.add((e.getY() - 30) / CELL_SIZE);
				currentCoords.add((e.getX() - 8) / CELL_SIZE);
				((GOLPattern) selectPattern.getSelectedItem()).draw(model,currentCoords);
				model.hasChanged();
				model.notifyObservers(model.toArrayOfState(State.ALIVE));
				active = model.getData();
				print();

				// }

			}

		});

		addSliders();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				controller.setGameFinished(true);
				super.windowClosing(e);
			}

		});

	}

	private void addSliders() {
		//
		// btnStop = new JButton("Stop game");
		// btnStop.setBounds(CELL_SIZE / 2 + bounds.get(1) * CELL_SIZE,
		// FIELDS_HEIGHT * 4, 200, 30);
		// btnStop.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// controller.update(0);
		// }
		// });
		// contentPane.add(btnStop);
		//
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		if (arg.getClass().isInstance(active)) {
			active = (List<List<Integer>>) arg;
		}
		print();
	}

	@Override
	public void setModel(Model model) {
		active = model.getData();
		this.model = (CellulatAutomation) model;
	}

	@Override
	public void print() {
		contentPane.addAll(active);
		contentPane.repaint();
	}

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

}
