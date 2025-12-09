package com.faizan.multithread.prac.prac21;

public class EvedOddPrr {
	int num=1;
	int limit=20;
	
	public synchronized void printEvent() {
			while(num<limit) {
				while(num % 2!=0) {
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Print even :"+num);
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
				while(num % 2==0) {
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				
				System.out.println("Print odd :"+num);
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
		EvedOddPrr evedOddPrr = new EvedOddPrr();
		Thread t1 =new  Thread(()->evedOddPrr.printEvent());
		Thread t2 = new Thread(()->evedOddPrr.printOdd());
		
		t1.start();
		t2.start();
	}

}
