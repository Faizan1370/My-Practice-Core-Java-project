package com.faizan.design.pattrens.singleton.prac2;

public class TestS {
	
	public static void main(String[] args) {
		Singleton singleton = Singleton.getInsatance1();
		Singleton singleton2 = Singleton.getInsatance1();
		
		if(singleton ==singleton2) {
			System.out.println("Same");
		}else {
			System.out.println("not same");
		}
		
		Runnable task =()->{
			Singleton singleton3 =Singleton.getInstance2();
			System.out.println(singleton3.hashCode());
		};
		
		Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
	}

}
