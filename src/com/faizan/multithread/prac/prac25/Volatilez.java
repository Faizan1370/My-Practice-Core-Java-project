package com.faizan.multithread.prac.prac25;

public class Volatilez {
	
	private static volatile boolean running=true;
	
	public void print() {
		while(running) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Volatilez volatilez = new Volatilez();
		Thread t1 = new Thread(()->volatilez.print());
		t1.start();
		Thread.sleep(1000);
		running=false;
	}

}
