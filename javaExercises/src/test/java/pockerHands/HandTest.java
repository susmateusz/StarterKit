package pockerHands;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.pokerHands.Card;
import com.capgemini.pokerHands.Hand;
import com.capgemini.pokerHands.Rank;

public class HandTest {
	// TODO Comments
	@Test
	public void testFilling() {
		Hand h = new Hand();
		h.addCard(new Card("5H"));
		h.addCard(new Card("6S"));
		h.addCard(new Card("KD"));
		h.addCard(new Card("7S"));
		h.addCard(new Card("5C"));
		assertEquals("[5C, 5H, 6S, 7S, KD]", h.toString());
	}

	@Test
	public void testFlushes() {
		Hand h = new Hand();
		h.addCard(new Card("9D"));
		h.addCard(new Card("QD"));
		h.addCard(new Card("JH"));
		h.addCard(new Card("KD"));
		h.addCard(new Card("10D"));
		h.findFlushes();
		assertEquals(Rank.STRAIGHT, h.getRank());
		h.clear();

		h.addCard(new Card("9D"));
		h.addCard(new Card("QD"));
		h.addCard(new Card("JD"));
		h.addCard(new Card("KD"));
		h.addCard(new Card("10D"));
		h.findFlushes();
		assertEquals(Rank.STRAIGHT_FLUSH, h.getRank());
		h.clear();

		h.addCard(new Card("4D"));
		h.addCard(new Card("QD"));
		h.addCard(new Card("JD"));
		h.addCard(new Card("KD"));
		h.addCard(new Card("10D"));
		h.findFlushes();
		assertEquals(Rank.FLUSH, h.getRank());
		h.clear();

		h.addCard(new Card("AD"));
		h.addCard(new Card("QD"));
		h.addCard(new Card("JD"));
		h.addCard(new Card("KD"));
		h.addCard(new Card("10D"));
		h.findFlushes();
		assertEquals(Rank.ROYAL_FLUSH, h.getRank());
	}

	@Test
	public void testFindPairs() {
		Hand h = new Hand();
		h.addCard(new Card("JS"));
		h.addCard(new Card("9D"));
		h.addCard(new Card("QH"));
		h.addCard(new Card("9H"));
		h.addCard(new Card("JD"));
		h.findPairs();
		assertEquals(Rank.TWO_PAIRS, h.getRank());
		h.clear();
	}

	@Test
	public void testFindRank() {
		Hand h = new Hand();
		h.addCard(new Card("5H"));
		h.addCard(new Card("5C"));
		h.addCard(new Card("6S"));
		h.addCard(new Card("7S"));
		h.addCard(new Card("KD"));
		h.findRank();
		assertEquals(Rank.ONE_PAIR, h.getRank());
		assertEquals(new Integer(5), h.getRankCard());
		h.clear();

		h.addCard(new Card("2C"));
		h.addCard(new Card("3S"));
		h.addCard(new Card("8S"));
		h.addCard(new Card("8D"));
		h.addCard(new Card("TD"));
		h.findRank();
		assertEquals(Rank.ONE_PAIR, h.getRank());
		assertEquals(new Integer(8), h.getRankCard());
		h.clear();

		h.addCard(new Card("5D"));
		h.addCard(new Card("8C"));
		h.addCard(new Card("9S"));
		h.addCard(new Card("JS"));
		h.addCard(new Card("AC"));
		h.findRank();
		assertEquals(Rank.HIGH_CARD, h.getRank());
		h.clear();

		h.addCard(new Card("2C"));
		h.addCard(new Card("5C"));
		h.addCard(new Card("7D"));
		h.addCard(new Card("8S"));
		h.addCard(new Card("QH"));
		h.findRank();
		assertEquals(Rank.HIGH_CARD, h.getRank());
		h.clear();

		h.addCard(new Card("2D"));
		h.addCard(new Card("9C"));
		h.addCard(new Card("AS"));
		h.addCard(new Card("AH"));
		h.addCard(new Card("AC"));
		h.findRank();
		assertEquals(Rank.THREE_OF_KIND, h.getRank());
		assertEquals(new Integer(14), h.getRankCard());
		h.clear();

		h.addCard(new Card("3D"));
		h.addCard(new Card("6D"));
		h.addCard(new Card("7D"));
		h.addCard(new Card("TD"));
		h.addCard(new Card("QD"));
		h.findRank();
		assertEquals(Rank.FLUSH, h.getRank());
		h.clear();

		h.addCard(new Card("4D"));
		h.addCard(new Card("6S"));
		h.addCard(new Card("9H"));
		h.addCard(new Card("QH"));
		h.addCard(new Card("QC"));
		h.findRank();
		assertEquals(Rank.ONE_PAIR, h.getRank());
		assertEquals(new Integer(12), h.getRankCard());
		h.clear();

		h.addCard(new Card("3D"));
		h.addCard(new Card("6D"));
		h.addCard(new Card("7H"));
		h.addCard(new Card("QD"));
		h.addCard(new Card("QS"));
		h.findRank();
		assertEquals(Rank.ONE_PAIR, h.getRank());
		assertEquals(new Integer(12), h.getRankCard());
		h.clear();

		h.addCard(new Card("2H"));
		h.addCard(new Card("2D"));
		h.addCard(new Card("4C"));
		h.addCard(new Card("4D"));
		h.addCard(new Card("4S"));
		h.findRank();
		assertEquals(Rank.FULL, h.getRank());
		assertEquals(new Integer(4), h.getRankCard());
		assertEquals(new Integer(2), h.getRankSecondCard());
		h.clear();

		h.addCard(new Card("3C"));
		h.addCard(new Card("3D"));
		h.addCard(new Card("3S"));
		h.addCard(new Card("9S"));
		h.addCard(new Card("9D"));
		h.findRank();
		assertEquals(Rank.FULL, h.getRank());
		assertEquals(new Integer(3), h.getRankCard());
		assertEquals(new Integer(9), h.getRankSecondCard());
		h.clear();
	}

	@Test
	public void testCompareHands() {
		Hand h1, h2;
		h1 = new Hand("5H", "5C", "6S", "7S", "KD");
		h2 = new Hand("2C", "3S", "8S", "8D", "TD");
		assertTrue(h1.compareTo(h2) <= -1);
		h1.clear();
		h2.clear();

		h1 = new Hand("5D", "8C", "9S", "JS", "AC");
		h2 = new Hand("2C", "5C", "7D", "8S", "QH");
		assertTrue(h1.compareTo(h2) >= 1);
		h1.clear();
		h2.clear();

		h1 = new Hand("2D", "9C", "AS", "AH", "AC");
		h2 = new Hand("3D", "6D", "7D", "TD", "QD");
		assertTrue(h1.compareTo(h2) <= -1);
		h1.clear();
		h2.clear();

		h1 = new Hand("4D", "6S", "9H", "QH", "QC");
		h2 = new Hand("3D", "6D", "7H", "QD", "QS");
		assertTrue(h1.compareTo(h2) >= 1);
		h1.clear();
		h2.clear();

		h1 = new Hand("2H", "2D", "4C", "4D", "4S");
		h2 = new Hand("3C", "3D", "3S", "9S", "9D");
		assertTrue(h1.compareTo(h2) >= 1);
		h1.clear();
		h2.clear();
	}

}
