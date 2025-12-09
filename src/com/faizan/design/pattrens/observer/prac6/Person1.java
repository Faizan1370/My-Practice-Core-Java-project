package com.faizan.design.pattrens.observer.prac6;

public class Person1 implements ObserverP {

	@Override
	public void update(String messaage) {
		System.out.println("person 1 :"+messaage);
		
	}
	

}
