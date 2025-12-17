package com.faizan.multithread.prac.prac22;

public class IntrupPrrr {
	
	public void show() {
		
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted()) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		IntrupPrrr intrupPrrr = new IntrupPrrr();
		Thread t = new Thread(()->intrupPrrr.show());
		t.start();
		
		Thread.sleep(100);
		t.interrupt();
	}

}
