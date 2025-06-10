package com.faizan.multithread.prac.prac1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentratntProConsumePro {
	
	int num=1;
	static ReentrantLock lock = new ReentrantLock();
	Condition produceCondition = lock.newCondition();
	Condition consumeCondition =lock.newCondition();
	static boolean isProced=false;
	
	
	public void produce() {
		while(true) {
			lock.lock();
			while(isProced) {
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
			isProced=true;
			consumeCondition.signal();
			lock.unlock();
		}
	}
	
	public void consume() {
   
		while(true) {
			lock.lock();
		try {
			while(!isProced) {
				try {
					consumeCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Consumed num :"+num);
			num++;
			isProced=false;
			produceCondition.signal();
		
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
			
			
		}
	}
	
	public static void main(String[] args) {
		ReentratntProConsumePro consumePro = new ReentratntProConsumePro();
		Thread t1= new Thread(()->consumePro.produce());
		Thread t2 = new Thread(()->consumePro.consume());
				
				t1.start();
		t2.start();
	}

}
