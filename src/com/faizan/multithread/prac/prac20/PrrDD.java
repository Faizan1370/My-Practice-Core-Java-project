package com.faizan.multithread.prac.prac20;

public class PrrDD {
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
			System.out.println("Produced number "+num);
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
			System.out.println("Cosumed number "+num);
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
		PrrDD dd = new PrrDD();
		Thread t1 = new Thread(()->dd.produce());
		Thread t2 = new Thread(()->dd.consume());
		
		t1.start();
		t2.start();
	}

}
