package singletonPrivateClass;

public class Singleton {

	private Singleton(){
		System.out.println("Creating Singleton.");
	}
	
	private static class SingletonHolder {
		private final static Singleton instance = new Singleton();
	}
	
	public static Singleton getInstance(){
		return SingletonHolder.instance;
	}
}
