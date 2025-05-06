package com.faizan.design.pattrens.observer;

public interface Subject {
	 
	void registerObserver(Observer observer);
    void unRegisterObserver(Observer observer);
    void notifyObserver(String msg);
	

}
