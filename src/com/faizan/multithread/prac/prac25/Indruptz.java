package com.faizan.multithread.prac.prac25;

public class Indruptz {
	
	
	public void print() {
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted()) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Indruptz intruptPr = new Indruptz();
		Thread t = new Thread(()->intruptPr.print());
		t.start();
		Thread.sleep(100);
		t.interrupt();
	}

}
