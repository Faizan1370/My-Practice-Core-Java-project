package com.faizan.multithread.prac.prac15;

public class InTruptProb {

	
	

	public void print() {
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted()) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		InTruptProb prob = new InTruptProb();
		Thread t1 = new Thread(()->prob.print());
		
		t1.start();
		Thread.sleep(1000);
		t1.interrupt();
	}

}
