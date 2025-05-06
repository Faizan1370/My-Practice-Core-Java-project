package com.faizan.design.pattrens.observer;

public class User1 implements Observer{

	@Override
	public void update(String msg) {
		System.out.println("User1 Notifioed :"+msg);
		
	}

}
