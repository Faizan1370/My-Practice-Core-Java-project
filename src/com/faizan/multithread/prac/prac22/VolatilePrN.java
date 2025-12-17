package com.faizan.multithread.prac.prac22;

public class VolatilePrN {
	static volatile boolean isRunning=true;
	
	
	public void show() {
		while(isRunning) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		VolatilePrN n = new VolatilePrN();
		Thread t1 = new Thread(()->n.show());
		
		t1.start();
		
		Thread.sleep(1000);
		isRunning=false;
	}

}
