package com.faizan.multithread.prac.prac1;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondition {
	
	//static int count =0;
	static AtomicInteger count = new AtomicInteger(0);
	
	public  void increment() {
		for(int i=0;i<2000;i++) {
			//count++;
			count.incrementAndGet();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		RaceCondition condition = new RaceCondition();
		Thread t1 = new Thread(()->condition.increment());
		Thread t2 = new Thread(()->condition.increment());
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println(count);
		
	}

}
