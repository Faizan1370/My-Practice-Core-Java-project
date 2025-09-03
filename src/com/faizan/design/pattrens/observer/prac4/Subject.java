package com.faizan.design.pattrens.observer.prac4;

public interface Subject {
	
	void addUser(Obeserver obeserver);
	void removeUser(Obeserver obeserver);
	void notifyUser(String message);

}
