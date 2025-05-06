package com.faizan.design.pattrens.observer;

public class User2 implements Observer{

	@Override
	public void update(String msg) {
		System.out.println("User2 Notified :"+msg);
		
	}

}
