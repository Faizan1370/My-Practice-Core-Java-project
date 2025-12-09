package com.faizan.design.pattrens.observer.prac6;

import java.util.ArrayList;

public class MyChannel1 implements SubjectPr{
	ArrayList<ObserverP> list = new ArrayList<ObserverP>();

	@Override
	public void addPerson(ObserverP observerP) {
		list.add(observerP);
		
	}

	@Override
	public void removePerson(ObserverP observerP) {
		list.remove(observerP);
		
	}

	@Override
	public void notifyPerson(String message) {
		for(ObserverP observerP : list) {
			observerP.update(message);
		}
		
	}
	
	

}
