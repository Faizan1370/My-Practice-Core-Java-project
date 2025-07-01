package com.faizan.design.pattrens.singleton.prac1;

public class Singleton2 {
	
	private static Singleton2 singleton2;
	
	private static Singleton2 singleton22;
	
	private static Singleton2 singleton23 = new Singleton2();
	
	
	public static Singleton2 getInstacne() {
		if(singleton2 ==null) {
			singleton2= new Singleton2();
		}
		return singleton2;
	}
 
	public static Singleton2 getInstance2() {
		if(singleton22 ==null) {
			synchronized (Singleton2.class) {
				if(singleton22==null) {
					singleton22= new Singleton2();
				}
				
			}
		}
		return singleton22;
	}
	
	public static Singleton2 getInstance3() {
		return singleton23;
	}
}
