package com.faizan.design.pattrens.observer.prac2;

public class User2 implements Observer{

	@Override
	public void update(String message) {
		System.out.println("Updated User2 :"+message);
		
	}

}
