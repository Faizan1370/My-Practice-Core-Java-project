package com.faizan.multithread.prac.prac4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerProblemReentantLock {
	
	int num=1;
	boolean isProduce=false;
	
	static ReentrantLock lock = new ReentrantLock();
	Condition produceCondition = lock.newCondition();
	Condition consumeCondition = lock.newCondition();
	
	public void produce() {
		while(true) {
			lock.lock();
			while(isProduce) {
				try {
					produceCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Produced number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			consumeCondition.signal();
			isProduce =true;
			lock.unlock();
		}
	}
	

	public void consume() {
		while(true) {
			lock.lock();
			while(!isProduce) {
				try {
					consumeCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("consumed number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			num++;
			produceCondition.signal();
			isProduce =false;
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ProducerConsumerProblemReentantLock consumerProblemReentantLock = new ProducerConsumerProblemReentantLock();
		Thread t1= new Thread(()->consumerProblemReentantLock.produce());
		Thread t2 = new Thread(()->consumerProblemReentantLock.consume());
		
		t1.start();
		t2.start();
	}


}
