package com.faizan.multithread.prac.prac20;

import java.util.concurrent.CountDownLatch;

public class CoundDDDLl {
	
	
	 static CountDownLatch countDownLatch = new CountDownLatch(3);
	
	public void print() {
		for(int i=0;i<50;i++) {
			System.out.println(i);
		}
		countDownLatch.countDown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		CoundDDDLl coundDDDLl = new CoundDDDLl();
		Thread t1 = new Thread(()->coundDDDLl.print());
		Thread t2 = new Thread(()->coundDDDLl.print());
		Thread t3 = new Thread(()->coundDDDLl.print());
		
		t1.start();
		t2.start();
		t3.start();
		
		countDownLatch.await();
	}

}
