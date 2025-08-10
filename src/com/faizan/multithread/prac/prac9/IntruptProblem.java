package com.faizan.multithread.prac.prac9;

public class IntruptProblem {
	
	
	public void print() {
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted()) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		IntruptProblem problem = new IntruptProblem();
		Thread t1 = new Thread(()->problem.print());
		t1.start();
		Thread.sleep(1000);
		t1.interrupt();
	}
	

}
