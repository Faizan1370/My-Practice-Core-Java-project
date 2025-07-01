package com.faizan.design.pattrens.observer.prac2;

public interface Subject {
	
	void RegisterUser(Observer observer);
	void unRegisterUser(Observer observer);
	void addNewVideo(String message);

}
