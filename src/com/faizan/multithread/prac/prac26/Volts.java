package com.faizan.multithread.prac.prac26;

public class Volts {
	private static volatile boolean running=true;
	
	public void print() {
		while(running) {
			System.out.println("hi");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Volts volatilez = new Volts();
		Thread t1 = new Thread(()->volatilez.print());
		t1.start();
		Thread.sleep(1000);
		running=false;
	}
}
