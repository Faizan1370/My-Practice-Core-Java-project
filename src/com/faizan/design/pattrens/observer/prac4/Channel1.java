package com.faizan.design.pattrens.observer.prac4;

import java.util.ArrayList;

public class Channel1  implements Subject{
	ArrayList<Obeserver> list = new ArrayList<Obeserver>();

	@Override
	public void addUser(Obeserver obeserver) {
		list.add(obeserver);
		
	}

	@Override
	public void removeUser(Obeserver obeserver) {
		list.remove(obeserver);
		
	}

	@Override
	public void notifyUser(String message) {
		for(Obeserver obs:list) {
			obs.update(message);
		}
		
	}

}
