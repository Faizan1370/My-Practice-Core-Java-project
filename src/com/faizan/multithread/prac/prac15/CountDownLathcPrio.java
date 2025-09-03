package com.faizan.multithread.prac.prac15;

import java.util.concurrent.CountDownLatch;

public class CountDownLathcPrio {
	static CountDownLatch countDownLatch = new CountDownLatch(3);
	
	public void print() {
		for(int i=0;i<50;i++) {
			System.out.println(i);
		}
		countDownLatch.countDown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLathcPrio countDownLathcPrio = new CountDownLathcPrio();
		Thread t1= new Thread(()->countDownLathcPrio.print());
		Thread t2= new Thread(()->countDownLathcPrio.print());
		Thread t3= new Thread(()->countDownLathcPrio.print());
		
		t1.start();
		t2.start();
		t3.start();
		
		countDownLatch.await();
		
	}

}
