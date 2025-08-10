package com.faizan.multithread.prac.prac12;

public class VolatileProb {
	
	static volatile boolean running=true;
	
	public void print() {
		while(running) {
			System.out.println("hi");
		}
	}
	
	public static void main(String[] args) {
		VolatileProb prob = new VolatileProb();
		Thread t1= new Thread(()->prob.print());
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
