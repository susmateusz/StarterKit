package com.msus.GameOfLifeTests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.msus.GameOfLifeController.GameOfLifeController;
import com.msus.GameOfLifeMVCInterfaces.Controller;
import com.msus.GameOfLifeMVCInterfaces.View;
import com.msus.GameOfLifeModel.CellulatAutomation;
import com.msus.GameOfLifeModel.GameOfLife;
import com.msus.GameOfLifeModel.State;
import com.msus.GameOfLifeView.GOLSwingView;

public class GameOfLifeTest {

	@Test
	public void shouldReturnEmptyBoardWhenEmptyInput() {
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		CellulatAutomation gol = new GameOfLife(3, 3);
		gol.next();
		List<List<Integer>> result = gol.toArrayOfState(State.ALIVE);
		assertTrue(expected.containsAll(result) && result.containsAll(expected));
	}

	@Test
	public void shouldReturnEmptyWhen000_010_000() {
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		CellulatAutomation gol = new GameOfLife(3, 3);
		gol.setCellState(Arrays.asList(new Integer[] { 1, 1 }), State.ALIVE);
		gol.next();
		List<List<Integer>> result = gol.toArrayOfState(State.ALIVE);
		assertTrue(expected.containsAll(result) && result.containsAll(expected));
	}

	@Test
	public void shouldReturnEmptyWhen000_011_000() {
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		CellulatAutomation gol = new GameOfLife(3, 3);
		gol.setCellState(Arrays.asList(new Integer[] { 1, 1 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 1, 2 }), State.ALIVE);
		gol.next();
		List<List<Integer>> result = gol.toArrayOfState(State.ALIVE);
		assertTrue(expected.containsAll(result) && result.containsAll(expected));
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

	@Test
	public void shouldMoveGlider2MovesForward() {
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		expected.add(Arrays.asList(new Integer[] { 2, 0 }));
		expected.add(Arrays.asList(new Integer[] { 3, 1 }));
		expected.add(Arrays.asList(new Integer[] { 3, 2 }));
		expected.add(Arrays.asList(new Integer[] { 2, 2 }));
		expected.add(Arrays.asList(new Integer[] { 1, 2 }));
		CellulatAutomation gol = new GameOfLife(10, 10);
		gol.setCellState(Arrays.asList(new Integer[] { 0, 1 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 1, 2 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 2, 0 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 2, 1 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 2, 2 }), State.ALIVE);
		gol.next();
		gol.next();
		List<List<Integer>> result = gol.toArrayOfState(State.ALIVE);
		assertTrue(expected.containsAll(result) && result.containsAll(expected));
	}

//	@Test
//	public void shouldFinishAfter32Moves() {
//		GameOfLife gol = new GameOfLife(10, 10);
//		gol.setCellState(Arrays.asList(new Integer[] { 0, 1 }), State.ALIVE);
//		gol.setCellState(Arrays.asList(new Integer[] { 1, 2 }), State.ALIVE);
//		gol.setCellState(Arrays.asList(new Integer[] { 2, 0 }), State.ALIVE);
//		gol.setCellState(Arrays.asList(new Integer[] { 2, 1 }), State.ALIVE);
//		gol.setCellState(Arrays.asList(new Integer[] { 2, 2 }), State.ALIVE);
//		GOLConsoleView console = new GOLConsoleView(10, 10);
//		Controller control = new GameOfLifeController(gol,console);
//		int i = 0;
//		while (!control.isGameFinished()){
//			control.nextMoveEvent();
//			i++;
//		}
//		assertEquals(32,i);
//	}

	@Test
	public void shouldRunSwingView() {
		GameOfLife gol = new GameOfLife(10, 10);
		gol.setCellState(Arrays.asList(new Integer[] { 0, 1 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 1, 2 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 2, 0 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 2, 1 }), State.ALIVE);
		gol.setCellState(Arrays.asList(new Integer[] { 2, 2 }), State.ALIVE);
		View swingView = new GOLSwingView(30,30);
		GameOfLifeController control = new GameOfLifeController(gol,swingView);
		control.setSpeed(30);
		control.start();
	}

}
