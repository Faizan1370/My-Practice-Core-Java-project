package com.faizan.multithread.prac.prac16;

import java.util.concurrent.CountDownLatch;

public class CountyDPr {
static	CountDownLatch countDownLatch = new CountDownLatch(3);
	
	public void print() {
		for(int i=0;i<50;i++) {
			System.out.println("hi"+i);
		}
		countDownLatch.countDown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		CountyDPr countyDPr = new CountyDPr();
		Thread t1 = new Thread(()->countyDPr.print());
		Thread t2 = new Thread(()->countyDPr.print());
		Thread t3 = new Thread(()->countyDPr.print());
		
		t1.start();
		t2.start();
		t3.start();
		countDownLatch.await();
		
	}

}
