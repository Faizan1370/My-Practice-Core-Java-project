package com.faizan.design.pattrens.singleton.prac1;


public class Singleton {
	private Singleton singleton;
	
	private volatile Singleton singleton2;
	
	private static Singleton singleton3 = new Singleton();
	
	
	public Singleton getInstance() {
		if(singleton ==null) {
			return new Singleton();
		}
		return singleton;
	}
	
	public Singleton getInstance1() {
		if(singleton2==null) {
			synchronized (Singleton.class) {
				if(singleton2==null) {
					singleton2 = new Singleton();
				}
				
			}
		}
		return singleton2;
	}
	
	public Singleton getInstace3() {
		return singleton3;
	}

}
