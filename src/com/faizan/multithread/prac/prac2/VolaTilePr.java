package com.faizan.multithread.prac.prac2;

public class VolaTilePr {
	
	static volatile boolean running=true;
	
	
	public void print() {
		while(running) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		VolaTilePr pr = new VolaTilePr();
		Thread t1 = new Thread(()->pr.print());
		
		t1.start();
		Thread.sleep(1000);
		running=false;
	}

}
