package com.faizan.multithread.prac.prac7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class EvenOddReentrant {
	
	int num=1;
	int limi=20;
	
	ReentrantLock lock = new ReentrantLock();
	
	Condition evenCondition = lock.newCondition();
	Condition oddCondition =lock.newCondition();
	
	public synchronized void printEven() throws InterruptedException {
		while(num<limi) {
			lock.lock();
			while(num%2!=0) {
				evenCondition.await();
			}
			System.out.println("Even No ."+num);
			Thread.sleep(1000);
			num++;
			oddCondition.signal();
			lock.unlock();
				
			}
		}
	
	
	public  void printOdd() throws InterruptedException {
		while(num<limi) {
			lock.lock();
			while(num%2==0) {
				oddCondition.await();
			}
			System.out.println("Odd No ."+num);
			Thread.sleep(1000);
			num++;
			evenCondition.signal();
			lock.unlock();	
			}
		}
	
	
	public static void main(String[] args) {
		EvenOdd consumerProb = new EvenOdd();
		Thread t1= new Thread(()->{
			try {
				consumerProb.printEven();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(()->{
			try {
				consumerProb.printOdd();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
	}

}
