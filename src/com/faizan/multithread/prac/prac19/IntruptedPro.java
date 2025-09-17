package com.faizan.multithread.prac.prac19;

public class IntruptedPro {
	

	public void print() {
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted()) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		IntruptedPro intruptedPro = new IntruptedPro();
		Thread t1 = new Thread(()->intruptedPro.print());
		
		t1.start();
		Thread.sleep(1000);
		t1.interrupt();
	}
	

}
