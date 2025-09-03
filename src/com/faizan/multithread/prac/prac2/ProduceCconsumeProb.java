package com.faizan.multithread.prac.prac2;

public class ProduceCconsumeProb {
	
	int num=1;
	boolean isProduce=false;
	
	
	public synchronized void  produce() {
		while(true) {
			while(isProduce) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Produced Number :"+num);
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
	
	public synchronized void  consume() {
		while(true) {
			while(!isProduce) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Consumed Number :"+num);
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
		ProduceCconsumeProb cconsumeProb = new ProduceCconsumeProb();
		Thread t1= new Thread(()->cconsumeProb.produce());
		Thread t2= new Thread(()->cconsumeProb.consume());
		
		t1.start();
		t2.start();
	}

}
