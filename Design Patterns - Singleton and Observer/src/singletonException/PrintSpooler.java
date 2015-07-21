package singletonException;

public class PrintSpooler {
	static boolean instance_flag = false;

	public PrintSpooler() throws SingletonException {
		if (instance_flag)
			throw new SingletonException("Only one spooler allowed.");
		instance_flag = true;
		System.out.println("Spooler opened.");
	}

	public void finalize() {
		instance_flag = false;
	}
}
