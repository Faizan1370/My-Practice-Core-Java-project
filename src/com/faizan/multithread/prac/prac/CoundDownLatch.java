package com.faizan.multithread.prac.prac;

import java.util.concurrent.CountDownLatch;

public class CoundDownLatch {
	
	 static CountDownLatch countDownLatch = new CountDownLatch(3);
	
	public synchronized void print() {
		for(int i=0;i<50;i++) {
			 System.out.println(Thread.currentThread().getName() + " -> " + i);
		}
		countDownLatch.countDown();
	}
	public static void main(String[] args) {
		CoundDownLatch coundDownLatch = new CoundDownLatch();

        new Thread(() -> coundDownLatch.print(), "Thread-1").start();
        new Thread(() -> coundDownLatch.print(), "Thread-2").start();
        new Thread(() -> coundDownLatch.print(), "Thread-3").start();

		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("All threads finished. Main thread continues.");
	}

}
