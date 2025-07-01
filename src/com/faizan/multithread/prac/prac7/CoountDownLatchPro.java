package com.faizan.multithread.prac.prac7;

import java.util.concurrent.CountDownLatch;

public class CoountDownLatchPro {
	
	static CountDownLatch countDownLatch = new CountDownLatch(3);
	
	public synchronized void print() {
		for(int i=0;i<50;i++) {
			System.out.println("helll "+i);
		}
		countDownLatch.countDown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		CoountDownLatchPro coountDownLatchPro = new CoountDownLatchPro();
		Thread thread = new Thread(()->coountDownLatchPro.print());
		Thread thread1 = new Thread(()->coountDownLatchPro.print());
		Thread thread2 = new Thread(()->coountDownLatchPro.print());
		
		thread.start();
		thread1.start();
		thread2.start();
		
		countDownLatch.await();
	}

}
