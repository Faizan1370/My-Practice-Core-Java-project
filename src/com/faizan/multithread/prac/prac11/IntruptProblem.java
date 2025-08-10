package com.faizan.multithread.prac.prac11;

public class IntruptProblem {
	
	public void print() {
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted()) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) {
		IntruptProblem intruptProblem = new IntruptProblem();
		Thread t1= new Thread(()->intruptProblem.print());
		t1.start();
		try {
			Thread.sleep(1000);
		t1.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
