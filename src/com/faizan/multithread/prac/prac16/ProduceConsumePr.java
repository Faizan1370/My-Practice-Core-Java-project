package com.faizan.multithread.prac.prac16;

public class ProduceConsumePr {
	
	
	int num=1;
	boolean isProduce=false;
	
	public  synchronized void produce() {
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
		ProduceConsumePr consumePr = new ProduceConsumePr();
		Thread t1= new Thread(()->consumePr.produce());
		Thread t2= new Thread(()->consumePr.consume());
		
		t1.start();
		t2.start();
	}

}
