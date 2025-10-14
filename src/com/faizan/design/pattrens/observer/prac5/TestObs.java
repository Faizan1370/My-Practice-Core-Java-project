package com.faizan.design.pattrens.observer.prac5;

public class TestObs {
	
	public static void main(String[] args) {
		Channel1 channel1 = new Channel1();
		User1 user1 = new User1();
		User2 user2 = new User2();
		channel1.addUser(user1);
		channel1.addUser(user2);
		channel1.notifyUser("new Video added");;
	}

}
