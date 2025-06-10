package com.faizan.multithread.prac.prac4;

public class ProducerConsumerProblem {
	
	int num=1;
	boolean isProdude=false;
	
	
	public synchronized void produce() {
		while(true) {
			while(isProdude) {
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
			isProdude =true;
			notify();
		}
	}
	
	public synchronized void consume() {
		while(true) {
			while(!isProdude) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("consumed Num :"+num);
			num++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProdude =false;
			notify();
		}
	}
	
	public static void main(String[] args) {
		ProducerConsumerProblem consumerProblem = new ProducerConsumerProblem();
		Thread t1 = new Thread(()->consumerProblem.produce());
		Thread t2 = new Thread(()->consumerProblem.consume());
		t1.start();
		t2.start();
	}


}
