package com.faizan.multithread.prac;

public class ProducerConsumerExer {
	
	boolean isProduced=false;
	static int value=1;
	
	public synchronized void produce() {
		while(true) {
			if(isProduced) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Produce Number is :"+value);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduced=true;
			//value++;
			notify();
		}
	}
	
	public synchronized void consume() {
		while(true) {
			if(!isProduced) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Consume Number is :"+value);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduced=false;
			value++;
			notify();
		}
	}
	
	public static void main(String[] args) {
		ProducerConsumerExer consumerExer = new ProducerConsumerExer();
		new Thread(()->consumerExer.produce()).start();
		new Thread(()->consumerExer.consume()).start();
	}

}
