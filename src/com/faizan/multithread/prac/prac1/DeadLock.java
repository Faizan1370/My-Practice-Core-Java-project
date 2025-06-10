package com.faizan.multithread.prac.prac1;

public class DeadLock {
	
	private String res1="res1";
	private String res2="res2";
	
	
	public void print() {
		synchronized (res1) {
			System.out.println("res 1 locked by :"+Thread.currentThread().getName());
			synchronized (res2) {
				System.out.println("res 2 locked by :"+Thread.currentThread().getName());	
			}
			
		}
	}
	
	public void print1() {
		synchronized (res2) {
			System.out.println("res 2 locked by :"+Thread.currentThread().getName());
			synchronized (res1) {
				System.out.println("res 1 locked by :"+Thread.currentThread().getName());	
			}
			
		}
	}
	public static void main(String[] args) {
		DeadLock deadLock = new DeadLock();
		Thread t1= new Thread(()->deadLock.print());
		Thread t2 = new Thread(()->deadLock.print1());
		 t1.start();
		 t2.start();
	}

}
