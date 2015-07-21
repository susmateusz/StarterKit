package observerPattern;

import java.util.Observable;

public class Obserwowany extends Observable {

	private String message = new String("First message.");
	
	public void changeState(String msg){
		message = msg;
		setChanged();
		notifyObservers(message);
	}
}
