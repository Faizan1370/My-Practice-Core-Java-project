package com.faizan.design.pattrens.observer.prac2;

import java.util.ArrayList;
import java.util.List;

public class JavaTechie implements Subject{
  List<Observer> list = new ArrayList<Observer>();
	@Override
	public void RegisterUser(Observer observer) {
	list.add(observer);
	}

	@Override
	public void unRegisterUser(Observer observer) {
		list.remove(observer);
		
	}

	@Override
	public void addNewVideo(String message) {
	  for(Observer obs:list) {
		  obs.update(message);
	  }
		
	}

}
