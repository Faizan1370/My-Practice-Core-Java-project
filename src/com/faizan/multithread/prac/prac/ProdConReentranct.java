package com.faizan.multithread.prac.prac;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProdConReentranct {
	
	static ReentrantLock lock = new ReentrantLock();
	Condition produced = lock.newCondition();
	Condition consumed = lock.newCondition();
	static boolean isProduced=false;
	static int num=1;
	
	public void produced() {
		while(true) {
			lock.lock();
			while(isProduced) {
				try {
					produced.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Produced Num :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			consumed.signal();
			isProduced=true;
			lock.unlock();
		}
	}
	
	public void consumed() {
		while(true) {
			lock.lock();
			while(!isProduced) {
				try {
					consumed.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("consumed Num :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			produced.signal();
			num++;
			isProduced=false;
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ProdConReentranct conReentranct = new ProdConReentranct();
		Thread t1 = new Thread(()->conReentranct.produced());
		Thread t2 = new Thread(()->conReentranct.consumed());
		
		t1.start();
		t2.start();
	}

}
