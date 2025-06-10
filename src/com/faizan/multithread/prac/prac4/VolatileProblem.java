package com.faizan.multithread.prac.prac4;

public class VolatileProblem {
	
	static volatile boolean running=true;
	
	public static void changeRunning()
	{
		running=false;
	}
	
	public void print() {
		int i=0;
		while(running) {
			System.out.println("hi "+i);
			i++;
		}
	}
	
	public static void main(String[] args) {
		VolatileProblem problem = new VolatileProblem();
		Thread t1 = new Thread(()->problem.print());
		t1.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		changeRunning();
	}
}
