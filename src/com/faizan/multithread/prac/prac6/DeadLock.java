package com.faizan.multithread.prac.prac6;

public class DeadLock {
	
	private static String res1="res1";
	private static String res2="res2";
	
	public void m1() {
		while(true) {
			synchronized (res1) {
				System.out.println("res 1 locked by :"+Thread.currentThread().getName());
				synchronized (res2) {
					System.out.println("res 2 locked by :"+Thread.currentThread().getName());
				}
			}
		}
	}
	

	public void m2() {
		while(true) {
			synchronized (res2) {
				System.out.println("res 2 locked by :"+Thread.currentThread().getName());
				synchronized (res1) {
					System.out.println("res 1 locked by :"+Thread.currentThread().getName());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		DeadLock deadLock = new DeadLock();
		Thread t1 = new Thread(()->deadLock.m1());
		Thread t2 = new Thread(()->deadLock.m2());
		t1.start();
		t2.start();
	}


}
