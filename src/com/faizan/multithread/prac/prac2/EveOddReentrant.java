package com.faizan.multithread.prac.prac2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class EveOddReentrant {
	int num=1;
	int limit=20;
	
	ReentrantLock lock = new ReentrantLock();
	Condition eveCondition =lock.newCondition();
	Condition oddCondition= lock.newCondition();
	
	public void printEven() {
		while(num<limit) {
			lock.lock();
			while(num%2!=0) {
				try {
					eveCondition.await();
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
			System.out.println("Odd Number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			eveCondition.signal();
			num++;
			lock.unlock();
			
		}
	}
	
	public static void main(String[] args) {
		EveOddReentrant eveOddPro =new EveOddReentrant();
		Thread t1= new Thread(()->eveOddPro.printEven());
		Thread t2 = new Thread(()->eveOddPro.printOdd());
		
		t1.start();
		t2.start();
	}

}
