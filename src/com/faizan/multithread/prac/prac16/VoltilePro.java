package com.faizan.multithread.prac.prac16;

public class VoltilePro {
	
	static volatile boolean running=true;
	
	public void print() {
		while(running) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		VoltilePro pro = new VoltilePro();
		Thread t1 = new Thread(()->pro.print());
		
		t1.start();
		
		Thread.sleep(1000);
		
		running =false;
		
	}

}
