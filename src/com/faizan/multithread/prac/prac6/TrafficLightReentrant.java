package com.faizan.multithread.prac.prac6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficLightReentrant {
	
	private static String red="red";
	private static String yellow="yellow";
	private static String green="green";
	
	private static String currentColor=red;
	ReentrantLock lock = new ReentrantLock();
	
	Condition redCondition = lock.newCondition();
	Condition yellowCondition = lock.newCondition();
	Condition greenCondition = lock.newCondition();
	
	public void printRed() throws InterruptedException {
		while(true) {
			lock.lock();
			while(!currentColor.equalsIgnoreCase(red)) {
				redCondition.await();
			}
			System.out.println("current color is :"+currentColor);
			Thread.sleep(1000);
			currentColor=yellow;
			yellowCondition.signal();
			lock.unlock();
		}
	}
	public void printYellow() throws InterruptedException {
		while(true) {
			lock.lock();
			while(!currentColor.equalsIgnoreCase(yellow)) {
				yellowCondition.await();
			}
			System.out.println("current color is :"+currentColor);
			Thread.sleep(1000);
			currentColor=green;
			greenCondition.signal();
			lock.unlock();
		}
	}
	public void printGreen() throws InterruptedException {
		while(true) {
			lock.lock();
			while(!currentColor.equalsIgnoreCase(green)) {
				greenCondition.await();
			}
			System.out.println("current color is :"+currentColor);
			Thread.sleep(1000);
			currentColor=red;
			redCondition.signal();
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		TrafficLightReentrant light = new TrafficLightReentrant();
		Thread t1 = new Thread(()->{
			try {
				light.printRed();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(()->{
			try {
				light.printYellow();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Thread t3 = new Thread(()->{
			try {
				light.printGreen();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		t1.start();
		t2.start();
		t3.start();
		
		
		
	}

}
