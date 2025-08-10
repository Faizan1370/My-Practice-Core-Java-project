package com.faizan.multithread.prac.prac9;

public class DeadLokc {
	
	private static String res1="res1";
	private static String res2="res2";
	
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
	DeadLokc deadLokc = new DeadLokc();
	Thread t1= new Thread(()->deadLokc.m1());
	Thread t2 = new Thread(()->deadLokc.m2());
	
	t1.start();
	t2.start();
	
}

}
