package com.faizan.design.pattrens.singleton.prac1;

public class Singleton3 {
	
	private Singleton3 singleton3;
	private Singleton3 singleton32;
	
	private static Singleton3 singleton33 = new Singleton3();
	
	
	public Singleton3 getInstance() {
		if(singleton3 == null) {
			singleton3= new Singleton3();
		}
		return singleton3;
	}
	
	public Singleton3 getInstance1() {
		if(singleton32==null) {
			synchronized (Singleton3.class) {
				if(singleton32==null) {
					return new Singleton3();
				}
			}
		}
		return singleton32;
	}
	
	public Singleton3 getInstance2() {
		return singleton33;
	}

}
