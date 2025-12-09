package com.faizan.design.pattrens.singleton.prac3;

public class TestPr {
	
	public static void main(String[] args) {
		SingleTonPr pr = SingleTonPr.getInstance1();
		SingleTonPr pr1 = SingleTonPr.getInstance1();
		
		if(pr==pr1) {
			System.out.println(true);
		}else {
			System.out.println(false);
		}
		Runnable run= ()->{
			SingleTonPr prs= SingleTonPr.getInstance1();
			System.out.println(prs.hashCode());
		};
		Thread t1 = new Thread(run);
		Thread t2 = new Thread(run);
		Thread t3 = new Thread(run);
		Thread t4 = new Thread(run);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}
