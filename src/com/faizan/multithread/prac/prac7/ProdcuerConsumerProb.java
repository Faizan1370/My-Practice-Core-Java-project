package com.faizan.multithread.prac.prac7;

public class ProdcuerConsumerProb {
	
	int num=1;
	boolean isProduced=false;
	
	public synchronized void produce() throws InterruptedException {
		while(true) {
			while(isProduced) {
				wait();
			}
			System.out.println("Ptodcued num :"+num);
			Thread.sleep(1000);
			isProduced=true;
			notify();
		}
	}
	
	public synchronized void consume() throws InterruptedException {
		while(true) {
			while(!isProduced) {
				wait();
			}
			System.out.println("consume num :"+num);
			Thread.sleep(1000);
			isProduced=false;
			num++;
			notify();
		}
	}
	
	public static void main(String[] args) {
		ProdcuerConsumerProb consumerProb = new ProdcuerConsumerProb();
		Thread t1= new Thread(()->{
			try {
				consumerProb.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(()->{
			try {
				consumerProb.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
	}

}
