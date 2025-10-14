package com.faizan.design.pattrens.singleton.prac2;

public class Singleton {
	
	private static volatile Singleton singleton;
	
	private static volatile Singleton singleton1 = new Singleton();
	
	public static Singleton getInsatance1() {
		if(singleton==null) {
			singleton =new Singleton();
		}
		return singleton;
	}
	
	public static Singleton getInstance2() {
		if(singleton==null) {
			synchronized (Singleton.class) {
				if(singleton==null) {
					singleton = new Singleton();
				}
				
			}
		}
		return singleton;
	}
	
	public static Singleton getInstance3() {
		return singleton1;
	}

}
