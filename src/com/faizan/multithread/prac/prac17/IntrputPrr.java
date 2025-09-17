package com.faizan.multithread.prac.prac17;

public class IntrputPrr {
	
	public void print() {
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted()) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		IntrputPrr intrputPrr = new IntrputPrr();
		Thread t1 = new Thread(()->intrputPrr.print());
		t1.start();
		Thread.sleep(1000);
		t1.interrupt();
	}

}
