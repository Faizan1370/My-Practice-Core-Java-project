package com.faizan.multithread.prac.prac14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class EvenOddReentrant {
	int num=1;
	int limit=20;
	
	ReentrantLock lock = new ReentrantLock();
	Condition evenCondition =lock.newCondition();
	Condition oddCondition =lock.newCondition();
	
	public void printEven() {
		while(num<limit) {
			lock.lock();
			while(num%2!=0) {
				try {
					evenCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Even :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			num++;
			oddCondition.signal();
			lock.unlock();
			
		}
	}
	
	public void printOdd() {
		while(num<limit) {
			lock.lock();
			while(num%2==0) {
				try {
					oddCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Odd :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			num++;
			evenCondition.signal();
			lock.unlock();
			
		}
	}
	public static void main(String[] args) {
		EvenOddReentrant evenOdd = new EvenOddReentrant();
		Thread t1 = new Thread(()->evenOdd.printEven());
		Thread t2 = new Thread(()->evenOdd.printOdd());
		
		t1.start();
		t2.start();
	}

}
