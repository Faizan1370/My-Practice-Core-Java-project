package com.faizan.multithread.prac.prac2;

import java.util.concurrent.CountDownLatch;

public class CountDowPr {
	
	static CountDownLatch countDownLatch = new CountDownLatch(3);
	
	public void print() {
		for(int i=0;i<50;i++) {
			System.out.println("hi"+i);
		}
		countDownLatch.countDown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		CountDowPr countDowPr = new CountDowPr();
		Thread t1 = new Thread(()->countDowPr.print());
		Thread t2 = new Thread(()->countDowPr.print());
		Thread t3 = new Thread(()->countDowPr.print());
		
		t1.start();
		t2.start();
		t3.start();
		countDownLatch.await();
	}

}
