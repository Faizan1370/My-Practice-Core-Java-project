package com.faizan.multithread.prac.prac1;

public class ProdConsumerPro {
	
	static int num=1;
	 static boolean isProduced=false;
	 
	 
	 public synchronized void produce() {
		 while(true) {
			 while(isProduced) {
				 try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 
			 System.out.println("produced num :"+num);
			 isProduced=true;
			 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 notify();
		 }
	 }
	 
	 public synchronized void consume() {
		 while(true) {
			 while(!isProduced) {
				 try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 
			 System.out.println("consumed num :"+num);
			 isProduced=false;
			 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 num++;
			 notify();
		 }
	 }
	 
	 public static void main(String[] args) {
		ProdConsumerPro consumerPro = new ProdConsumerPro();
		
		Thread t1 = new Thread(()->consumerPro.produce());
		Thread t2 = new Thread(()->consumerPro.consume());
		
		t1.start();
		t2.start();
	}

}
