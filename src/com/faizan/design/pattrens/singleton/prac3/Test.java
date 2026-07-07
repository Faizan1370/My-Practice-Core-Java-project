package com.faizan.design.pattrens.singleton.prac3;

public class Test {
	public static void main(String[] args) {
		SingleTonPrac prac = SingleTonPrac.getInsatce();
		SingleTonPrac prac2 = SingleTonPrac.getInsatce();
		
		System.out.println(prac.hashCode() +" "+prac2.hashCode());
		if(prac==prac2) {
			System.out.println(true);
		}else {
			System.out.println(false);
		}
		
		Runnable run= ()->{
			SingleTonPrac prs= SingleTonPrac.getInsatce2();
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
