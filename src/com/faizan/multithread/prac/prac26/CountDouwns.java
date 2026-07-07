package com.faizan.multithread.prac.prac26;

import java.util.concurrent.CountDownLatch;

public class CountDouwns {
	static CountDownLatch countDownLatch = new CountDownLatch(3);
	
	public void print() {
		for(int i=0;i<50;i++) {
			System.out.println(i);
		}
		System.out.println("******************");
		countDownLatch.countDown();
	}
	public static void main(String[] args) throws InterruptedException {
		CountDouwns countDouwns = new CountDouwns();
		Thread thread = new Thread(()->countDouwns.print());
		Thread thread1 = new Thread(()->countDouwns.print());
		Thread thread2 = new Thread(()->countDouwns.print());
		thread.run();
		thread1.run();
		thread2.run();
		
		thread.join();
		thread1.join();
		thread2.join();
		countDownLatch.await();
		
	}

}
