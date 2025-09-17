package com.faizan.multithread.prac.prac19;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCon {
	//static int count=0;
	static AtomicInteger count= new AtomicInteger(0);
	
	public void print() {
		for(int i=0;i<2000;i++) {
			//count++;
			count.incrementAndGet();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		RaceCon con = new RaceCon();
		Thread t1 = new Thread(()->con.print());
		Thread t2 = new Thread(()->con.print());
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		System.out.println(count);
	}

}
