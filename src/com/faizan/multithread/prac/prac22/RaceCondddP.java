package com.faizan.multithread.prac.prac22;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondddP {
	 //static int count=0;
	static AtomicInteger count = new AtomicInteger(0);
	 
	 
	 public void show() {
		 for(int i=0;i<2000;i++) {
			 count.incrementAndGet();
		 }
	 }
	 
	 public static void main(String[] args) throws InterruptedException {
		RaceCondddP condddP = new RaceCondddP();
		Thread t1 = new Thread(()->condddP.show());
		Thread t2 = new Thread(()->condddP.show());
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		System.out.println(count);
	}

}
