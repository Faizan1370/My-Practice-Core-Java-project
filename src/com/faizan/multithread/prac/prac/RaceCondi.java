package com.faizan.multithread.prac.prac;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondi {
	
	//static int count=1;
	static AtomicInteger count = new AtomicInteger(0);
	
	public void print() {
		for(int i=0;i<2000;i++) {
			//count++;
			count.incrementAndGet();
		}
	}
	
	public static void main(String[] args) {
		RaceCondi consumer = new RaceCondi();
		Thread t1 = new Thread(()->consumer.print());
		Thread t2 = new Thread(()->consumer.print());
		
		t1.start();
		t2.start();
		
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("count :"+count);
	}

}
