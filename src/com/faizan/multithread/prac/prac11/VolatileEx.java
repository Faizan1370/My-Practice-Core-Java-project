package com.faizan.multithread.prac.prac11;

public class VolatileEx {
	
	private static volatile boolean running=true;
	
	public void print() {
		while(running) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		VolatileEx ex = new VolatileEx();
		Thread t1 = new Thread(()->ex.print());
		t1.start();
		Thread.sleep(1000);
		running=false;
	}
}
