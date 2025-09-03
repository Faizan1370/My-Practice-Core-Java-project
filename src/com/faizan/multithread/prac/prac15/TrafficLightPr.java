package com.faizan.multithread.prac.prac15;

public class TrafficLightPr {
	
	private String red="red";
	private String yellow="yellow";
	private String green="green";
	private String currentColor=red;
	
	
	public synchronized void prntRed() {
		while(true) {
			while(!currentColor.equalsIgnoreCase(red)) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Current color :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor=yellow;
			notifyAll();
		}
	}
	
	public synchronized void prntYellow() {
		while(true) {
			while(!currentColor.equalsIgnoreCase(yellow)) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Current color :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor=green;
			notifyAll();
		}
	}

	
	public synchronized void prntGreen() {
		while(true) {
			while(!currentColor.equalsIgnoreCase(green)) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Current color :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor=red;
			notifyAll();
		}
	}
	
	public static void main(String[] args) {
		TrafficLightPr lightPr = new TrafficLightPr();
		Thread t1= new Thread(()->lightPr.prntRed());
		Thread t2 = new Thread(()->lightPr.prntYellow());
		Thread t3 = new Thread(()->lightPr.prntGreen());
		
		t1.start();
		t2.start();
		t3.start();
	}


}
