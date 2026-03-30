package com.faizan.multithread.prac.prac23;

public class IntruptPr {
	
	public void print() {
		while(!Thread.currentThread().isInterrupted()|| !Thread.interrupted()) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		IntruptPr intruptPr = new IntruptPr();
		Thread t = new Thread(()->intruptPr.print());
		t.start();
		Thread.sleep(100);
		t.interrupt();
	}

}
