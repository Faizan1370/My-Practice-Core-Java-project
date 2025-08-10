package com.faizan.design.pattrens.observer.prac3;

public class User2 implements Observer{

	@Override
	public void update(String message) {
	System.out.println("Update User2 :"+message);
		
	}

}
