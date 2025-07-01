package com.faizan.multithread.prac.prac7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProduceConsumeReentrant {
	
	int num=1;
	boolean isProduced=false;
	
	ReentrantLock lock = new ReentrantLock();
	
	Condition prodcueCondition = lock.newCondition();
	Condition comnsumeCondition = lock.newCondition();
	
	public void produce() throws InterruptedException {
		while(true) {
			lock.lock();
			while(isProduced) {
				prodcueCondition.await();
			}
			System.out.println("Produced Num :"+num);
			Thread.sleep(1000);
			isProduced=true;
			comnsumeCondition.signal();
			lock.unlock();
		}
	}
	
	public void consume() throws InterruptedException {
		while(true) {
			lock.lock();
			while(!isProduced) {
				comnsumeCondition.await();
			}
			System.out.println("Consumed Num :"+num);
			Thread.sleep(1000);
			isProduced=false;
			num++;
			prodcueCondition.signal();
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ProduceConsumeReentrant consumerProb = new ProduceConsumeReentrant();
		Thread t1= new Thread(()->{
			try {
				consumerProb.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(()->{
			try {
				consumerProb.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
	}
}
