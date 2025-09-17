package com.faizan.multithread.prac.prac17;

import com.faizan.multithread.prac.prac16.TrafficLightPr;

public class TrafficLightPrr {

	private static String red="red";
	private static String yellow="yellow";
	private static String green="green";
	private static String currentColor=red;
	
	public synchronized void printRed() {
		while(true) {
			while(!currentColor.equalsIgnoreCase(red)) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("current color :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor =yellow;
			notifyAll();
		}
	}
	
	public synchronized void printYellow() {
		while(true) {
			while(!currentColor.equalsIgnoreCase(yellow)) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("current color :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor =green;
			notifyAll();
		}
	}
	
	public synchronized void printGreen() {
		while(true) {
			while(!currentColor.equalsIgnoreCase(green)) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("current color :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor =red;
			notifyAll();
		}
	}
	
	public static void main(String[] args) {
		TrafficLightPrr lightPr = new TrafficLightPrr();
		Thread t1 = new Thread(()->lightPr.printRed());
		Thread t2 = new Thread(()->lightPr.printYellow());
		Thread t3 = new Thread(()->lightPr.printGreen());
		
		t1.start();
		t2.start();
		t3.start();
	}


}
