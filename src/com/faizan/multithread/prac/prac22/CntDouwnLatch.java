package com.faizan.multithread.prac.prac22;

import java.util.concurrent.CountDownLatch;

public class CntDouwnLatch {
	static CountDownLatch countDownLatch = new CountDownLatch(3);
	
	
	public void show() {
		for(int i=0;i<50;i++) {
			System.out.println(i);
		}
		countDownLatch.countDown();
	}
	public static void main(String[] args) throws InterruptedException {
		CntDouwnLatch cntDouwnLatch = new CntDouwnLatch();
		Thread t = new Thread(()->cntDouwnLatch.show());
		Thread t1 = new Thread(()->cntDouwnLatch.show());
		Thread t2 = new Thread(()->cntDouwnLatch.show());
		t.start();
		t1.start();
		t2.start();
		
		countDownLatch.await();
	} 

}
