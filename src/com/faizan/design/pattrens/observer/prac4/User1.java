package com.faizan.design.pattrens.observer.prac4;

public class User1 implements Obeserver{

	@Override
	public void update(String message) {
		System.out.println("User1 update :"+message);
		
	}

}
