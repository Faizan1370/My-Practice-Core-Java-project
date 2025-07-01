package com.faizan.multithread.prac.prac6;

public class ProdcuerConsumer {
	
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
			System.out.println("Produced Num :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduce=true;
			notify();
		}
	}
	
	public synchronized void cosume() {
		while(true) {
			while(!isProduce) {
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
			isProduce=false;
			notify();
		}
	}
	
	public static void main(String[] args) {
		ProdcuerConsumer arrayBlockingQuque = new ProdcuerConsumer();
		Thread t1 = new Thread(()->arrayBlockingQuque.produce());
		Thread t2 = new Thread(()->arrayBlockingQuque.cosume());
		t1.start();
		t2.start();
	}


}
