package com.faizan.design.pattrens.singleton.prac1;

public class Singleton5 {
	
	private static volatile Singleton5 singleton5;
	
	private static volatile Singleton5 singleton52= new Singleton5();
	 // Private constructor to prevent instantiation
    private Singleton5() {}
	
	public static Singleton5 getInstance() {
		if(singleton5==null) {
			singleton5= new Singleton5();
		}
		return singleton5;
	}
	
	public static Singleton5 getInstance1() {
		if(singleton5==null) {
			synchronized (Singleton5.class) {
				if(singleton5==null) {
					singleton5= new Singleton5();
				}
			}
			
		}
		return singleton5;
	}
	
	public Singleton5 getInstacne3() {
		return singleton52;
	}

}
