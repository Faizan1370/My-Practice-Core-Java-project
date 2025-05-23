package com.faizan.multithread.prac;

import com.faizan.multithread.prac.prac.VolatileEx;

public class StopThread {
	
	public void running() {
		int i=0;
		while (!Thread.currentThread().isInterrupted()) {
	        System.out.println("hello " + i);
	        i++;
	    }
	    System.out.println("Thread stopped via interrupt.");
	}
	
	public static void main(String[] args) {
	    StopThread ex = new StopThread();
		Thread t1 =new Thread(()->ex.running());
		t1.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.interrupt();
	    //t1.stop(); will stop thread immiadiatly without any condition
	}

}
