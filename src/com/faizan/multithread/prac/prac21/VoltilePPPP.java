package com.faizan.multithread.prac.prac21;

public class VoltilePPPP {

	static volatile boolean running = true;

	public void print() {
		while (running) {
			System.out.println("hi");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		VoltilePPPP pppp = new VoltilePPPP();
		Thread t1 = new Thread(() -> pppp.print());

		t1.start();

		Thread.sleep(1000);

		running = false;
	}

}
