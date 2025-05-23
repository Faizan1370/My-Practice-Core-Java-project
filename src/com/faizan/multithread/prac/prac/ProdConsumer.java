package com.faizan.multithread.prac.prac;

public class ProdConsumer {
	
	private static boolean isProduced=false;
	private static int num=1;
	
	public synchronized void produced() {
		while(true){
			while(isProduced) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Prodcued Number :"+num);
			isProduced=true;
			notify();
		}
	}
	
	public synchronized void consumed() {
		while(true){
			while(!isProduced) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Consumed Number :"+num);
			isProduced=false;
			num++;
			notify();
		}
	}
	
	public static void main(String[] args) {
		ProdConsumer consumer = new ProdConsumer();
		Thread t1 = new Thread(()->consumer.produced());
		Thread t2 = new Thread(()->consumer.consumed());
		
		t1.start();
		t2.start();
	}
	
	

}
