package com.faizan.multithread.prac.prac21;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondddd {
	//static int count=0;
	static AtomicInteger count = new AtomicInteger(0);
	
	public void print() {
		for(int i=0;i<2000;i++) {
			//count++;
			count.incrementAndGet();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		RaceCondddd condddd = new RaceCondddd();
		Thread t1 = new Thread(()->condddd.print());
		Thread t2= new Thread(()->condddd.print());
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println(count);
	}

}
