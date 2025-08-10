package com.faizan.multithread.prac.prac14;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchPro {
	static CountDownLatch countDownLatch = new CountDownLatch(3);
	
	public void print() {
		for(int i=0;i<50;i++) {
			System.out.println(i);
		}
		countDownLatch.countDown();
	}
	
	public static void main(String[] args) {
		CountDownLatchPro countDownLatchPro = new CountDownLatchPro();
		Thread t1= new Thread(()->countDownLatchPro.print());
		Thread t2= new Thread(()->countDownLatchPro.print());
		Thread t3= new Thread(()->countDownLatchPro.print());
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
