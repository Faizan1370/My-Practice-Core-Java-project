package com.faizan.design.pattrens.observer.prac3;

public interface Subject {
	
	void addUser(Observer observer);
	void removUser(Observer observer);
	void notifyUser(String message);

}
