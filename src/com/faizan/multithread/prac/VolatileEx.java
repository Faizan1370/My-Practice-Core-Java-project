package com.faizan.multithread.prac;

public class VolatileEx {
	
	
	private static volatile boolean running =true;
	
	public static void stopThread() {
		running=false;
	}
	
	public void printValue() {
		while(running) {
			for(int i=0;i<10000;i++) {
				System.out.println("runnig :"+i);
			}
		}
	}
	public static void main(String[] args) {
		new Thread(()->new VolatileEx().printValue()).start();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopThread();
	}

}
