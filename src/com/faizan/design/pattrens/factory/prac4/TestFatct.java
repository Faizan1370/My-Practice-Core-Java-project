package com.faizan.design.pattrens.factory.prac4;

public class TestFatct {
	
	public static void main(String[] args) {
		MoobileFactory factory = new MoobileFactory();
		Mobile orderMobile = factory.orderMobile("abc");
		orderMobile.createMobile();
	}

}
