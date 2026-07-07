package com.faizan.multithread.prac.prac26;

public class EvnOdds {
	int num=1;
	int limit=20;
	
	public synchronized void printEven() {
		while(num<limit) {
			while(num % 2 !=0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("event num :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			num++;
			notify();
		}
	}
	
	public synchronized void printOdd() {
		while(num<limit) {
			while(num % 2 ==0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("odd num :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			num++;
			notify();
		}
	}
	public static void main(String[] args) {
		EvnOdds evnOdds = new EvnOdds();
		Thread t1 = new Thread(()->evnOdds.printEven());
		Thread t2 = new Thread(()->evnOdds.printOdd());
		
		t1.start();
		t2.start();
	}

}
