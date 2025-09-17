package com.faizan.multithread.prac.prac18;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCond {
	//static int count=0;
	static AtomicInteger count = new AtomicInteger(0);
	
	public void print() {
		for(int i=0;i<2000;i++) {
			//count++;
			count.incrementAndGet();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		RaceCond cond = new RaceCond();
		Thread t1 = new Thread(()->cond.print());
		Thread t2 = new Thread(()->cond.print());
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(count);
	}

}
