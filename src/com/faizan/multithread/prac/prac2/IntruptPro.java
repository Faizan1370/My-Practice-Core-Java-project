package com.faizan.multithread.prac.prac2;

public class IntruptPro {
	
	
	public void print() {
		while(!Thread.interrupted() || Thread.currentThread().isInterrupted()) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		IntruptPro intruptPro = new IntruptPro();
		Thread t1 = new Thread(()->intruptPro.print());
		t1.start();
		Thread.sleep(1000);
		t1.interrupt();
	}

}
