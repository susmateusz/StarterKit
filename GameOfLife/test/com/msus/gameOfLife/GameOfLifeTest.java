package com.msus.gameOfLife;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class GameOfLifeTest {

	@Test
	public void shouldReturnEmptyBoardWhenEmptyInput() {
		CellulatAutomation gol = new GameOfLife(3, 3);
		gol.next();
		String expected = new String("");
		assertEquals(expected, gol.toString());
	}

	@Test
	public void shouldReturnEmptyWhen000_010_000() {
		CellulatAutomation gol = new GameOfLife(3, 3);
		gol.setCellState(Arrays.asList(new Integer[] { 1, 1 }), State.ALIVE);
		gol.next();
		String expected = new String("");
		assertEquals(expected, gol.toString());
	}

	@Test
	public void shouldReturnEmptyWhen000_011_000() {
		CellulatAutomation gol = new GameOfLife(3, 3);
		gol.setCellState(Arrays.asList(new Integer[] { 1, 1 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 1, 2 }), State.ALIVE);
		gol.next();
		String expected = new String("");
		assertEquals(expected, gol.toString());
	}

	@Test
	public void shouldReturn000_011_011When000_011_011() {
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		expected.add(Arrays.asList(new Integer[] { 1, 1 }));
		expected.add(Arrays.asList(new Integer[] { 1, 2 }));
		expected.add(Arrays.asList(new Integer[] { 2, 1 }));
		expected.add(Arrays.asList(new Integer[] { 2, 2 }));
		CellulatAutomation gol = new GameOfLife(3, 3);
		for (List<Integer> cell : expected)
			gol.setCellState(cell, State.ALIVE);
		gol.next();
		List<List<Integer>> result = gol.toArrayOfState(State.ALIVE);
		assertTrue(expected.containsAll(result) && result.containsAll(expected));
	}

	@Test
	public void shouldReturn110_101_010When110_101_010() {
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		expected.add(Arrays.asList(new Integer[] { 0, 0 }));
		expected.add(Arrays.asList(new Integer[] { 0, 1 }));
		expected.add(Arrays.asList(new Integer[] { 1, 0 }));
		expected.add(Arrays.asList(new Integer[] { 1, 2 }));
		expected.add(Arrays.asList(new Integer[] { 2, 1 }));
		CellulatAutomation gol = new GameOfLife(3, 3);
		for (List<Integer> cell : expected)
			gol.setCellState(cell, State.ALIVE);
		gol.next();
		List<List<Integer>> result = gol.toArrayOfState(State.ALIVE);
		assertTrue(expected.containsAll(result) && result.containsAll(expected));
	}

	@Test
	public void shouldReturn1100_1000_0001_0011When1100_1100_0011_0011() {
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		expected.add(Arrays.asList(new Integer[] { 0, 0 }));
		expected.add(Arrays.asList(new Integer[] { 0, 1 }));
		expected.add(Arrays.asList(new Integer[] { 1, 0 }));
		expected.add(Arrays.asList(new Integer[] { 2, 3 }));
		expected.add(Arrays.asList(new Integer[] { 3, 2 }));
		expected.add(Arrays.asList(new Integer[] { 3, 3 }));
		CellulatAutomation gol = new GameOfLife(4, 4);
		gol.setCellState(Arrays.asList(new Integer[] { 0, 0 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 0, 1 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 1, 0 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 1, 1 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 2, 2 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 2, 3 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 3, 2 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 3, 3 }), State.ALIVE);
		gol.next();
		List<List<Integer>> result = gol.toArrayOfState(State.ALIVE);
		assertTrue(expected.containsAll(result) && result.containsAll(expected));
	}

	@Test
	public void shouldReturn1100_1100_0011_0011When1100_1000_0001_0011() {
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		expected.add(Arrays.asList(new Integer[] { 0, 0 }));
		expected.add(Arrays.asList(new Integer[] { 0, 1 }));
		expected.add(Arrays.asList(new Integer[] { 1, 0 }));
		expected.add(Arrays.asList(new Integer[] { 1, 1 }));
		expected.add(Arrays.asList(new Integer[] { 2, 2 }));
		expected.add(Arrays.asList(new Integer[] { 2, 3 }));
		expected.add(Arrays.asList(new Integer[] { 3, 2 }));
		expected.add(Arrays.asList(new Integer[] { 3, 3 }));
		CellulatAutomation gol = new GameOfLife(4, 4);
		gol.setCellState(Arrays.asList(new Integer[] { 0, 0 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 0, 1 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 1, 0 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 2, 3 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 3, 2 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 3, 3 }), State.ALIVE);
		gol.next();
		List<List<Integer>> result = gol.toArrayOfState(State.ALIVE);
		assertTrue(expected.containsAll(result) && result.containsAll(expected));
	}

	@Test
	public void shouldReturn000_111_000When010_010_010() {
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		expected.add(Arrays.asList(new Integer[] { 1, 0 }));
		expected.add(Arrays.asList(new Integer[] { 1, 1 }));
		expected.add(Arrays.asList(new Integer[] { 1, 2 }));
		CellulatAutomation gol = new GameOfLife(3, 3);
		gol.setCellState(Arrays.asList(new Integer[] { 0, 1 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 1, 1 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 2, 1 }), State.ALIVE);
		gol.next();
		List<List<Integer>> result = gol.toArrayOfState(State.ALIVE);
		assertTrue(expected.containsAll(result) && result.containsAll(expected));
	}

}
