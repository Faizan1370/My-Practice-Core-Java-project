package com.faizan.design.pattrens.observer.prac;

import java.util.ArrayList;
import java.util.List;

public class Channel1 implements Subject{
	
	List<Observer> list = new ArrayList<>();

	@Override
	public void registerObserver(Observer observer) {
		list.add(observer);
		
	}

	@Override
	public void unregisterObserver(Observer observer) {
		list.remove(observer);
		
	}

	@Override
	public void notifyObserver(String message) {
		for(Observer observer :list) {
			observer.update(message);
		}
		
	}
	
	public void addNewVideo(String message) {
		notifyObserver(message);
	}

}
