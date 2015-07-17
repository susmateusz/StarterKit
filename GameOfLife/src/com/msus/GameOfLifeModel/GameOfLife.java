package com.msus.GameOfLifeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;

public class GameOfLife extends Observable implements CellulatAutomation {

	private Map<List<Integer>, Cell> grid = new HashMap<List<Integer>, Cell>();
	private List<Integer> bounds = new ArrayList<Integer>();
	
	public long nextFunctionCounter = 0;

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
		for (List<Integer> coords : grid.keySet()) {
			nextFunctionCounter+=1;
			System.out.println("Next: "+nextFunctionCounter);
			Cell tmpCell = (Cell) grid.get(coords).clone();
			List<List<Integer>> surroundingCoords = getSurroundingCoords(coords);
			List<Cell> surroundCells = new ArrayList<Cell>();
			for (List<Integer> c : surroundingCoords)
				surroundCells.add(grid.get(c));
			tmpCell.nextState(surroundCells);
			// if state of cell was changed
			if (tmpCell.getState() != grid.get(coords).getState()) {
				changedCells.put(coords, tmpCell);
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

	@Override
	public void setCellState(List<Integer> coords) {
		State currentState = grid.get(coords).getState();
		State newState = currentState == State.DEAD ? State.ALIVE : State.DEAD;
		System.out.println("Changing "+currentState+" to "+newState);
		setCellState(coords, newState);
		setChanged();
		notifyObservers(toArrayOfState(State.ALIVE) );
		
	}

	@Override
	public State getCellState(List<Integer> coords) {
		return grid.get(coords).getState();
	}
	
	
}
