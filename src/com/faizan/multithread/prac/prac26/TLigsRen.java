package com.faizan.multithread.prac.prac26;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TLigsRen {
	private String red ="red";
	private String yellow ="yellow";
	private String green ="green";
	private String currentColor=red;
	
	ReentrantLock lock = new ReentrantLock();
	Condition redCondition =lock.newCondition();
	Condition yellCondition =lock.newCondition();
	Condition grCondition = lock.newCondition();
	
	public void printRed() {
		while(true) {
			lock.lock();
			while(!currentColor.equalsIgnoreCase(red)) {
				try {
					redCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("current color :"+currentColor);
			currentColor=yellow;
			yellCondition.signal();
			lock.unlock();
		}
		}

}
