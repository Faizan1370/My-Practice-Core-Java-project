package com.faizan.design.pattrens.observer.prac7;

public class User2 implements Observer{


	@Override
	public void update(String message) {
	   System.out.println("user 2 :"+message);
		
	}

}
