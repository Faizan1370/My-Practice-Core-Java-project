package com.faizan.multithread.prac.prac7;

public class Volatile {
	
	static volatile boolean running=true;
	
	
	public void print() {
		while(running) {
			System.out.println("running");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(()->new Volatile().print());
		t1.start();
		
		Thread.sleep(1000);
		
		running =false;
	}

}
