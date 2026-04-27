package com.faizan.multithread.prac.prac25;

public class EvenzOdd {
	int num=1;
	int limit=20;
	
	public synchronized void evenNumber() {
		while(num<limit) {
			while(num % 2!=0) {
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
			System.out.println("even number :"+num);
			num++;
			notify();
		}
	}
	
	public synchronized void oddNumber() {
		while(num<limit) {
			while(num % 2==0) {
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
			System.out.println("odd number :"+num);
			num++;
			notify();
		}
	}
	public static void main(String[] args) {
		EvenzOdd evenzOdd = new EvenzOdd();
		Thread t1 = new Thread(()->evenzOdd.evenNumber());
		Thread t2 = new Thread(()->evenzOdd.oddNumber());
		t1.start();
		t2.start();
	}

}
