package com.faizan.design.pattrens.observer.prac6;

public class Person2 implements ObserverP{

	
	@Override
	public void update(String messaage) {
		System.out.println("person 2 :"+messaage);
		
	}
}
