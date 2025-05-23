package com.faizan.multithread.prac.prac;

public class VolatileEx {
	
	static volatile boolean running=true;
	
	public void chnageStatus() {
		running=false;
	}
	
	public void running() {
		int i=0;
		while (!Thread.currentThread().isInterrupted()) {
	        System.out.println("hello " + i);
	        i++;
	    }
	    System.out.println("Thread stopped via interrupt.");
	}
	
	public static void main(String[] args) {
		VolatileEx  ex = new VolatileEx();
		Thread t1 =new Thread(()->ex.running());
		t1.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.interrupt();
		//ex.chnageStatus();
	}

}
