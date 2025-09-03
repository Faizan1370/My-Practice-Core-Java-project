package com.faizan.multithread.prac.prac16;

public class DeadLockPr {
	
	private String res1="res1";
	private String res2="res2";
	
	public void m1() {
		while(true){
			synchronized (res1) {
				System.out.println("Res1 locked by :"+Thread.currentThread().getName());
				synchronized (res2) {
				System.out.println("Res2 locked by :"+Thread.currentThread().getName());
				}
			}
		}
	}
	

	public void m2() {
		while(true){
			synchronized (res2) {
				System.out.println("Res2 locked by :"+Thread.currentThread().getName());
				synchronized (res1) {
				System.out.println("Res1 locked by :"+Thread.currentThread().getName());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		DeadLockPr deadLockPr = new DeadLockPr();
		Thread t1 = new Thread(()->deadLockPr.m1());
		Thread t2 = new Thread(()->deadLockPr.m2());
		t1.start();
		t2.start();
	}

}
