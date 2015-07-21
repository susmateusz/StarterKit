package singletonFinalClass;

final public class PrintSpooler {

	static public void print(String s){
		System.out.println("static "+s);
	}

	public PrintSpooler() {
		super();
		System.out.println("Creating PrintSpooler with final.");
	}
	
	
	public void printInstance(String s){
		System.out.println("instance "+s);
	}
	
}
