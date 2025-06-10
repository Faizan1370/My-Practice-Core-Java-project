package com.faizan.design.pattrens.observer.prac1;

public class User1 implements Observer{

	@Override
	public void update(String message) {
		System.out.println("update user1 :"+message);
		
	}

}
