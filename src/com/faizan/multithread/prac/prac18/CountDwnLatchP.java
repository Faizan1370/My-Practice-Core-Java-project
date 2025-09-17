package com.faizan.multithread.prac.prac18;

import java.util.concurrent.CountDownLatch;

public class CountDwnLatchP {
	static CountDownLatch countDownLatch = new CountDownLatch(3);
	
	public void print() {
		for(int i=0;i<50;i++) {
			System.out.println(i);
		}
		countDownLatch.countDown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		CountDwnLatchP countDwnLatchP = new CountDwnLatchP();
		Thread t1 = new Thread(()->countDwnLatchP.print());
		Thread t2 = new Thread(()->countDwnLatchP.print());
		Thread t3 = new Thread(()->countDwnLatchP.print());
		
		t1.start();
		t2.start();
		t3.start();
		countDownLatch.await();
	}

}
