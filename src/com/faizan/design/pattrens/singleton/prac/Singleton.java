package com.faizan.design.pattrens.singleton.prac;

public class Singleton {
	
	private static Singleton singleton;
	
	private static volatile Singleton singleton2;
	
	private static Singleton singleton3 = new Singleton();
	
	
	public static Singleton getInstance() {
		if(singleton ==null) {
			singleton= new Singleton();
		}
		
		return singleton;
	}
	
	public static Singleton getInstance1() {
		if(singleton2==null) {
			synchronized (Singleton.class) {
				if(singleton2==null) {
					singleton2 = new Singleton();
				}
				
			}
		}
		return singleton2;
	}
	
	public static Singleton getInstance2() {
		return singleton3;
	}

}
