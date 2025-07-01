package com.faizan.multithread.prac.prac6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerReentract {
	
	int num=1;
	boolean isProduce=false;
	
	static ReentrantLock lock = new ReentrantLock();
	Condition produceCondition =lock.newCondition();
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
			System.out.println("Prodcued num :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduce=true;
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
			
			System.out.println("consumed num :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduce=false;
			num++;
			produceCondition.signal();
			lock.unlock();
		}
	}
	
	
	public static void main(String[] args) {
		ProducerConsumerReentract consumerReentract = new ProducerConsumerReentract();
		Thread t1 = new Thread(()->consumerReentract.produce());
		Thread t2 = new Thread(()->consumerReentract.consume());
		
		t1.start();
		t2.start();
	}

}
