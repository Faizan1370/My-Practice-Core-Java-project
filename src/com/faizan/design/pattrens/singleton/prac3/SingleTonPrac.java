package com.faizan.design.pattrens.singleton.prac3;

public class SingleTonPrac {
	
	private static  volatile SingleTonPrac prac;
	private static volatile SingleTonPrac  prac2 = new SingleTonPrac();
	
	
	public static SingleTonPrac getInsatce() { // for single thread
		if(prac==null) {
			prac = new SingleTonPrac();
		}
		return prac;
	}
	public static SingleTonPrac getInsatce2() { // for multiple thread
		if(prac==null) {
			synchronized (SingleTonPrac.class) {
				prac = new SingleTonPrac();
			}
			
		}
		return prac;
	}
	public static SingleTonPrac getInsatce3() { // for all
		return prac2;
	}

}
