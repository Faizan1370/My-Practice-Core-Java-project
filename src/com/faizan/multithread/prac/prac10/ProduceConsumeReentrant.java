package com.faizan.multithread.prac.prac10;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProduceConsumeReentrant {
	
	int num=1;
	boolean isProduce=false;
	
	ReentrantLock lock = new ReentrantLock();
	
	Condition produceCondition =lock.newCondition();
	Condition consumeCondition= lock.newCondition();
	
	
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
			System.out.println("Prodced number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduce =true;
			consumeCondition.signal();
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
			isProduce =false;
			num++;
			produceCondition.signal();
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ProduceConsumeReentrant consumePr = new ProduceConsumeReentrant();
		Thread t1= new Thread(()->consumePr.produce());
		Thread t2= new Thread(()->consumePr.consume());
		
		t1.start();
		t2.start();
	}

}
