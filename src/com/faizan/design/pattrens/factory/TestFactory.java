package com.faizan.design.pattrens.factory;

public class TestFactory {
	 
	public static void main(String[] args) {
		MobileFactory factory = new MobileFactory();
		Mobile orderMobile = factory.orderMobile("iphone");
		System.out.println(orderMobile);
		orderMobile.createMobile();
	}

}
