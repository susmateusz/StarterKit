package lambda;

public interface PersonFactory<P extends Person> {
	P create(String firstName, String secondName);
}
