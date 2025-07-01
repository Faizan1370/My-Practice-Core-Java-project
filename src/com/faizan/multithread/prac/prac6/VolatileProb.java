package com.faizan.multithread.prac.prac6;

public class VolatileProb {
	
	private static volatile boolean running=true;
	
	public void print() {
		while(running) {
			System.out.println("running ....");
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
