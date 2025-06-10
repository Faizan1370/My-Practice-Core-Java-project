package com.faizan.design.pattrens.observer.prac1;

public class User2 implements Observer{

	@Override
	public void update(String message) {
		System.out.println("update user2 :"+message);
		
	}

}
