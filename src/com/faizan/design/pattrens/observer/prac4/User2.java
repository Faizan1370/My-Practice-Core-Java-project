package com.faizan.design.pattrens.observer.prac4;

public class User2 implements Obeserver{

	@Override
	public void update(String message) {
		System.out.println("User2 update :"+message);
		
	}

}
