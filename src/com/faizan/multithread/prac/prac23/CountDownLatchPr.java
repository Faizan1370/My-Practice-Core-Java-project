package com.faizan.multithread.prac.prac23;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchPr {
	
	static CountDownLatch countDownLatch = new CountDownLatch(3);
	public void print() {
		for(int i=0;i<50;i++) {
			System.out.println("hi"+i);
		}
		System.out.println("*****************");
		countDownLatch.countDown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatchPr countDownLatchPr = new CountDownLatchPr();
		Thread t1 = new Thread(()->countDownLatchPr.print());
		Thread t2 = new Thread(()->countDownLatchPr.print());
		Thread t3 = new Thread(()->countDownLatchPr.print());
		
		t1.start();
		t2.start();
		t3.start();
		
		countDownLatch.await();
	}

}
