package com.faizan.multithread.prac.prac17;

import java.util.concurrent.CountDownLatch;

public class ContDoun {
	
	static CountDownLatch countDownLatch = new CountDownLatch(3);
	

	public void print() {
		for(int i=0;i<50;i++) {
			System.out.println("hi");
		}
		countDownLatch.countDown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		ContDoun contDoun = new ContDoun();
		Thread t1= new Thread(()->contDoun.print());
		Thread t2= new Thread(()->contDoun.print());
		Thread t3= new Thread(()->contDoun.print());
		
		t1.start();
		t2.start();
		t3.start();
		
		countDownLatch.await();
	}
	

}
