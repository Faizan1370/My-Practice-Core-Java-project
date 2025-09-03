package com.faizan.design.pattrens.observer.prac4;

public class Test {
	public static void main(String[] args) {
		User1 uer1 = new User1();
		User2 user2 = new User2();
		Channel1 channel1 = new Channel1();
		channel1.addUser(uer1);
		channel1.addUser(user2);
		channel1.notifyUser("added new video");
	}

}
