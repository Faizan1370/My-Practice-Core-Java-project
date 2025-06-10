package com.faizan.design.pattrens.observer.prac;

public class TestObserver {
	
	public static void main(String[] args) {
		Channel1 channel1 = new Channel1();
		User1 user1 = new User1();
		User2 user2 = new User2();
		channel1.registerObserver(user1);
		channel1.registerObserver(user2);
		channel1.addNewVideo("new video Added");
	}

}
