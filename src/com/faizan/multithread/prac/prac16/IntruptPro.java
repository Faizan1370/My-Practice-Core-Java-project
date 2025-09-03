package com.faizan.multithread.prac.prac16;

public class IntruptPro {
	
	public void print() {
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted()) {
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
