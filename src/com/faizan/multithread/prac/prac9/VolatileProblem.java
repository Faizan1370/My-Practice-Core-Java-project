package com.faizan.multithread.prac.prac9;

public class VolatileProblem {
	
	static volatile boolean running=true;
	
	public void print() {
		while(running) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		VolatileProblem  problem = new VolatileProblem();
		Thread t1 = new Thread(()->problem.print());
		t1.start();
		Thread.sleep(1000);
		//t1.interrupt();
		t1.stop();
		//running=false;
	}
	

}
