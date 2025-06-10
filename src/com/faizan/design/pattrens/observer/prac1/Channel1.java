package com.faizan.design.pattrens.observer.prac1;

import java.util.ArrayList;
import java.util.List;

public class Channel1 implements Subject {
	
	List<Observer> list = new ArrayList<Observer>();

	@Override
	public void addUser(Observer observer) {
		list.add(observer);
		
	}

	@Override
	public void removeUSer(Observer observer) {
		list.remove(observer);
		
	}

	@Override
	public void notifyUser(String message) {
		for(Observer obs :list) {
			obs.update(message);
		}
		
	}
	public void addNewVideo(String message) {
		notifyUser(message);
	}

}
