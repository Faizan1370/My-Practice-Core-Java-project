package com.faizan.multithread.prac.prac20;

import java.util.concurrent.atomic.AtomicInteger;

public class RcccConn {
	
	//static int count=0;
static	AtomicInteger count = new AtomicInteger(0);
	
	public void print() {
		for(int i=0;i<2000;i++) {
			count.incrementAndGet();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		RcccConn conn = new RcccConn();
		
		Thread t1 = new Thread(()->conn.print());
		Thread t2 = new Thread(()->conn.print());
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(count);
	}

}
