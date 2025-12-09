package com.faizan.design.pattrens.singleton.prac3;

public class SingleTonPr {
	
	private static volatile SingleTonPr singleTonPr;
	private static volatile SingleTonPr singleTonPr1 = new SingleTonPr();
	
	
	public static SingleTonPr getInstance() {
		if(singleTonPr==null) {
			singleTonPr= new SingleTonPr();
		}
		return singleTonPr;
	}
	
	public static SingleTonPr getInstance1() {
		if(singleTonPr == null) {
			synchronized (SingleTonPr.class) {
				if(singleTonPr == null) {
					singleTonPr = new SingleTonPr();
				}
			}
		}
		return singleTonPr;
	}
	public static SingleTonPr getInstance2() {
		return singleTonPr1;
	}
	

}
