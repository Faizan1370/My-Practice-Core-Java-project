package com.faizan.multithread.prac.prac26;

public class InPts {
	
	public void print() {
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted()) {
			System.out.println("hi");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		InPts inPts = new InPts();
		Thread t1 = new Thread(()->inPts.print());
		
		t1.start();
		Thread.sleep(100);
		t1.interrupt();
	}

}
