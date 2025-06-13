package com.faizan.multithread.prac.prac5;

public class VolaTileEx {
	
	private static volatile boolean runnig=true;
	
	
	public void print() {
		while(runnig) {
			System.out.println("hello");
		}
	}
	
	public static void main(String[] args) {
		VolaTileEx ex = new VolaTileEx();
		Thread t1 = new Thread(()->ex.print());
		t1.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		runnig=false;
	}

}
