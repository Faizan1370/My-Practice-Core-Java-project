package com.faizan.multithread.prac.prac22;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrrConDDReen {
	
	int num=1;
	boolean isProdced=false;
	
	ReentrantLock lock = new ReentrantLock();
	Condition produceCondition = lock.newCondition();
	Condition consumedCondition =lock.newCondition();
	
	public void produce() {
		while(true) {
			lock.lock();
			while(isProdced) {
				try {
					produceCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Produced Number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProdced=true;
			consumedCondition.signal();
			lock.unlock();
		}
	}
	
	public void consume() {
		while(true) {
			lock.lock();
			while(!isProdced) {
				try {
					consumedCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Consumed Number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProdced=false;
			num++;
			produceCondition.signal();
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		PrrConDDReen conDD = new PrrConDDReen();
		Thread t1 = new Thread(()->conDD.produce());
		Thread t2 = new Thread(()->conDD.consume());
		
		t1.start();
		t2.start();
	}


}
