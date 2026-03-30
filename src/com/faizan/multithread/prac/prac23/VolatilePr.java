package com.faizan.multithread.prac.prac23;

public class VolatilePr {
	
	private static volatile boolean running=true;
	
	public void print() {
		while(running) {
			System.out.println("running...");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		VolatilePr pr = new VolatilePr();
		Thread t1 = new Thread(()->pr.print());
		
		t1.start();
		Thread.sleep(1000);
		running=false;
	}

}
