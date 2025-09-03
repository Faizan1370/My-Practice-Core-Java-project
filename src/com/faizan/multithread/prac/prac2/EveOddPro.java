package com.faizan.multithread.prac.prac2;

public class EveOddPro {
	int num=1;
	int limit=20;
	
	public synchronized void printEven() {
		while(num<limit) {
			while(num%2!=0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Even Number:"+num);
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
			while(num%2==0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Odd Number:"+num);
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
		EveOddPro eveOddPro = new EveOddPro();
		Thread t1= new Thread(()->eveOddPro.printEven());
		Thread t2 = new Thread(()->eveOddPro.printOdd());
		
		t1.start();
		t2.start();
	}

}
