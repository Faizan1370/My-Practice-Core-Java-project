package com.faizan.multithread.prac.prac18;

public class DeadLockPrr {
	
	private String res1="res1";
	private String res2="res2";
	
	public void m1() {
		while(true) {
			synchronized (res1) {
				System.out.println("res1 lokced by :"+Thread.currentThread().getName());
				synchronized (res2) {
					System.out.println("res2 lokced by :"+Thread.currentThread().getName());
				}
			}
		}
	}

	public void m2() {
		while(true) {
			synchronized (res2) {
				System.out.println("res2 lokced by :"+Thread.currentThread().getName());
				synchronized (res1) {
					System.out.println("res1 lokced by :"+Thread.currentThread().getName());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		DeadLockPrr deadLockPrr = new DeadLockPrr();
		Thread t1 = new Thread(()->deadLockPrr.m1());
		Thread t2 = new Thread(()->deadLockPrr.m2());
		
		t1.start();
		t2.start();
	}

}
