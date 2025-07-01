package com.faizan.design.pattrens.observer.prac2;

public class User1 implements Observer{

	@Override
	public void update(String message) {
		System.out.println("updates User1 :"+message);
		
	}

}
