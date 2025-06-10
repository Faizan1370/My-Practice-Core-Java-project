package com.faizan.multithread.prac.prac1;

public class Volatile {
	
	static volatile boolean runnig =true;
	
	public static void chnageStatus() {
		runnig=false;
	}
	
	public void print() {
		while(runnig) {
		//for(int i=0;i<5000;i++) {
			System.out.println("hello :");
			
		//}
	}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Volatile volatile1 = new Volatile();
		Thread t1 = new Thread(()->volatile1.print());
		 t1.start();
		Thread.sleep(1);
		
		chnageStatus();
	}

}
