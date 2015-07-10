package com.capgemini.taxi;

import java.util.concurrent.ThreadFactory;

public class TaxiFactory implements ThreadFactory {
	private int counter = 0;
	private String prefix = "";
	
	public TaxiFactory(String prefix) {
		this.prefix = prefix;
	}
	public Thread newThread(Runnable r) {
		return new Thread(r,prefix+"-"+counter++);
	}

}
