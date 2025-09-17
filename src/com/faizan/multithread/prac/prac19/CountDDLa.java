package com.faizan.multithread.prac.prac19;

import java.util.concurrent.CountDownLatch;

public class CountDDLa {
	static CountDownLatch countDownLatch = new CountDownLatch(3);
	
	public void print() {
		for(int i=0;i<50;i++) {
			System.out.println(i);
		}
		countDownLatch.countDown();
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		CountDDLa countDDLa = new CountDDLa();
		Thread t1 = new Thread(()->countDDLa.print());
		Thread t2 = new Thread(()->countDDLa.print());
		Thread t3 = new Thread(()->countDDLa.print());
		
		t1.start();
		t2.start();
		t3.start();
		
		countDownLatch.await();
	}

}
