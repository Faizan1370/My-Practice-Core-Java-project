package com.faizan.design.pattrens.observer;

import java.util.ArrayList;
import java.util.List;

public class Channel1 implements Subject{
	
	List<Observer> list = new ArrayList<>();

	@Override
	public void registerObserver(Observer observer) {
		list.add(observer);
		
	}

	@Override
	public void unRegisterObserver(Observer observer) {
		if(list.size()>0) {
			list.remove(observer);
		}
	}

	@Override
	public void notifyObserver(String msg) {
		list.stream().forEach(t->t.update(msg));
	}
	
	public void addNewVideo(String msg) {
		notifyObserver(msg);
	}

}
