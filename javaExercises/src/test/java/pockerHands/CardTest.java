package pockerHands;
import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.pokerHands.Card;

public class CardTest {
	// TODO Comments
	private String[] values = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A" };
	private String[] kinds = { "H", "C", "S", "D" };

	@Test
	public void testCreateCard() {
		for (String i : values)
			for (String j : kinds) {
				Card card = new Card(i + j);
				assertEquals(String.format("%s%s", i, j), card.toString());
			}
	}

	@Test
	public void testFirstLessThanSecond() {
		for (int i = 0; i < values.length; i++)
			for (String kind : kinds) {
				Card card1 = new Card(values[i] + kind);
				for (int j = i + 1; j < values.length; j++)
					for (String kind2 : kinds) {
						Card card2 = new Card(values[j] + kind2);
						assertTrue("Expected to be less than",card1.compareTo(card2) <= -1);
					}
			}
	}

	@Test
	public void testFirstGreaterThanSecond() {
		for (int i = 0; i < values.length; i++)
			for (String kind : kinds) {
				Card card1 = new Card(values[i] + kind);
				for (int j = 0; j < i; j++)
					for (String kind2 : kinds) {
						Card card2 = new Card(values[j] + kind2);
						assertTrue("Expected to be greater than",card1.compareTo(card2) >= 1);
					}
			}
	}

	@Test
	public void testFirstEqualsToSecond() {
		for (int i = 0; i < values.length; i++)
			for (String kind : kinds) {
				Card card1 = new Card(values[i] + kind);
				for (String kind2 : kinds) {
					Card card2 = new Card(values[i] + kind2);
					assertTrue("Expected to be equal", card1.compareTo(card2) == 0);
				}
			}
	}
}
