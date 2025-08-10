package com.faizan.design.pattrens.observer.prac3;

import java.util.ArrayList;

public class Channel1 implements Subject {
	
	ArrayList<Observer> list = new ArrayList<Observer>();

	@Override
	public void addUser(Observer observer) {
		list.add(observer);
		
	}

	@Override
	public void removUser(Observer observer) {
		list.remove(observer);
		
	}

	@Override
	public void notifyUser(String message) {
		for(Observer obs:list) {
			obs.update(message);
		}
		
	}

}
