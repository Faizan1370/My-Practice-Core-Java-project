package com.faizan.design.pattrens.observer.prac;

public class User1 implements Observer{

	@Override
	public void update(String message) {
		System.out.println("update user1 :"+message);
		
	}

}
