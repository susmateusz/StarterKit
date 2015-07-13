package com.capgemini.pokerHands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Game {
	// TODO Comments
	private Hand[][] gameList = new Hand[1000][2];
	private int wins1=0,wins2=0;
	
	public int getWins1() {
		return wins1;
	}

	public void setWins1(int wins1) {
		this.wins1 = wins1;
	}

	public int getWins2() {
		return wins2;
	}

	public void setWins2(int wins2) {
		this.wins2 = wins2;
	}

	public void loadGamesFromFile(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));;
		try{
			String line = br.readLine();
			int row = 0;
			while(line != null){
				String [] cards = line.split(" ");
				gameList[row][0] = new Hand(cards[0],cards[1],cards[2],cards[3],cards[4]);
				gameList[row][1] = new Hand(cards[5],cards[6],cards[7],cards[8],cards[9]);
				line = br.readLine();
				row++;
			}
		} finally {
			br.close();
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(1000*30);
		for(Hand[] hands : gameList){
			for(Hand h : hands)
				result.append(h.toString());
			result.append("\n");
		}
		return result.toString();
	}
	
	public Game(){
		
	}
	
	public Game(String filename){
		try {
			loadGamesFromFile("src\\poker.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int playGame(int i){
		if(i<0 || i > gameList.length)
			return 0;
		return gameList[i][0].compareTo(gameList[i][1]);
	}
	
	public void playAllGames(){
		setWins1(0);
		setWins2(0);
		for(Hand[] hands : gameList)
			if(hands[0].compareTo(hands[1]) <= -1)
				setWins2(getWins2()+1);
			else if (hands[0].compareTo(hands[1]) >= 1)
				setWins1(getWins1()+1);
			else
				System.out.println("Draw between: "+hands[0]+" and "+hands[1]);
	}
	
	
}
