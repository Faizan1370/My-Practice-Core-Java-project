package com.faizan.multithread.prac.prac10;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class EvenOddReentrantProb {
	
	int num=1;
	int limit =20;
	
	ReentrantLock lock = new ReentrantLock();
	Condition evenCondition=lock.newCondition();
	Condition oddCondition = lock.newCondition();
	
	
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
			System.out.println("Even number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			oddCondition.signal();
			num++;
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
			System.out.println("Odd number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			evenCondition.signal();
			num++;
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		EvenOddReentrantProb evenOdd = new EvenOddReentrantProb();
		Thread t1= new Thread(()->evenOdd.printEven());
		Thread t2= new Thread(()->evenOdd.printOdd());
		
		t1.start();
		t2.start();
	}


}
