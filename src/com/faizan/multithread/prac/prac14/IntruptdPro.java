package com.faizan.multithread.prac.prac14;

public class IntruptdPro {
	
	public void print() {
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted() ) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		IntruptdPro   prob = new IntruptdPro();
		Thread t1 =new Thread(()->prob.print());
		t1.start();
		Thread.sleep(1000);
		t1.interrupt();
	}

}
