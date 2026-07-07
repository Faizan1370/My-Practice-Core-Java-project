package com.faizan.design.pattrens.observer.prac7;

public interface Subject {
	void registerObserver(Observer observer);
	void unresgisterObeserver(Observer observer);
	void notifyObserver(String meaage);

}
