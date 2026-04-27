package com.faizan.multithread.prac.prac25;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class EvenzOddReen {
	int num=1;
	int limit=20;
	ReentrantLock lock = new ReentrantLock();
	Condition evenCondition = lock.newCondition();
	Condition oddCondition =lock.newCondition();
	
	public void evenNumber() {
		while(num<limit) {
			lock.lock();
			while(num % 2!=0) {
				try {
					evenCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("even num :"+num);
			num++;
			oddCondition.signal();
			lock.unlock();
		}
	}
	public void oddNumber() {
		while(num<limit) {
			lock.lock();
			while(num % 2==0) {
				try {
					oddCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("odd num :"+num);
			num++;
			evenCondition.signal();
			lock.unlock();
		}
	}
	public static void main(String[] args) {
		EvenzOddReen evenzOdd = new EvenzOddReen();
		Thread t1 = new Thread(()->evenzOdd.evenNumber());
		Thread t2 = new Thread(()->evenzOdd.oddNumber());
		t1.start();
		t2.start();
	}


}
