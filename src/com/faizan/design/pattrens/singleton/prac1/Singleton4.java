package com.faizan.design.pattrens.singleton.prac1;

public class Singleton4 {
	// Volatile is necessary for safe publishing of the instance
    private static volatile Singleton4 instance;

    // Private constructor to prevent instantiation
    private Singleton4() {}
    
    public static Singleton4 getInstance() {
    	if(instance== null) {
    		synchronized (Singleton4.class) {
				if(instance==null) {
					instance= new Singleton4();
				}
			}
    	}
		return instance;
    }


}
