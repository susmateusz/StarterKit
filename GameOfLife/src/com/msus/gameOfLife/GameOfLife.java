package com.msus.gameOfLife;

import java.util.ArrayList;
import java.util.List;

public class GameOfLife implements CellulatAutomation {

	List<Cell> grid = new ArrayList<Cell>();

	public GameOfLife(int n, int m) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				grid.add(new Cell(i, j, 0));
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Cell cell : grid)
			result.append(cell.toString() + '\n');
		return result.toString();
	}

	@Override
	public void setAliveCell(int i, int j) {
		Cell newCell = new Cell(i, j, 1);
		for (Cell c : grid)
			if (c.equals(newCell))
				c.setState(1);
	}

	@Override
	public void next() {
		List<Cell> next = new ArrayList<Cell>();
		for (Cell cell : grid) {
			Cell cloned = (Cell) cell.clone();
			List<Cell> neighbourhood = getLivingNeighbours(cloned);
			int sum = neighbourhood.size();
			System.out.println(cell + " " + sum);
			if (cloned.getState()==1 && sum < 2 ) {
				cloned.setState(0);
				// System.out.println("Should stay.");
			}
			next.add(cloned);
		}
		grid = next;

	}

	private List<Cell> getLivingNeighbours(Cell cell) {
		List<Cell> result = new ArrayList<Cell>();
		for (Cell neighbour : grid) {
			if (cell != neighbour && neighbour.getState() != 0 && Math.abs(cell.getX() - neighbour.getX()) <= 1
					&& Math.abs(cell.getY() - neighbour.getY()) <= 1) {
				result.add(neighbour);
				// System.out.println(cell+" "+neighbour);
			}

		}
		return result;
	}

}
