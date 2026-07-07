package com.faizan.multithread.prac.prac26;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.faizan.multithread.prac.prac22.EvnOddReen;

public class EvnOddRs {
	
	int num=1;
	int limit =20;
	
	ReentrantLock lock = new ReentrantLock();
	Condition evenCondition =lock.newCondition();
	Condition oddCondition =lock.newCondition();
	
	  public void printEven() {
		while(num<limit) {
			lock.lock();
			while(num %2!=0) {
				try {
					evenCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("even Number :"+num);
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
				while(num %2==0) {
					try {
						oddCondition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("odd Number :"+num);
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
		EvnOddReen evnOddReen = new EvnOddReen();
		Thread t1 = new Thread(()->evnOddReen.printEven());
		Thread t2 = new Thread(()->evnOddReen.printOdd());
		
		t1.start();
		t2.start();
	}

}
