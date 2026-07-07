package com.faizan.multithread.prac.prac26;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondsa {
	 //static int  count=1;
	 static AtomicInteger count = new AtomicInteger(0);
	 
	
	public void print() {
		for(int i=0;i<2000;i++) {
			//count++;
			count.incrementAndGet();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		RaceCondsa condsa = new RaceCondsa();
		Thread t1 = new Thread(()->condsa.print());
		Thread t2 = new Thread(()->condsa.print());
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		
		System.out.println(count);
	}

}
