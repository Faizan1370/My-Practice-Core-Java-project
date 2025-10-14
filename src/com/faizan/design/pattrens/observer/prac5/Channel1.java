package com.faizan.design.pattrens.observer.prac5;

import java.util.ArrayList;

public class Channel1 implements Subject{
	ArrayList<Observer> list = new ArrayList<Observer>();

	@Override
	public void addUser(Observer observer) {
		list.add(observer);
		
	}

	@Override
	public void removeUser(Observer observer) {
		list.remove(observer);
		
	}

	@Override
	public void notifyUser(String message) {
		for(Observer observer :list) {
			observer.update(message);
		}
		
	}

}
