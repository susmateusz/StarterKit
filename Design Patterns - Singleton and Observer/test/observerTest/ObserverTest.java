package observerTest;

import org.junit.Test;

import observerPattern.Obserwator;
import observerPattern.Obserwowany;

public class ObserverTest {

	@Test
	public void shouldBeNotified() {
		Obserwowany subject = new Obserwowany();
		Obserwator ob1 = new Obserwator(1);
		Obserwator ob2 = new Obserwator(2);
		Obserwator ob3 = new Obserwator(3);
		subject.addObserver(ob1);
		subject.addObserver(ob2);
		subject.addObserver(ob3);
		subject.changeState("new state");
	}

}
