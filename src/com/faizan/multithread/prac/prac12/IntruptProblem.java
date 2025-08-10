package com.faizan.multithread.prac.prac12;

public class IntruptProblem {
	
	public void print() {
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted()) {
			System.out.println("Hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		IntruptProblem intruptProblem = new IntruptProblem();
		Thread t1= new Thread(()->intruptProblem.print());
		t1.start();
		Thread.sleep(1000);
		t1.interrupt();
				
	}

}
