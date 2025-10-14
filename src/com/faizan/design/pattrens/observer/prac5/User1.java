package com.faizan.design.pattrens.observer.prac5;

public class User1 implements Observer{

	@Override
	public void update(String message) {
		System.out.println("User 1 +"+message);
		
	}

}
