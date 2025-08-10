package com.faizan.multithread.prac.prac9;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProduceConsumeReentrant {
	int num=1;
	boolean isProduced=false;
	
	ReentrantLock lock = new ReentrantLock();
	Condition produceCondition=lock.newCondition();
	Condition consumeCondition=lock.newCondition();
	
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
			System.out.println("Produced number :"+num);
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
			System.out.println("consumed number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduced=false;
			num++;
			produceCondition.signal();
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ProduceConsumeReentrant consumePro = new ProduceConsumeReentrant();
		Thread t1 = new Thread(()->consumePro.produce());
		Thread t2 = new Thread(()->consumePro.consume());
		
		t1.start();
		t2.start();
	}

}
