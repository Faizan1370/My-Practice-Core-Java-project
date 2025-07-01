package com.faizan.design.pattrens.observer.prac2;

public class TestObs {
	
	public static void main(String[] args) {
		JavaTechie javaTechie = new JavaTechie();
		User1 user1 = new User1();
		User2 user2 = new User2();
		javaTechie.RegisterUser(user1);
		javaTechie.RegisterUser(user2);
		javaTechie.addNewVideo("added new video");
	}

}
