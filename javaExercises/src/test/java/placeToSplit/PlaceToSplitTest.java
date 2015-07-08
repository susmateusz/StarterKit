package placeToSplit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.placeToSplit.PlaceToSplit;

/**
 * Tests class PlaceToSplit
 * 
 * @author MATSUS
 *
 */
public class PlaceToSplitTest {

	/**
	 * Tests if function canBalance(...) returns correct value for most common
	 * examples: when array is empty, has one or two elements, all elements are
	 * equal 0, some elements are nagative etc
	 */
	@Test
	public void testCanBalance() {
		// Tests where array can be split correctly
		assertTrue(PlaceToSplit.canBalance(new int[] { 1, 1, 1, 2, 1 }));
		assertTrue(PlaceToSplit.canBalance(new int[] { 10, 10 }));
		assertTrue(PlaceToSplit.canBalance(new int[] { 1, 3, -2 }));
		assertTrue(PlaceToSplit.canBalance(new int[] { 0, 0, 0 }));
		// Tests where array cannot be split correctly
		assertFalse(PlaceToSplit.canBalance(new int[] {}));
		assertFalse(PlaceToSplit.canBalance(new int[] { 0 }));
		assertFalse(PlaceToSplit.canBalance(new int[] { 10 }));
		assertFalse(PlaceToSplit.canBalance(new int[] { 2, 1, 1, 2, 1 }));

	}

}
