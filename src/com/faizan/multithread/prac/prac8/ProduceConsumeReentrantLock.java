package com.faizan.multithread.prac.prac8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProduceConsumeReentrantLock {
	int num=1;
	boolean isProduced=false;
	
	ReentrantLock lock = new ReentrantLock();
	Condition produceCondition =lock.newCondition();
	Condition consumeCondition =lock.newCondition();
	
	public void produce() {
		while(true) {
			lock.lock();
			while(isProduced) {
				try {
					produceCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Prodcued num :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduced=true;
			consumeCondition.signal();
			lock.unlock();
		}
	}
	
	public void consume() {
		while(true) {
			lock.lock();
			while(!isProduced) {
				try {
					consumeCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Consumed num :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduced=false;
			produceCondition.signal();
			num++;
			lock.unlock();
		}
	}
	public static void main(String[] args) {
		ProduceConsumeReentrantLock consumerProb = new ProduceConsumeReentrantLock();
		Thread t1= new Thread(()->consumerProb.produce());
		Thread t2= new Thread(()->consumerProb.consume());
		
		t1.start();
		t2.start();
	}
	

}
