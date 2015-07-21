package com.msus.GameOfLifeView;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.msus.GameOfLifeMVCInterfaces.Controller;
import com.msus.GameOfLifeMVCInterfaces.Model;
import com.msus.GameOfLifeMVCInterfaces.View;
import com.msus.GameOfLifeModel.CellulatAutomation;
import com.msus.GameOfLifeModel.GOLPattern;
import com.msus.GameOfLifeModel.State;

public class GOLSwingView extends JFrame implements View {

	public static Integer CELL_SIZE = 6;
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
	JButton btnClear;
	JButton btnPrintState;
	JComboBox<GOLPattern> selectPattern;
	JLabel textRotation;
	List<List<Integer>> points;
	protected boolean isSetPattern = false;
	protected int pattern = 0;
	protected int rotation = 0;
	private JButton btnRandom;
	JSlider filling;
	private JTextArea coordsList;
	private JScrollPane scroll;
	private JButton btnLoadState;
	private JLabel labelPos;

	public GOLSwingView(int n, int m) {
		bounds.add(n);
		bounds.add(m);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(10, 10, 26 + bounds.get(1) * CELL_SIZE + 200, 40 +
		// bounds.get(0) * CELL_SIZE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		contentPane = new GamePanel(bounds);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		int menuOrder = 0;

		btnNext = new JButton("Next step");
		btnNext.setBounds(bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * menuOrder++, 200, 30);
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.update(-1);
			}
		});
		contentPane.add(btnNext);

		JLabel labelSpeed = new JLabel("FPS");
		labelSpeed.setBounds(bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * menuOrder++, 200, 30);
		contentPane.add(labelSpeed);

		textSpeed = new JTextField("50");
		textSpeed.setBounds(bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * menuOrder++, 200, 30);
		contentPane.add(textSpeed);

		btnStart = new JButton("Start game / load speed");
		btnStart.setBounds(bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * menuOrder++, 200, 30);
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.update(1000 / Integer.parseInt(textSpeed.getText()));
			}
		});
		contentPane.add(btnStart);

		btnStop = new JButton("Stop game");
		btnStop.setBounds(bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * menuOrder++, 200, 30);
		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.update(0);
			}
		});
		contentPane.add(btnStop);

		btnClear = new JButton("Clear board");
		btnClear.setBounds(bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * menuOrder++, 200, 30);
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (List<Integer> coords : active)
					model.setCellState(coords, State.DEAD);
				contentPane.clear();
				controller.update(-1);
			}
		});
		contentPane.add(btnClear);

		selectPattern = new JComboBox<GOLPattern>(GOLPattern.values());
		selectPattern.setBounds(bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * menuOrder++, 200, 30);
		selectPattern.setBackground(Color.WHITE);
		contentPane.add(selectPattern);
		
		labelPos = new JLabel(new String("Cursor pos:\t(?,\t?)"));
		labelPos.setBounds(bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * menuOrder++, 200, 30);
		contentPane.add(labelPos);

		textRotation = new JLabel("Rotation: " + (rotation * 90) + " degrees.[press 'R']");
		textRotation.setBounds(bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * menuOrder++, 200, 30);
		contentPane.add(textRotation);

		filling = new JSlider(0, 100, 50);
		filling.setBounds(bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * menuOrder++, 200, 30);
		filling.setMajorTickSpacing(20);
		filling.setPaintLabels(true);

		contentPane.add(filling);

		btnRandom = new JButton("Random");
		btnRandom.setBounds(bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * menuOrder++, 200, 30);
		btnRandom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer fillingRate = filling.getValue();
				Random rand = new Random();
				for (int i = 0; i < bounds.get(0); i++) {
					for (int j = 0; j < bounds.get(1); j++) {
						List<Integer> coords = new ArrayList<Integer>(Arrays.asList(new Integer[] { i, j }));
						int random = rand.nextInt(100);
						if (random < fillingRate)
							model.setCellState(coords, State.ALIVE);
					}
				}
				model.hasChanged();
				model.notifyObservers(model.toArrayOfState(State.ALIVE));
				active = model.getData();
				print();
			}
		});
		contentPane.add(btnRandom);

		btnPrintState = new JButton("Print State");
		btnPrintState.setBounds(bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * menuOrder++, 200, 30);
		btnPrintState.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				coordsList.setText(null);
				for (List<Integer> coords : active)
					coordsList.append(coords.toString() + "\n");
			}
		});
		contentPane.add(btnPrintState);

		int rows = 8;
		coordsList = new JTextArea(20, 1);
		coordsList.setLineWrap(true);
		System.out.println(coordsList.getRows() + " " + coordsList.getFont().getSize());
		scroll = new JScrollPane(coordsList);
		scroll.setBounds(bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * menuOrder++, 200, rows * FIELDS_HEIGHT);
		contentPane.add(scroll);
		menuOrder += rows - 1;

		btnLoadState = new JButton("Load State");
		btnLoadState.setBounds(bounds.get(1) * CELL_SIZE, FIELDS_HEIGHT * menuOrder++, 200, 30);
		btnLoadState.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (List<Integer> coords : active)
					model.setCellState(coords, State.DEAD);
				for (String line : coordsList.getText().split("\n")) {
					line = line.replaceAll("[^0-9 ]", "");
					List<Integer> coords = new ArrayList<Integer>();
					for(String numberInStr : line.split(" "))
						coords.add(Integer.parseInt(numberInStr.trim()));
					model.setCellState(coords, State.ALIVE);
				}
				model.hasChanged();
				model.notifyObservers(model.toArrayOfState(State.ALIVE));
				active = model.getData();
				print();
			}
		});
		contentPane.add(btnLoadState);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				points = new ArrayList<List<Integer>>();
				List<Integer> currentCoords = new ArrayList<Integer>();
				currentCoords.add((e.getY() - 30) / CELL_SIZE);
				currentCoords.add((e.getX() - 8) / CELL_SIZE);
				if (currentCoords.get(0) < bounds.get(0) && currentCoords.get(1) < bounds.get(1)) {
					((GOLPattern) selectPattern.getSelectedItem()).draw(model, currentCoords, rotation);
					model.hasChanged();
					model.notifyObservers(model.toArrayOfState(State.ALIVE));
					active = model.getData();
					print();
				}
				points.add(currentCoords);
			}

		});

		setBounds(10, 10, 26 + bounds.get(1) * CELL_SIZE + 200,
				40 + Math.max(bounds.get(0) * CELL_SIZE, FIELDS_HEIGHT * menuOrder));

		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new GameDispatcher());

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

	private class GameDispatcher implements KeyEventDispatcher {

		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == 82) {
				rotation = (rotation + 1) % 4;
				textRotation.setText("Rotation: " + (rotation * 90) + " degrees.[press 'R']");
			}
			return false;
		}

	}

}
