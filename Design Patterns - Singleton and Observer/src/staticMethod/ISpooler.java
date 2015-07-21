package staticMethod;

public class ISpooler {

	private static ISpooler instance = null;
	
	private ISpooler(){
		System.out.println("Creating instance of iSpooler.");
	}
	
	public static ISpooler getInstance(){
		if(instance == null){
			synchronized (ISpooler.class) {
				if(instance == null)
					instance = new ISpooler();
			}
		}
		return instance;
	}
}
