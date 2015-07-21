import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
/**
 * http://winterbe.com/posts/2014/03/16/java-8-tutorial/
 * http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 * @author MATSUS
 *
 */
public class Streams {

	List<String> stringCollection;

	@Before
	public void initCollection() {
		stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		System.out.println("Collection: " + stringCollection);
	}

	/**
	 * Filter accepts a predicate
	 */
	@Test
	public void testFilter() {
		System.out.println("Filter");
		stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
	}

	/**
	 * Sorted - natural order or custom comparator
	 */
	@Test
	public void testSorted() {
		System.out.println("Sorted and Filter");
		stringCollection.stream().filter((s) -> s.startsWith("a")).sorted().forEach(System.out::println);
	}

	/**
	 * Map - natural order or custom comparator
	 */
	@Test
	public void testMap() {
		System.out.println("Map");
		stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
				.forEach(System.out::println);
	}

	/**
	 * Match - check if all, any or none element match to the predicate
	 */
	@Test
	public void testMatch() {
		boolean anyStartsWithA = stringCollection.stream().anyMatch((s)->s.startsWith("a"));
		assertTrue(anyStartsWithA);
		
		boolean allStartsWithA = stringCollection.stream().allMatch((s)->s.startsWith("a"));
		assertFalse(allStartsWithA);
		
		boolean noneStartsWithY = stringCollection.stream().noneMatch((s)->s.startsWith("z"));
		assertTrue(noneStartsWithY);
	}

}
