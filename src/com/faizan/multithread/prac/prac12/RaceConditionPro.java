package com.faizan.multithread.prac.prac12;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionPro {
	
	//static int count=1;
	 static AtomicInteger count = new AtomicInteger(0);
	public void print() {
		for(int i=0;i<2000;i++) {
			//count++;
			count.incrementAndGet();
		}
	}
	
	public static void main(String[] args) {
		RaceConditionPro conditionPro = new RaceConditionPro();
		Thread t1 = new Thread(()->conditionPro.print());
		Thread t2 = new Thread(()->conditionPro.print());
		
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
