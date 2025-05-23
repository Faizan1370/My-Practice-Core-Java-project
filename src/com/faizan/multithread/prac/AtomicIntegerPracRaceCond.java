package com.faizan.multithread.prac;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerPracRaceCond {
	
	private static int count=0;
	
	//private static AtomicInteger count = new AtomicInteger(0);
	
	public void print() {
		for(int i=0;i<1000;i++) {
			count++;
			//count.incrementAndGet();
		}
		
	}
	
	public static void main(String[] args) {
		AtomicIntegerPracRaceCond atomicIntegerPracRaceCond = new AtomicIntegerPracRaceCond();
		Thread thread = new Thread(()->atomicIntegerPracRaceCond.print());
		Thread thread1 = new Thread(()->atomicIntegerPracRaceCond.print());
		
		thread.start();
		thread1.start();
		
	
		
		try {
			thread.join();
			thread1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Count value :"+count);
		
	}

}
