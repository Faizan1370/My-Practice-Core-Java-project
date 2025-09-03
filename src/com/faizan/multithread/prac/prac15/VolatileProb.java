package com.faizan.multithread.prac.prac15;

public class VolatileProb {
	
	static volatile boolean running=true;
	
	public void print() {
		while(running) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		VolatileProb prob = new VolatileProb();
		Thread t1 = new Thread(()->prob.print());
		
		t1.start();
		Thread.sleep(1000);
		running=false;
	}

}
