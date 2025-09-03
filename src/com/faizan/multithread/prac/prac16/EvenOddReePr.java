package com.faizan.multithread.prac.prac16;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class EvenOddReePr {
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
			System.out.println("Even Number :"+num);
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
			System.out.println("Odd Number :"+num);
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
		EvenOddReePr evenOddPr = new EvenOddReePr();
		Thread t1= new Thread(()->evenOddPr.printEven());
		Thread t2= new Thread(()->evenOddPr.printOdd());
		
		t1.start();
		t2.start();
	}


}
