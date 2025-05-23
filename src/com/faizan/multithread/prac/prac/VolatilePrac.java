package com.faizan.multithread.prac.prac;

public class VolatilePrac {
	
	static volatile boolean running=true;
	
	public void changeStatus() {
		running=false;
	}
	
	
	public void print() {
		while(running) {
			//for(int i=0;i<1000;i++) {
				System.out.println("helo");
			//}
		}
	}
	
	public static void main(String[] args) {
		VolatilePrac prac = new VolatilePrac();
		Thread t1 = new Thread(()->prac.print());
		t1.start();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		prac.changeStatus();
	}

}
