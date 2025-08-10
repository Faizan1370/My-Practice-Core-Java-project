package com.faizan.design.pattrens.observer.prac3;

public class User1 implements Observer{

	@Override
	public void update(String message) {
		System.out.println("Upadte user1 :"+message);
	}

}
