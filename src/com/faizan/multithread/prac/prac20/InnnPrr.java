package com.faizan.multithread.prac.prac20;

public class InnnPrr {
	
	public void print() {
		
		while(!Thread.currentThread().isInterrupted() || !Thread.interrupted()) {
			System.out.println("hi");
		}
	}
	
   public static void main(String[] args) throws InterruptedException {
	 InnnPrr innnPrr = new InnnPrr();
	 Thread t1 = new Thread(()->innnPrr.print());
	 t1.start();
	 Thread.sleep(100);
	 
	 t1.interrupt();
}

}
