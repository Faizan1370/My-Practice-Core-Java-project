package com.faizan.multithread.prac.prac8;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionProb {
	//static int count =0;
	static AtomicInteger count = new AtomicInteger(0);
	
	public  void print() {
		for(int i=0;i<2000;i++) {
			//count++;
			count.incrementAndGet();
		}
	}
	public static void main(String[] args) {
		RaceConditionProb conditionProb = new RaceConditionProb();
		Thread t1 = new Thread(()->conditionProb.print());
		Thread t2 = new Thread(()->conditionProb.print());
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count);
	}

}
