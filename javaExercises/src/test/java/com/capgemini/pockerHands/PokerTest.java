package com.capgemini.pockerHands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.pokerHands.Card;
import com.capgemini.pokerHands.Game;
import com.capgemini.pokerHands.Hand;
import com.capgemini.pokerHands.Rank;

public class PokerTest {

	private Hand h;

	@Before
	public void initializeHand() {
		h = new Hand();
		System.out.println("Nowy test");
	}

	@After
	public void endTest(){
		System.out.println("Koniec testu");
	}
	@Test
	public void shouldReturnStraightWhen6D7S8C9HTD() {
		h.addCard(new Card("7S"));
		h.addCard(new Card("6D"));
		h.addCard(new Card("TD"));
		h.addCard(new Card("9H"));
		h.addCard(new Card("8C"));
		h.findRank();
		assertEquals(Rank.STRAIGHT, h.getRank());
	}

	@Test
	public void shouldReturnFlushWhen4D6D8DTDQD() {
		h.addCard(new Card("4D"));
		h.addCard(new Card("6D"));
		h.addCard(new Card("8D"));
		h.addCard(new Card("TD"));
		h.addCard(new Card("QD"));
		h.findRank();
		assertEquals(Rank.FLUSH, h.getRank());
	}

	@Test
	public void shouldReturnStraightFlushWhen7H8H9HTHJH() {
		h.addCard(new Card("JH"));
		h.addCard(new Card("TH"));
		h.addCard(new Card("9H"));
		h.addCard(new Card("8H"));
		h.addCard(new Card("7H"));
		h.findRank();
		assertEquals(Rank.STRAIGHT_FLUSH, h.getRank());
	}

	@Test
	public void shouldReturnRoyalFlushWhenASKSQSJSTS() {
		System.out.println("dudud");
		h.addCard(new Card("AS"));
		h.addCard(new Card("QS"));
		h.addCard(new Card("JS"));
		h.addCard(new Card("KS"));
		h.addCard(new Card("TS"));
		h.findRank();
		assertEquals(Rank.ROYAL_FLUSH, h.getRank());
	}

	@Test
	public void shouldReturnOnePairWhen549Q9() {
		Hand h = new Hand();
		h.addCard(new Card("5S"));
		h.addCard(new Card("4D"));
		h.addCard(new Card("9H"));
		h.addCard(new Card("QH"));
		h.addCard(new Card("9D"));
		h.findRank();
		assertEquals(Rank.ONE_PAIR, h.getRank());
	}

	@Test
	public void shouldReturnTwoPairsWhen89J9J1() {
		h.addCard(new Card("8H"));
		h.addCard(new Card("9C"));
		h.addCard(new Card("JS"));
		h.addCard(new Card("9S"));
		h.addCard(new Card("JD"));
		h.findRank();
		assertEquals(Rank.TWO_PAIRS, h.getRank());
	}

	@Test
	public void shouldReturnThreeOfKindWhenTAJJJ() {
		h.addCard(new Card("TC"));
		h.addCard(new Card("JS"));
		h.addCard(new Card("AS"));
		h.addCard(new Card("JD"));
		h.addCard(new Card("JH"));
		h.findRank();
		assertEquals(Rank.THREE_OF_KIND, h.getRank());
	}

	@Test
	public void shouldReturnFullHouseWhenKAKAK() {
		h.addCard(new Card("KD"));
		h.addCard(new Card("AC"));
		h.addCard(new Card("KS"));
		h.addCard(new Card("AS"));
		h.addCard(new Card("KC"));
		h.findRank();
		assertEquals(Rank.FULL, h.getRank());
	}

	@Test
	public void shouldReturnFourOfAKindWhenAAAAK() {
		h.addCard(new Card("AC"));
		h.addCard(new Card("AD"));
		h.addCard(new Card("AS"));
		h.addCard(new Card("AH"));
		h.addCard(new Card("KH"));
		h.findRank();
		assertEquals(Rank.FOUR_OF_KIND, h.getRank());
	}

	@Test
	public void shouldReturnWinWhen469QQvs367QQ() {
		Hand h1, h2;
		h1 = new Hand("4D", "6S", "9H", "QH", "QC");
		h2 = new Hand("3D", "6D", "7H", "QD", "QS");
		assertTrue(h1.compareTo(h2) >= 1);
	}
	
	@Test
	public void shouldReturnFailureWhen29AAAvs3367TD() {
		Hand h1, h2;
		h1 = new Hand("2D", "9C", "AS", "AH", "AC");
		h2 = new Hand("3D", "6D", "7D", "TD", "QD");
		assertTrue(h1.compareTo(h2) <= -1);
	}
	
	@Test
	public void shouldReturn376WinsForPlayer1(){
		Game g = new Game("src\\poker.txt");
		g.playAllGames();
		assertEquals(376, g.getWins1());
		//System.out.println("Scores\nPlayer 1: "+g.getWins1()+"\nPlayer 2: "+g.getWins2());
	}
	

}
