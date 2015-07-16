package com.msus.GameOfLifeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Map.Entry;

import com.msus.GameOfLifeMVCInterfaces.Model;

public class GameOfLife extends Observable implements CellulatAutomation,Model {

	private Map<List<Integer>, Cell> grid = new HashMap<List<Integer>, Cell>();
	private List<Integer> bounds = new ArrayList<Integer>();

	public GameOfLife(int n, int m) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				ArrayList<Integer> coords = new ArrayList<Integer>();
				coords.add(i);
				coords.add(j);
				grid.put(coords, new Cell(State.DEAD));
			}
		bounds.add(new Integer(n));
		bounds.add(new Integer(m));
	}

	public List<List<Integer>> toArrayOfState(State state) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (List<Integer> key : grid.keySet()) {
			if (grid.get(key).getState() == state) {
				result.add(key);
			}
		}
		return result;
	}

	@Override
	public void setCellState(List<Integer> coords, State state) {
		grid.put(coords, new Cell(state));
	}

	@Override
	public void next() {
		Map<List<Integer>, Cell> changedCells = new HashMap<List<Integer>, Cell>();
		for (Entry<List<Integer>, Cell> pair : grid.entrySet()) {
			Cell tmpCell = (Cell) pair.getValue().clone();
			List<List<Integer>> surroundingCoords = getSurroundingCoords(pair.getKey());
			List<Cell> surroundCells = new ArrayList<Cell>();
			for (List<Integer> coords : surroundingCoords)
				surroundCells.add(grid.get(coords));
			tmpCell.nextState(surroundCells);
			// if state of cell was changed
			if (tmpCell.getState() != pair.getValue().getState()) {
				changedCells.put(pair.getKey(), tmpCell);
			}
		}
		// updating
		grid.putAll(changedCells);
		setChanged();
		notifyObservers(toArrayOfState(State.ALIVE) );
	}

	private List<List<Integer>> getSurroundingCoords(List<Integer> coords) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int dim = bounds.size();
		// 
		for (int i = 0; i < Math.pow(3, dim); i++) {
			List<Integer> shiftedCoords = new ArrayList<Integer>(dim);
			boolean isNotOutOfBound = true;
			for (int j = 0; j < dim; j++) {
				int shift = new Double(i / Math.pow(3, j)).intValue() % 3 - 1;
				if (coords.get(j) + shift < 0 || coords.get(j) + shift >= bounds.get(j))
					isNotOutOfBound = false;
				shiftedCoords.add(j, coords.get(j) + shift);
			}
			if (!shiftedCoords.equals(coords) && isNotOutOfBound)
				result.add(shiftedCoords);
		}
		return result;
	}

	@Override
	public List<List<Integer>> getData() {
		return toArrayOfState(State.ALIVE);
	}
	
	
}
