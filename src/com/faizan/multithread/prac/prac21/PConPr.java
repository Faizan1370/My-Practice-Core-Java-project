package com.faizan.multithread.prac.prac21;

public class PConPr {
	int num=1;
	boolean isProduce=false;
	
	public synchronized void produce() {
		while(true) {
			while(isProduce) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			System.out.println("produced Number :"+num);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduce=true;
			notify();
		}
	}
	
	public synchronized void consume() {
		while(true) {
			while(!isProduce) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			System.out.println("consumed Number :"+num);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduce=false;
			num++;
			notify();
		}
	}
	
	public static void main(String[] args) {
		PConPr conPr = new PConPr();
		Thread t1 = new Thread(()->conPr.produce());
		Thread t2 = new Thread(()->conPr.consume());
		t1.start();
		t2.start();
	}

}
