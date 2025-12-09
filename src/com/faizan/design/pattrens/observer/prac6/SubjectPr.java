package com.faizan.design.pattrens.observer.prac6;

public interface SubjectPr {
	
	public void addPerson(ObserverP observerP);
	public void removePerson(ObserverP observerP);
	public void notifyPerson(String message);

}
