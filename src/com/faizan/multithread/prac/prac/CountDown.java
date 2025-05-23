package com.faizan.multithread.prac.prac;

import java.util.concurrent.CountDownLatch;

public class CountDown {
	
	static CountDownLatch countDownLatch = new CountDownLatch(3);	
	public void print() {
		for(int i=0;i<50;i++) {
			 System.out.println(Thread.currentThread().getName() + " -> " + i);
		}
		countDownLatch.countDown();
	}
	
	public static void main(String[] args) {
		CountDown consumer = new CountDown();
		Thread t1 = new Thread(()->consumer.print());
		Thread t2 = new Thread(()->consumer.print());
		Thread t3 = new Thread(()->consumer.print());
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("all thread finished");
	}

}
