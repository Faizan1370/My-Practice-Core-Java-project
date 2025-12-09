package com.faizan.multithread.prac.prac21;

public class InduptTT {
	
	public void print() {
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted()) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		InduptTT induptTT = new InduptTT();
		Thread t = new Thread(()->induptTT.print());
		
		t.start();
		
		Thread.sleep(1000);
		
		t.interrupt();
	}

}
