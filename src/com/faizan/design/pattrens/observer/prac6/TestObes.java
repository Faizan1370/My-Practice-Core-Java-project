package com.faizan.design.pattrens.observer.prac6;

public class TestObes {
	
	public static void main(String[] args) {
		Person1 person1 = new Person1();
		Person2 person2 = new Person2();
		MyChannel1 channel1 = new MyChannel1();
		channel1.addPerson(person1);
		channel1.addPerson(person2);
		channel1.notifyPerson("added new video");
	}

}
