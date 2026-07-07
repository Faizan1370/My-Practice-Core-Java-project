package com.faizan.design.pattrens.observer.prac7;

import java.util.ArrayList;

public class Channel1 implements Subject{
	
	
	ArrayList<Observer> list = new ArrayList<Observer>();

	@Override
	public void registerObserver(Observer observer) {
		list.add(observer);
		
	}

	@Override
	public void unresgisterObeserver(Observer observer) {
		list.remove(observer);
		
	}

	@Override
	public void notifyObserver(String meaage) {
		for(Observer ib :list) {
			ib.update(meaage);
		}
		
	}

}
