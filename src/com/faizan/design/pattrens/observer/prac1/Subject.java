package com.faizan.design.pattrens.observer.prac1;

public interface Subject {
	void addUser(Observer observer);
	void removeUSer(Observer observer);
	void notifyUser(String message);

}
