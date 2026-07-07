package com.faizan.design.pattrens.observer.prac7;

public class User1 implements Observer{

	@Override
	public void update(String message) {
	   System.out.println("user 1 :"+message);
		
	}

}
