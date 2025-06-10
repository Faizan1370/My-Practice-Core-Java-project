package com.faizan.design.pattrens.observer.prac1;

public class TestObs {
	
	public static void main(String[] args) {
		User1 user1 = new User1();
		User2 user2 = new User2();
		Channel1 channel1 = new Channel1();
		channel1.addUser(user1);
		channel1.addUser(user2);
		channel1.addNewVideo("new video added");
	}

}
