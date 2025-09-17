package com.faizan.multithread.prac.prac17;

public class VolTpr {
	
	static volatile boolean running =true;
	
	public void print() {
		while(running) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		VolTpr tpr = new VolTpr();
		Thread t1 = new Thread(()->tpr.print());
		t1.start();
		Thread.sleep(1000);
		running=false;
	}

}
