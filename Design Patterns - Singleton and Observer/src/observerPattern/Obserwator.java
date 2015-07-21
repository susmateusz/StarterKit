package observerPattern;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.SynchronousQueue;

public class Obserwator implements Observer {

	private int id;
	
	public Obserwator(int id) {
		this.id = id;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		String msg = (String) arg1;
		System.out.println(id+" was notified with message: "+msg);
	}

}
