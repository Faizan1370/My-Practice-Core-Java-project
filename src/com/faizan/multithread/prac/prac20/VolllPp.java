package com.faizan.multithread.prac.prac20;

public class VolllPp {
	
	private static volatile boolean running=true;
	
	public void print() {
		while(running) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		VolllPp pp = new VolllPp();
		Thread t1 = new Thread(()->pp.print());
		
		t1.start();
		
		Thread.sleep(1000);
		running=false;
	}

}
