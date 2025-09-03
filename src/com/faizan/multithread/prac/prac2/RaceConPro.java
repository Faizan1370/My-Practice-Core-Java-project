package com.faizan.multithread.prac.prac2;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConPro {
	
	//static int count=1;
	static AtomicInteger count= new AtomicInteger(0);
	
	public void print() {
		for(int i=0;i<2000;i++) {
			//count++;
			count.incrementAndGet();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		RaceConPro conPro = new RaceConPro();
		Thread t1= new Thread(()->conPro.print());
		Thread t2= new Thread(()->conPro.print());
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		System.out.println(count);
		
	}

}
