package com.faizan.multithread.prac.prac5;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchProb {
	
	static CountDownLatch countDownLatch = new CountDownLatch(3);
	
	public synchronized void print()
	{
		for(int i=0;i<50;i++) {
			System.out.println("Hello :"+i);
		}
		countDownLatch.countDown();
	}
	
	public static void main(String[] args) {
		CountDownLatchProb countDownLatchProb = new CountDownLatchProb();
		Thread t1 = new Thread(()->countDownLatchProb.print());
		Thread t2 = new Thread(()->countDownLatchProb.print());
		Thread t3 = new Thread(()->countDownLatchProb.print());
		
		t1.start();
		t2.start();
		t3.start();
		
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}
