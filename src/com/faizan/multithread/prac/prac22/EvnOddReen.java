package com.faizan.multithread.prac.prac22;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class EvnOddReen {
	int num=1;
   
	ReentrantLock lock = new ReentrantLock();
	Condition evenCondition = lock.newCondition();
	Condition oddCondition = lock.newCondition();
	
	public void printEven() {
		while(true) {
			lock.lock();
			while(num % 2!=0) {
				try {
					evenCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Evene Numbder :"+num);
			num++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			oddCondition.signal();
			lock.unlock();
		}
	}
	
	public void printOdd() {
		while(true) {
			lock.lock();
			while(num % 2==0) {
				try {
					oddCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Odd Numbder :"+num);
			num++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			evenCondition.signal();
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		EvnOddReen evnOdd = new EvnOddReen();
		Thread t1 = new Thread(()->evnOdd.printEven());
		Thread t2 = new Thread(()->evnOdd.printOdd());
		
		t1.start();
		t2.start();
	}

}
