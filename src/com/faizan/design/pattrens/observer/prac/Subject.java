package com.faizan.design.pattrens.observer.prac;

public interface Subject {
	void registerObserver(Observer observer);
	void unregisterObserver(Observer observer);
	void notifyObserver(String message);

}
