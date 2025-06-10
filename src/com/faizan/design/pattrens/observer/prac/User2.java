package com.faizan.design.pattrens.observer.prac;

public class User2 implements Observer{

	@Override
	public void update(String message) {
		System.out.println("Updated user2  :"+message);
		
	}

}
