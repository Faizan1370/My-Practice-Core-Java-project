package com.faizan.multithread.prac.prac10;

public class VolatilePro {
	
	private static volatile boolean running=true;
	
	
	public void print() {
		while(running) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) {
		VolatilePro pro = new VolatilePro();
		Thread t1= new Thread(()->pro.print());
		
		t1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		running=false;
	}

}
