package com.faizan.design.pattrens.singleton.prac1;

public class TestSingleTon5 {
	
	public static void main(String[] args) {
		Singleton5 singleton5 = Singleton5.getInstance();
		Singleton5 singleton52 = Singleton5.getInstance();
		if(singleton5==singleton52) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
		Runnable run =()->{
			Singleton5 singleton7 = Singleton5.getInstance1();
			System.out.println("Hash code :"+singleton7.hashCode());
		};
		Thread t1= new Thread(run);
		Thread t2= new Thread(run);
		Thread t3 = new Thread(run);
		
		t1.start();
		t2.start();
		t3.start();
	}

}
