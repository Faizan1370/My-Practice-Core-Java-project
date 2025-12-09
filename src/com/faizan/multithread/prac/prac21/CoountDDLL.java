package com.faizan.multithread.prac.prac21;

import java.util.concurrent.CountDownLatch;

public class CoountDDLL {
	
	static CountDownLatch countDownLatch = new CountDownLatch(3);
	
	
	public void print() {
		for(int i=0;i<50;i++) {
			System.out.println(i);
		}
		countDownLatch.countDown();
		System.out.println("-----");
	}
	
	public static void main(String[] args) {
		CoountDDLL coountDDLL = new CoountDDLL();
		Thread t1= new Thread(()->coountDDLL.print());
		Thread t2= new Thread(()->coountDDLL.print());
		Thread t3= new Thread(()->coountDDLL.print());
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
