package com.faizan.multithread.prac.prac18;

public class IntruptPrrr {
	
	public void print() {
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted()) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		IntruptPrrr intruptPrrr = new IntruptPrrr();
		Thread t1 = new Thread(()->intruptPrrr.print());
		
		t1.start();
		Thread.sleep(100);
		t1.interrupt();
	}

}
