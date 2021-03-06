package com.msus.GameOfLifeModel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum GOLPattern {
	EMPTY, GLIDER, TOAD, LIGHTWEIGHT_SPACESHIP, GOSPER_GLIDER_GUN, INFINITE1, INFINITE2;

	List<List<Integer>> points;

	public Set<Integer[]> getPoints(List<Integer> currentCoords, int rot){
		Set<Integer[]> points = new HashSet<Integer[]>();
		if (this == GOLPattern.EMPTY) {
			points.add(new Integer[] { 0, 0 });
		} else if (this == GOLPattern.GLIDER) {
			points.add(new Integer[] { 0, 1 });
			points.add(new Integer[] { 1, 2 });
			points.add(new Integer[] { 2, 0 });
			points.add(new Integer[] { 2, 1 });
			points.add(new Integer[] { 2, 2 });
		} else if (this == GOLPattern.TOAD) {
			points.add(new Integer[] { 0, 1 });
			points.add(new Integer[] { 0, 2 });
			points.add(new Integer[] { 0, 3 });
			points.add(new Integer[] { 1, 0 });
			points.add(new Integer[] { 1, 1 });
			points.add(new Integer[] { 1, 2 });
		} else if (this == GOLPattern.LIGHTWEIGHT_SPACESHIP) {
			points.add(new Integer[] { 0, 1 });
			points.add(new Integer[] { 0, 4 });
			points.add(new Integer[] { 1, 0 });
			points.add(new Integer[] { 2, 0 });
			points.add(new Integer[] { 2, 4 });
			points.add(new Integer[] { 3, 0 });
			points.add(new Integer[] { 3, 1 });
			points.add(new Integer[] { 3, 2 });
			points.add(new Integer[] { 3, 3 });
		} else if (this == GOLPattern.GOSPER_GLIDER_GUN) {
			points.add(new Integer[] { 0, 0 });
			points.add(new Integer[] { 0, 1 });
			points.add(new Integer[] { 1, 0 });
			points.add(new Integer[] { 1, 1 });
			points.add(new Integer[] { 0, 10 });
			points.add(new Integer[] { 1, 10 });
			points.add(new Integer[] { 2, 10 });
			points.add(new Integer[] { -1, 11 });
			points.add(new Integer[] { 3, 11 });
			points.add(new Integer[] { -2, 12 });
			points.add(new Integer[] { 4, 12 });
			points.add(new Integer[] { -2, 13 });
			points.add(new Integer[] { 4, 13 });
			points.add(new Integer[] { 1, 14 });
			points.add(new Integer[] { -1, 15 });
			points.add(new Integer[] { 3, 15 });
			points.add(new Integer[] { 0, 16 });
			points.add(new Integer[] { 1, 16 });
			points.add(new Integer[] { 2, 16 });
			points.add(new Integer[] { 1, 17 });
			points.add(new Integer[] { -2, 20 });
			points.add(new Integer[] { -1, 20 });
			points.add(new Integer[] { 0, 20 });
			points.add(new Integer[] { -2, 21 });
			points.add(new Integer[] { -1, 21 });
			points.add(new Integer[] { 0, 21 });
			points.add(new Integer[] { -3, 22 });
			points.add(new Integer[] { 1, 22 });
			points.add(new Integer[] { -4, 24 });
			points.add(new Integer[] { -3, 24 });
			points.add(new Integer[] { 1, 24 });
			points.add(new Integer[] { 2, 24 });
			points.add(new Integer[] { -2, 34 });
			points.add(new Integer[] { -1, 34 });
			points.add(new Integer[] { -2, 35 });
			points.add(new Integer[] { -1, 35 });
		} else if (this == GOLPattern.INFINITE1) {
			points.add(new Integer[] { 0, 0 });
			points.add(new Integer[] { 0, 2 });
			points.add(new Integer[] { -1, 2 });
			points.add(new Integer[] { -2, 4 });
			points.add(new Integer[] { -3, 4 });
			points.add(new Integer[] { -4, 4 });
			points.add(new Integer[] { -3, 6 });
			points.add(new Integer[] { -4, 6 });
			points.add(new Integer[] { -5, 6 });
			points.add(new Integer[] { -4, 7 });
		} else if (this == GOLPattern.INFINITE2) {
			points.add(new Integer[] { 0, 0 });
			points.add(new Integer[] { 0, 1 });
			points.add(new Integer[] { 0, 2 });
			points.add(new Integer[] { 0, 4 });
			points.add(new Integer[] { 1, 0 });
			points.add(new Integer[] { 2, 3 });
			points.add(new Integer[] { 2, 4 });
			points.add(new Integer[] { 3, 1 });
			points.add(new Integer[] { 3, 2 });
			points.add(new Integer[] { 3, 4 });
			points.add(new Integer[] { 4, 0 });
			points.add(new Integer[] { 4, 2 });
			points.add(new Integer[] { 4, 4 });
		}
		for (Integer[] p : points) {
			int p0 = p[0];
			int p1 = p[1];
			p[0] = p0 * (int) Math.cos(Math.PI * rot / 2) - p1 * (int) Math.sin(Math.PI * rot / 2);
			p[0] += currentCoords.get(0);
			p[1] = p0 * (int) Math.sin(Math.PI * rot / 2) + p1 * (int) Math.cos(Math.PI * rot / 2);
			p[1] += currentCoords.get(1);
		}
		return points;
	}
	public void draw(CellulatAutomation model, List<Integer> currentCoords, int rot) {
		State expectedState = State.ALIVE;
		if( this == GOLPattern.EMPTY && model.getCellState(currentCoords) == State.ALIVE)
			expectedState = State.DEAD;
		Set<Integer[]> points = getPoints(currentCoords, rot);
		for (Integer[] p : points) {
			model.setCellState(Arrays.asList(p), expectedState);
		}
	}

}
