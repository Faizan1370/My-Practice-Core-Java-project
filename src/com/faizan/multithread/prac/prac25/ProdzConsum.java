package com.faizan.multithread.prac.prac25;

public class ProdzConsum {
	
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
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("produce num :"+num);
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
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("consume num :"+num);
			isProduce=false;
			num++;
			notify();
		}
	}
	
	public static void main(String[] args) {
	  ProdzConsum consum = new ProdzConsum();
	  Thread t1 = new Thread(()->consum.produce());
	  Thread t2 = new Thread(()->consum.consume());
	  t1.start();
	  t2.start();
	}


}
