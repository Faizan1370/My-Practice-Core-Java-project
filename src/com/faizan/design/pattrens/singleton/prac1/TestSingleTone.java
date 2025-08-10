package com.faizan.design.pattrens.singleton.prac1;

public class TestSingleTone {
	
	public static void main(String[] args) {
//		Singleton4 singleton4 = Singleton4.getInstance();
//		Singleton4 singleton42 = Singleton4.getInstance();
//		
//		if(singleton4==singleton42) {
//			System.out.println("both are same");
//		}else {
//			System.out.println("failed single ton");
//		}
		
		Runnable task = () -> {
            Singleton4 instance = Singleton4.getInstance();
            System.out.println("Instance hash: " + instance.hashCode());
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();
	}

}
