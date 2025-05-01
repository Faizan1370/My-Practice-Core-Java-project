package com.faizan.multithread.prac;

public class DeadLockPrac {
	
	private static String resource1="resource1";
	private static String resource2 ="resource2";
	
	public void method1() {
		while(true) {
			synchronized (resource1) {
				System.out.println("Locked Resource 1 by thread1");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
			synchronized (resource2) {
				System.out.println("Locked Resource 2 by thread1");
			}
			}
			
		}
	}
	
	public void method2() {
		while(true) {
			synchronized (resource2) {
				System.out.println("Locked Resource 2 by thread2");
			 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (resource1) {
				System.out.println("Locked Resource 1 by thread2");
			}
			}
			
		}
	}
	
	public static void main(String[] args) {
		DeadLockPrac deadLockPrac = new DeadLockPrac();
		new Thread(()->deadLockPrac.method1()).start();
		new Thread(()->deadLockPrac.method2()).start();
	}

}
