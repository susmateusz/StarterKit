package singletonEnum;

public enum SingletonEnum {
	INSTANCE;
	private SingletonEnum() {
		System.out.println("Creating SingletonEnum.");
	}
}
