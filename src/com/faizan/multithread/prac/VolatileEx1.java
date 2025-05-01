package com.faizan.multithread.prac;

public class VolatileEx1 {
	
	private static volatile boolean running =true;
	
	public static void stopThread() {
		running=false;
	}
	
	public void print() {
		for(int i=0;i<10000;i++) {
			if(running) {
				System.out.println("value :"+i);
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(()->new VolatileEx1().print());
		t1.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopThread();
	}

}
