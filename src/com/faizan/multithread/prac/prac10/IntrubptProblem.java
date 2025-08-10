package com.faizan.multithread.prac.prac10;

public class IntrubptProblem {

	public void print() {
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted()) {
			System.out.println("hi");
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		IntrubptProblem intrubptProblem = new IntrubptProblem();
		Thread t1= new Thread(()->intrubptProblem.print());
		t1.start();
		Thread.sleep(1000);
		t1.interrupt();
	}
}
