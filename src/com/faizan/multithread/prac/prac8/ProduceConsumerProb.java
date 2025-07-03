package com.faizan.multithread.prac.prac8;

public class ProduceConsumerProb {
	int num=1;
	boolean isProduced=false;
	
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
			System.out.println("Produced num :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduced=true;
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
			System.out.println("Consumed num :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduced=false;
			num++;
			notify();
		}
	}
	
	public static void main(String[] args) {
		ProduceConsumerProb consumerProb = new ProduceConsumerProb();
		Thread t1= new Thread(()->consumerProb.produce());
		Thread t2= new Thread(()->consumerProb.consume());
		
		t1.start();
		t2.start();
	}
}