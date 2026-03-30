package com.faizan.multithread.prac.prac23;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionPr {
	//static int count=0;
	static AtomicInteger count = new AtomicInteger(1);
	
	public void m1() {
		for(int i=0;i<2000;i++) {
			//count++;
			count.incrementAndGet();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		RaceConditionPr conditionPr = new RaceConditionPr();
		Thread t = new Thread(()->conditionPr.m1());
		Thread t2 = new Thread(()->conditionPr.m1());
		
		t.start();
		t2.start();
		t.join();
		t2.join();
		System.out.println(count);
		
	}

}
