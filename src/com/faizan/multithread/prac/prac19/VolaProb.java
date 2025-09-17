package com.faizan.multithread.prac.prac19;

public class VolaProb {
	
	static volatile boolean running=true;
	
	public void print() {
		while(running) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		VolaProb prob = new VolaProb();
		Thread t1 = new Thread(()->prob.print());
		
		t1.start();
		
		Thread.sleep(1000);
		running=false;
	}

}
