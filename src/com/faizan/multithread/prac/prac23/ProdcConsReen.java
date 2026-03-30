package com.faizan.multithread.prac.prac23;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProdcConsReen {
	int num = 1;
	boolean isProduced = false;
    
	ReentrantLock lock = new ReentrantLock();
	Condition produceCondition = lock.newCondition();
	Condition consumeCondition = lock.newCondition();
	
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
			System.out.println("Produced Number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduced =true;
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
			System.out.println("Consumed number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduced =false;
			num++;
			produceCondition.signal();
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		ProdcConsReen cons = new ProdcConsReen();
		Thread t1 = new Thread(() -> cons.produce());
		Thread t2 = new Thread(() -> cons.consume());

		t1.start();
		t2.start();
	}

}
