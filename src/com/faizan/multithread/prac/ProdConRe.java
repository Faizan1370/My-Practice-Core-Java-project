package com.faizan.multithread.prac;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProdConRe {
	
	static ReentrantLock lock = new ReentrantLock();
    static Condition producer = lock.newCondition();
    static Condition consumer = lock.newCondition();
    
    static boolean isProduce =false;
    
    static int count =0;
    
    public void produce() {
    	while(true) {
    		lock.lock();
    		if(isProduce) { // we can put deadlock using same condition in both !isProduce
    			try {
					producer.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		System.out.println("produe value :"+count);
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			isProduce=true;
    		consumer.signal();
    		lock.unlock();
    	}
    }
    
    
    public void consume() {
    	while(true) {
    		lock.lock();
    		if(!isProduce) { // we can use if or while loop
    			try {
					consumer.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		System.out.println("consume value :"+count);
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			isProduce=false;
    		producer.signal();
    		count++;
    		lock.unlock();
    	}
    }
    
    public static void main(String[] args) {
		ProdConRe conRe = new ProdConRe();
		Thread t1 = new Thread(()->conRe.produce());
		Thread t2 = new Thread(()->conRe.consume());
		t1.start();
		t2.start();
	}

}
