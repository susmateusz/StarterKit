package com.capgemini.pokerHands;

import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;

public class GameTest {
	// TODO Comments
	@Test
	public void testFile() {
		Game g = new Game();
		boolean succeed = true;
		try {
			g.loadGamesFromFile("src\\poker.txt");
		} catch (IOException e) {
			succeed = false;
			e.printStackTrace();
		}
		assertTrue(succeed);
	}
	
	@Test
	public void testAllGames(){
		Game g = new Game("src\\poker.txt");
		g.playAllGames();
		//System.out.println("Scores\nPlayer 1: "+g.getWins1()+"\nPlayer 2: "+g.getWins2());
	}
}
