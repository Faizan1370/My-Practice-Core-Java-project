package com.faizan.multithread.prac.prac19;

public class ProDDcon {

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
			System.out.println("Cosumed Num :"+num);
			try {
				Thread.sleep(1000);
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
		ProDDcon dcon = new ProDDcon();
		Thread t1= new Thread(()->dcon.produce());
		Thread t2 = new Thread(()->dcon.consume());
		
		t1.start();
		t2.start();
	}
}
