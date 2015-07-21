import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

import lambda.Converter;
import lambda.LambdaAccesToFieldsAndStatic;
import lambda.Person;
import lambda.PersonFactory;
import lambda.Something;

public class LambdaTests {

	@Test
	public void shouldSortNames() {
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(names, (a, b) -> b.compareTo(a));
		assertEquals("[xenia, peter, mike, anna]", names.toString());
	}

	@Test
	public void shouldConvertTo123() {
		Converter<String, Integer> converter = Integer::valueOf;
		Integer converted = converter.convert("123");
		assertEquals(new Integer(123), converted);
	}

	@Test
	public void shouldReturnJ() {
		Something something = new Something();
		Converter<String, String> converter = something::startsWith;
		String converted = converter.convert("Java");
		assertEquals("J", converted);
	}

	@Test
	public void shouldReturnEqualPerson() {
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Mateusz", "Sus");
		Person person2 = new Person("Mateusz", "Sus");
		assertTrue(person.equals(person2));
	}

	@Test
	public void shouldReturn3WhenScopeFinal() {
		final int num = 1;
		Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
		assertEquals("3", stringConverter.convert(2));
	}

	@Test
	public void shouldReturn3WhenScopeFinalImplicity() {
		int num = 1;
		Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
		assertEquals("3", stringConverter.convert(2));
	}

	@Test
	public void shouldReturn320FromLambdaAccessClass() {
		LambdaAccesToFieldsAndStatic lam = new LambdaAccesToFieldsAndStatic();
		assertEquals("320", lam.testScopes());
	}

	/**
	 * Predicate - orzeczenia
	 */
	@Test
	public void shouldReturnTrueForPredicate() {
		Predicate<String> predicate1 = (s) -> s.length() > 0;
		Predicate<String> predicate2 = (s) -> s.length() < 10;
		Predicate<String> predicate3 = predicate1.and(predicate2);
		assertTrue(predicate3.test("foo"));
	}

	@Test
	public void shouldReturnFalseForPredicate() {
		Predicate<String> predicate1 = (s) -> s.length() > 0;
		Predicate<String> predicate2 = (s) -> s.length() < 10;
		Predicate<String> predicate3 = predicate1.and(predicate2);
		assertFalse(predicate3.negate().test("foo"));
	}

	/**
	 * Functions - bierze jeden argument i produkuje wynik
	 */
	@Test
	public void shouldReturn123ForFunctions() {
		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);
		assertEquals("123", backToString.apply("123"));
	}

	/**
	 * Suppliers - produkuje obiekt danego typu
	 */
	@Test
	public void shouldCreatePersonForSuppliers() {
		Supplier<Person> personSupplier = Person::new;
		Person expected = personSupplier.get();
		assertEquals(Person.class, expected.getClass());
	}

	/**
	 * Consumer - make operations on one argument
	 */
	@Test
	public void shouldPerformOperationsForConsumers() {
		Consumer<Person> addDotToLastName = (p) -> p.setLastName(p.getLastName() + ".");
		Consumer<Person> lastNameToInitial = (p) -> p.setLastName(p.getLastName().substring(0, 1));
		Consumer<Person> censorLastName = lastNameToInitial.andThen(addDotToLastName);
		Person p = new Person("Mateusz", "Sus");
		censorLastName.accept(p);
		assertEquals("S.", p.getLastName());

	}

	/**
	 * Comparator - compare
	 */
	@Test
	public void shouldJohnHaveBiggerNameThanAliceForComparator() {
		Comparator<Person> comparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());
		Person p1 = new Person("John", "Doe");
		Person p2 = new Person("Alice", "Wonderland");
		assertTrue(comparator.compare(p1, p2) > 0);
		assertTrue(comparator.reversed().compare(p1, p2) < 0);
	}

	/**
	 * Optional - prevert NullPointerException instead of return nothing return
	 * optional
	 */
	@Test
	public void testOptional() {
		Optional<String> optional = Optional.of("pusto");
		// check if optional value is set
		assertTrue(optional.isPresent());
		// return optional value
		assertEquals("pusto", optional.get());
		// perform a consumer if optional value is present
		Consumer<Person> addDotToLastName = (p) -> p.setLastName(p.getLastName() + ".");
		Consumer<Person> lastNameToInitial = (p) -> p.setLastName(p.getLastName().substring(0, 1));
		Consumer<Person> censorLastName = lastNameToInitial.andThen(addDotToLastName);
		Person p1 = new Person("Joe","Doe");
		Optional<Person> optionalPerson = Optional.of(p1);
		optionalPerson.ifPresent(censorLastName);
		Person p2 = new Person("Mateusz","Sus");
		Person p3 = optionalPerson.orElse(p2);
		assertEquals("Person [firstName=Joe, lastName=D.]", p3.toString());
	}
}
