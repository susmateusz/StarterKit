package com.msus.gameOfLife;

import static org.junit.Assert.assertEquals;

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
		gol.setAliveCell(1, 1);
		gol.next();
		String expected = new String("");
		assertEquals(expected, gol.toString());
	}

	@Test
	public void shouldReturnEmptyWhen000_011_000() {
		CellulatAutomation gol = new GameOfLife(3, 3);
		gol.setAliveCell(1, 1);
		gol.setAliveCell(1, 2);
		gol.next();
		String expected = new String("");
		assertEquals(expected, gol.toString());
	}

	@Test
	public void shouldReturn000_011_011EmptyWhen000_011_011() {
		CellulatAutomation gol = new GameOfLife(3, 3);
		gol.setAliveCell(1, 1);
		gol.setAliveCell(1, 2);
		gol.setAliveCell(2, 1);
		gol.setAliveCell(2, 2);
		gol.next();
		String expected = new String("C(1, 1)\nC(1, 2)\nC(2, 1)\nC(2, 2)\n");
		assertEquals(expected, gol.toString());
	}

	@Test
	public void shouldReturn110_101_010EmptyWhen110_101_010() {
//		System.out.println("Start");
		CellulatAutomation gol = new GameOfLife(3, 3);
		gol.setAliveCell(0, 0);
		gol.setAliveCell(0, 1);
		gol.setAliveCell(1, 0);
		gol.setAliveCell(1, 2);
		gol.setAliveCell(2, 1);
		gol.next();
		String expected = new String("C(0, 0)\nC(0, 1)\nC(1, 0)\nC(1, 2)\nC(2, 1)\n");
//		System.out.println("End");
		assertEquals(expected, gol.toString());
	}

//	@Test
//	public void shouldReturn1100_1000_0001_0011EmptyWhen1100_1100_0011_0011() {
////		System.out.println("Start");
//		CellulatAutomation gol = new GameOfLife(3, 3);
//		gol.setAliveCell(0, 0);
//		gol.setAliveCell(0, 1);
//		gol.setAliveCell(1, 0);
//		gol.setAliveCell(1, 2);
//		gol.setAliveCell(2, 1);
//		gol.next();
//		String expected = new String("C(0, 0)\nC(0, 1)\nC(1, 0)\nC(1, 2)\nC(2, 1)\n");
////		System.out.println("End");
//		assertEquals(expected, gol.toString());
//	}

}
