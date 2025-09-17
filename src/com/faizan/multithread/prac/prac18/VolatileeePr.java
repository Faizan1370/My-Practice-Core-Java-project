package com.faizan.multithread.prac.prac18;

public class VolatileeePr {
	
	static volatile boolean running=true;
	
	public void print() {
		while(running) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		VolatileeePr pr = new VolatileeePr();
		Thread t1 = new Thread(()->pr.print());
		
		t1.start();
		Thread.sleep(1000);
		
		running=false;
	}

}
