package com.faizan.multithread.prac.prac20;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TtLLReen {
	
	private String red="red";
	private String yellow="yellow";
	private String green="green";
	
	
	private String currentColor=red;
	
	ReentrantLock lock = new ReentrantLock();
	Condition redCondition = lock.newCondition();
	Condition yellowCondition =lock.newCondition();
	Condition greenCondition = lock.newCondition();
	
	public void printRed(){
		while(true) {
			lock.lock();
			while(!currentColor.equalsIgnoreCase(red)) {
				try {
					redCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("current color is :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor =yellow;
			yellowCondition.signal();
			lock.unlock();
		}
	}
	
	public void printYellow(){
		while(true) {
			lock.lock();
			while(!currentColor.equalsIgnoreCase(yellow)) {
				try {
					yellowCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("current color is :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor =green;
			greenCondition.signal();
			lock.unlock();
		}
	}
	
	public void printGreen(){
		while(true) {
			lock.lock();
			while(!currentColor.equalsIgnoreCase(green)) {
				try {
					greenCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("current color is :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor =red;
			redCondition.signal();
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		TtLLReen tt = new TtLLReen();
		Thread t1 = new Thread(()->tt.printRed());
		Thread t2 = new Thread(()->tt.printYellow());
		Thread t3 = new Thread(()->tt.printGreen());
		
		t1.start();
		t2.start();
		t3.start();
	}
}
