package com.faizan.multithread.prac.prac4;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchProblem {
	
	static CountDownLatch countDownLatch = new CountDownLatch(3);
	
	
	public synchronized void print() {
		for(int i=0;i<50;i++) {
			System.out.println("hello :"+i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		countDownLatch.countDown();
	}
	
	public static void main(String[] args) {
		CountDownLatchProblem countDownLatchProblem = new CountDownLatchProblem();
			
			Thread t1 = new Thread(()->countDownLatchProblem.print());
			Thread t2 = new Thread(()->countDownLatchProblem.print());
			Thread t3 = new Thread(()->countDownLatchProblem.print());
			
			t1.start();
			t2.start();
			t3.start();
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
