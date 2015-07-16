package com.msus.gameOfLife;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GameOfLife implements CellulatAutomation {

	Map<List<Integer>, Cell> grid2 = new HashMap<List<Integer>, Cell>();
	List<Integer> dims = new ArrayList<Integer>();

	public GameOfLife(int n, int m) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				ArrayList<Integer> coords = new ArrayList<Integer>();
				coords.add(i);
				coords.add(j);
				grid2.put(coords, new Cell(State.DEAD));
			}
		dims.add(new Integer(n));
		dims.add(new Integer(m));
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (List<Integer> key : grid2.keySet()) {
//			System.out.println(key + " " + grid2.get(key).getState());
			if (grid2.get(key).getState() != State.DEAD) {
				result.append("C"+key+'\n');
			}
		}
		return result.toString();
	}
	
	public List<List<Integer>> toArrayOfState(State state){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (List<Integer> key : grid2.keySet()) {
			if (grid2.get(key).getState() == state) {
				result.add(key);
			}
		}
		return result;
	}

	@Override
	public void setCellState(List<Integer> coords, State state) {
		grid2.put(coords, new Cell(state));
	}

	@Override
	public void next() {
		Map<List<Integer>, Cell> changed = new HashMap<List<Integer>, Cell>();
		for (Entry<List<Integer>, Cell> pair : grid2.entrySet()) {
			Cell tmpCell = (Cell) pair.getValue().clone();
			List<List<Integer>> neighbourhood = getNeighbourhood(pair.getKey());
//			System.out.println("KEY: "+pair);
//			System.out.println(neighbourhood);
			List<Cell> surroundCells = new ArrayList<Cell>();
			for (List<Integer> coords : neighbourhood)
				surroundCells.add(grid2.get(coords));
			tmpCell.next(surroundCells);
			// System.out.println("After "+pair.getKey()+" "+pair.getValue()+"
			// "+tmpCell);
			if (tmpCell.getState() != pair.getValue().getState()) {
				changed.put(pair.getKey(), tmpCell);
			}
		}
		grid2.putAll(changed);
	}
	//
	// List<Cell> next = new ArrayList<Cell>();for(
	// Cell cell:grid)
	//
	// {
	// Cell cloned = (Cell) cell.clone();
	// List<Cell> neighbourhood = getLivingNeighbours(cloned);
	// int sum = neighbourhood.size();
	// System.out.println(cell + " " + sum);
	// if (cloned.getState() == 1 && sum < 2) {
	// cloned.setState(0);
	// // System.out.println("Should stay.");
	// }
	// next.add(cloned);
	// } grid=next;
	//
	// }

	private List<List<Integer>> getNeighbourhood(List<Integer> key) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int dim = dims.size();
		for (int i = 0; i < Math.pow(3, dim); i++) {
			List<Integer> shiftedCoords = new ArrayList<Integer>(dim);
			boolean isNotOutOfBound = true;
			for(int j=0;j<dim;j++){
				int shift = new Double(i / Math.pow(3, j)).intValue() % 3 - 1;
				if(key.get(j)+shift<0 || key.get(j)+shift >= dims.get(j))
					isNotOutOfBound = false;
				shiftedCoords.add(j, key.get(j) +shift);
			}
			if(!shiftedCoords.equals(key) && isNotOutOfBound)
				result.add(shiftedCoords);
		}

//		for (int i = 0; i < key.size(); i++) {
//			for (int j = -1; j <= 1; j += 1) {
//				if (key.get(i) + j >= 0 && key.get(i) + j < dims.get(i)) {
//					List<Integer> tmpCoords = new ArrayList<Integer>(key);
//					tmpCoords.set(i, key.get(i) + j);
//					result.add(tmpCoords);
//				}
//			}
//		}
		return result;
	}

}
