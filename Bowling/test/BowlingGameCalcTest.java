import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BowlingGameCalcTest {

	@Test
	public void shouldReturn1For1() {

		BowlingGameCalc bc = new BowlingCalculator();
		bc.roll(1);
		assertEquals(1, bc.score());

	}

	@Test
	public void shouldReturn4For1And3() {

		BowlingGameCalc bc = new BowlingCalculator();
		bc.roll(1);
		bc.roll(3);
		assertEquals(4, bc.score());

	}

	@Test
	public void shouldReturn12For10And1() {
		BowlingGameCalc bc = new BowlingCalculator();
		bc.roll(10);
		bc.roll(1);
		assertEquals(12, bc.score());

	}

	@Test
	public void shouldReturn16For10_1_2() {
		BowlingGameCalc bc = new BowlingCalculator();
		bc.roll(10);
		bc.roll(1);
		bc.roll(2);
		assertEquals(16, bc.score());

	}

	@Test
	public void shouldReturn16For2_8_3() {
		BowlingGameCalc bc = new BowlingCalculator();
		bc.roll(2);
		bc.roll(8);
		bc.roll(3);
		assertEquals(16, bc.score());

	}

	/**
	 * I : bonus 2, score 10 II: bonus 3, score 30 III: bonus 1, score 45
	 */
	@Test
	public void shouldReturn45For10_10_5() {
		BowlingGameCalc bc = new BowlingCalculator();
		bc.roll(10);
		bc.roll(10);
		bc.roll(5);
		assertEquals(45, bc.score());
	}

	@Test
	public void shouldReturn60For10_10_10() {
		BowlingGameCalc bc = new BowlingCalculator();
		bc.roll(10);
		bc.roll(10);
		bc.roll(10);
		assertEquals(60, bc.score());
	}

	@Test
	public void shouldReturn53For10_10_5_4() {
		BowlingGameCalc bc = new BowlingCalculator();
		bc.roll(10);
		bc.roll(10);
		bc.roll(5);
		bc.roll(4);
		assertEquals(53, bc.score());
	}

	@Test
	public void shouldReturn19For0_10_2_5() {
		BowlingGameCalc bc = new BowlingCalculator();
		bc.roll(0);
		bc.roll(10);
		bc.roll(2);
		bc.roll(5);
		assertEquals(19, bc.score());
	}

	@Test
	public void shouldReturn16For0_10_3() {
		BowlingGameCalc bc = new BowlingCalculator();
		bc.roll(0);
		bc.roll(10);
		bc.roll(3);
		assertEquals(16, bc.score());

	}

	@Test
	public void shouldReturn36For0_10_10_3() {
		BowlingGameCalc bc = new BowlingCalculator();
		bc.roll(0);
		bc.roll(10);
		bc.roll(10);
		bc.roll(3);
		assertEquals(36, bc.score());

	}

	@Test
	public void shouldReturn46For0_10_10_3_5() {
		BowlingGameCalc bc = new BowlingCalculator();
		bc.roll(0);
		bc.roll(10);
		bc.roll(10);
		bc.roll(3);
		bc.roll(5);
		assertEquals(46, bc.score());

	}

	@Test
	public void shouldReturn32For10_5_5_1() {
		BowlingGameCalc bc = new BowlingCalculator();
		bc.roll(10);
		bc.roll(5);
		bc.roll(5);
		bc.roll(1);
		assertEquals(32, bc.score());

	}

	@Test
	public void shouldBeFinishedNoStrikeNoSpare() {
		BowlingGameCalc bc = new BowlingCalculator();
		for (int i = 0; i < 20; i++) {
			bc.roll(1);
		}
		assertTrue(bc.isFinished());

	}

	@Test
	public void shouldNotBeFinished() {
		BowlingGameCalc bc = new BowlingCalculator();
		bc.roll(1);
		assertFalse(bc.isFinished());

	}
	//TODO
/*	ostatni ruch 
 *  ostatnia tura 3 rzuty
 *  kombinacje strike-spare
 * 
 */
}
