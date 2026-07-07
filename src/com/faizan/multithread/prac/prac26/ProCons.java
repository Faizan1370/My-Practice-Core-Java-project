package com.faizan.multithread.prac.prac26;

public class ProCons {
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
			System.out.println("produce num :"+num);
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
			System.out.println("consume num :"+num);
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
		ProCons cons = new ProCons();
		Thread t1 = new Thread(()->cons.produce());
		Thread t2 = new Thread(()->cons.consume());
		
		t1.start();
		t2.start();
	}

}
