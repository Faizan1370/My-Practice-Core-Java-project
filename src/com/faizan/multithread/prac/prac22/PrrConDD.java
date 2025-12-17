package com.faizan.multithread.prac.prac22;

public class PrrConDD {
	int num=1;
	boolean isProdced=false;
	
	
	public synchronized void produce() {
		while(true) {
			while(isProdced) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Produced Num :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProdced=true;
			notify();
		}
	}
	
	public synchronized void consume() {
		while(true) {
			while(!isProdced) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Consumed Num :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			num++;
			isProdced=false;
			notify();
		}
	}
	
	public static void main(String[] args) {
		PrrConDD conDD = new PrrConDD();
		Thread t1 = new Thread(()->conDD.produce());
		Thread t2 = new Thread(()->conDD.consume());
		
		t1.start();
		t2.start();
	}

}
