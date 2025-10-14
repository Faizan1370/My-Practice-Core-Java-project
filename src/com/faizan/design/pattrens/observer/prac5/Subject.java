package com.faizan.design.pattrens.observer.prac5;

public interface Subject {
	
	void addUser(Observer observer);
	void removeUser(Observer observer);
	void notifyUser(String messsage);

}
