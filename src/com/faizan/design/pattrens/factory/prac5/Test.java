package com.faizan.design.pattrens.factory.prac5;

public class Test {
	
	public static void main(String[] args) {
		MobileFactory factory = new MobileFactory();
		Mobile orderMobile = factory.orderMobile("poco");
		orderMobile.createMobile();
	}

}
